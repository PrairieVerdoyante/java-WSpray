
package ch.hearc.gui.socket.server;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import ch.hearc.b_poo.z_tools.Threads;
import ch.hearc.c_gui.z_tools.image.BImageSerializable;
import ch.hearc.e_socket.bin.a_tools.status.listener.StatusAdapter;
import ch.hearc.e_socket.bin.a_tools.status.listener.StatusListener;
import ch.hearc.e_socket.bin.a_tools.status.listener.error.ErrorEvent;
import ch.hearc.e_socket.bin.a_tools.status.listener.success.SuccessEvent;
import ch.hearc.e_socket.bin.b_api.server.SocketServer;
import ch.hearc.e_socket.bin.b_api.server.communication.Transformer_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.spray.display.JDisplay;

/**
 * color -> level gray
 */
public class Server
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void start(String tITLE, int pORT)
		{
		SocketServer<BImageSerializable, BImageSerializable> server = new SocketServer<BImageSerializable, BImageSerializable>(createSupplier(), tITLE, pORT);

		server.addStatusListener(createStatusListener());

		// Start
		server.start(); // async

		Threads.sleepMS(10 * 1000 * 6 * 2); // disons

		// Stop after LIFE_TIME_SERVER_MS
		server.stop(); // en pratique on ne l'arrete jamais
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static Supplier<Transformer_I<BImageSerializable, BImageSerializable>> createSupplier()
		{
		return new Supplier<Transformer_I<BImageSerializable, BImageSerializable>>()
			{

			@Override
			public Transformer_I<BImageSerializable, BImageSerializable> get()
				{
				return createTransformer();
				}
			};
		}

	private static Transformer_I<BImageSerializable, BImageSerializable> createTransformer()
		{
		return new Transformer_I<BImageSerializable, BImageSerializable>()
			{

			@Override
			public BImageSerializable transform(BImageSerializable serializable)
				{

				BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = image.createGraphics();

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

				ColorToLevelgray converter = new ColorToLevelgray(image);
				converter.run();

				return new BImageSerializable(image);
				}
			};
		}

	private static StatusListener createStatusListener()
		{
		return new StatusAdapter()
			{

			@Override
			public void onSuccess(SuccessEvent successEvent)
				{
				System.out.println(successEvent);
				}

			@Override
			public void onError(ErrorEvent errorEvent)
				{
				System.err.println(errorEvent);
				}
			};
		}

	}
