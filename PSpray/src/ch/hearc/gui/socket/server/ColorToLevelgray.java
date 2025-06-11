
package ch.hearc.gui.socket.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ColorToLevelgray implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * mise a jour de l'image
	 */
	public ColorToLevelgray(BufferedImage bimage)
		{
		// inputs/outputs
			{
			this.bimage = bimage;
			}

		// Tools
			{
			this.font = new Font("Helvetica", Font.BOLD + Font.ITALIC, 50); //police, decoration, taille
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		togray();
		watermark();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/**
	 * meme que l'input
	 */
	public BufferedImage getbImage()
		{
		return this.bimage;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * <pre>
	 * Indications:
	 *
	 * 		(I1)	Double boucle pour parcourir l'image
	 * 		(I2)	getRGB
	 * 				transformation du pixel en gris avec la methode private int toGray(int intARGB)
	 * 				setRGB
	 * 		(I3)	voir DRainbow
	 *
	 * Note:
	 *
	 * 		Plus performant avec un parcours  "row major"
	 *
	 * Defi :
	 *
	 * 		Passer par le raster pour aller plus vite (Voir Images à cet effet)
	 *		 ie recuper l'image sous la forme d'un tableau de byte
	 *
	 * 				int[] arrayRGB = Images.getArrayInt(bimage);
	 *
	 * 		puis travailler sur ce arrayRGB avec une unique boucle
	 *
	 * 				for(int s = 0; s < WH; s++)
	 *
	 * 		note : pas de set et get sur chaque pixel comme la version originale
	 *
	 * </pre>
	 */
	private void togray()
		{
		int width = bimage.getWidth();
		int height = bimage.getHeight();

		for(int y = 0; y < height; y++)
			{
			for(int x = 0; x < width; x++)
				{
				int argb = bimage.getRGB(x, y);
				int grayARGB = toGray(argb);
				bimage.setRGB(x, y, grayARGB);
				}
			}
		}

	/**
	 * <pre>
	 * Contraintes:
	 *
	 * 		(C1)	color rouge
	 * 		(C2)	la font (attribut)
	 *
	 * Note:
	 *
	 * 		Pas besoin de backuper g2d, il est detruit ici a la fin!
	 * </pre>
	 */
	private void watermark()
		{
		Graphics2D g2d = bimage.createGraphics();

		g2d.setColor(Color.RED); // (C1)
		g2d.setFont(font); // (C2)

		// Dessine un texte simple, tu peux l'améliorer si besoin
		g2d.drawString("WATERMARK", 20, bimage.getHeight() - 20);

		g2d.dispose(); // libération
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/**
	 * <pre>
	 * Input:
	 *
	 * 		Une color ARGB dans 4 octets de 1 int
	 *
	 * Output:
	 *
	 * 		Une color ARGB dans 4 octets de 1 int
	 *
	 * Indications :
	 *
	 * 		(I1) 	Color color = new Color(intARGB, true);  // warning : true pour indiquer existence couche alpha
	 * 			 	int red=color.getRed();
	 * 				int green=color.getGreen();
	 * 				int blue=color.getBlue();
	 * 				int alpha=color.getAlpha();
	 *
	 * 		(I2)	new Color(r,g,b,alpha).getRGB()
	 *
	 * Tip:
	 *
	 * 		Une image en niveau de gris a 3 cannaux couleur identique, que l'on peut obtenir en faisant soit:
	 * 			- une moyenne
	 * 			- une moyenne ponderer (standard (ITU-R BT.601))
	 *
	 * 			moyenne simple  : 	levelGray=(R+G+B)/3
	 * 			moyenne ponderer:   levelGray=0.299 * R + 0.587 * G + 0.114 * B;
	 *
	 * </pre>
	 */
	private static int toGray(int intARGB)
		{
	    Color color = new Color(intARGB, true);

	    int alpha = color.getAlpha();
	    int red = color.getRed();
	    int green = color.getGreen();
	    int blue = color.getBlue();

	    // Moyenne pondérée (recommandée)
	    int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue);

	    return new Color(gray, gray, gray, alpha).getRGB();
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs/outputs
	private BufferedImage bimage;

	// Tools
	private Font font;

	}
