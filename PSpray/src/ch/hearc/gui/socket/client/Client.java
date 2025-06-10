
package ch.hearc.gui.socket.client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hearc.c_gui.z_tools.decorator.JFrameSimple;
import ch.hearc.c_gui.z_tools.decorator.image.JImage;
import ch.hearc.c_gui.z_tools.image.BImageSerializable;
import ch.hearc.e_socket.bin.a_tools.status.listener.StatusAdapter;
import ch.hearc.e_socket.bin.a_tools.status.listener.StatusListener;
import ch.hearc.e_socket.bin.a_tools.status.listener.error.ErrorEvent;
import ch.hearc.e_socket.bin.b_api.client.SocketClient;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.spray.display.JDisplay;

/**
 * start server before!
 */
public class Client
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	/**
	 * <pre>
	 * But :
	 * 		(B1) envoyer une image au serveur (CERVIN png)
	 * 		(B2) le serveur doit passer l'image en niveau de gris
	 * 		(B3) le serveur doit ajouter le watermark "He-arc"
	 * 		(B4) le serveur doit retourner l'iamge ainsi transformer
	 * 		(B5) le client doit afficher l'image original et l'image transformer par le serveur (tout a la fin!!, pas avant)
	 *
	 * Notes:
	 * 		(N1) Une BufferedImage n'est pas serializable
	 * 			 Utiliser notre classe BImageSerializabe pour corriger le problem
	 *
	 * 		(N2) Coter client utiliser les decorator
	 * 					JImage
	 * 					JFrameSimple
	 *
	 * 			pour afficher les images
	 *
	 * Defi 1
	 *
	 * 		(D1) Une fois le cervin de ImageShop reussi
	 * 			 Travailler avec l'image issu de la DComposition (Rainbow, Bidule, Cervin)
	 *
	 * 			Problems:
	 *
	 * 				(P1) Coter client il faut resussir a obtenir une BufferedImage de DComposition
	 *
	 * 				(P2) Sans mettre JComposition dans une JFrame
	 * 					 Tant que JComposition n'est pas dans une JFrame, il n a pas de taille, et donc l'iamge n'est pas dessiner.
	 *
	 * 			Solutions:
	 *
	 * 				(S1) Regarder les methodes offertes dans la classe Images mises à disposition
	 * 					 On peut convertir tout JComponent en une BufferedImage
	 *
	 * 				(S2) Utiliser la classe Repaints fournies, qui offre une methode repaintSync
	 * 					 On peut forcer les dessins a se dessiner
	 * 					 On peut forcer les jcomponents a se dessiner
	 * 					 sans la moindre JFrame
	 * 					 On a besoin d'un repaintAsync pour etre sur que l'image soit repaint avant de l'envoyer dans le socket
	 * 					 rappel : jcomponent.repaint() est async et poste dans fil d'attente AWT_QUEUE_EVENT (le thread swing) de dessiner
	 *
	 * Defi 2
	 *
	 * 		(D2) Idem defi ci-dessus, mais il est interdit d'utiliser Repaints.repaintAsync();
	 * </pre>
	 * @throws Exception
	 * @throws IOException
	 */
	public static void doSomething(String ip, int port, String title) throws IOException, Exception
		{
		SocketClient<BImageSerializable, BImageSerializable> client = new SocketClient<BImageSerializable, BImageSerializable>(ip, title, port);

		client.addStatusListener(createStatusListener());

		if (client.start()) // true if isStarted
			{
			exemple1(client);

			client.stop();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private static void exemple1(SocketClient<BImageSerializable, BImageSerializable> client) throws IOException, Exception
		{
		BufferedImage source = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = source.createGraphics();

		// Optionnel: fond blanc
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 1000, 1000);

		// Récupérer tous les sprays visibles
		List<Sprayage> allSprays = new ArrayList<>();
		for(DCalque calque:JDisplay.getInstance().getCalques())
			{
			if (calque.isVisible())
				{
				allSprays.addAll(calque.getSprays());
				}
			}

		// Dessiner tous les sprays
		for(Sprayage spray:allSprays)
			{
			spray.draw(g2d, 0, 0);
			}

		g2d.dispose();

		BImageSerializable bImageSerializable = new BImageSerializable(source);
		client.send(bImageSerializable);
		BImageSerializable response1 = client.waitResponse();
		JImage image = new JImage(response1.bimage());
		new JFrameSimple(image);
		}

	private static StatusListener createStatusListener()
		{
		return new StatusAdapter()
			{

			//			@Override
			//			public void onSuccess(SuccessEvent successEvent)
			//				{
			//				System.out.println();
			//				System.out.println(successEvent);
			//				System.out.println();
			//				}

			@Override
			public void onError(ErrorEvent errorEvent)
				{
				System.err.println();
				System.err.println(errorEvent);
				System.err.println();
				}
			};
		}

	}
