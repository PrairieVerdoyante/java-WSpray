
package ch.hearc.gui.export;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import ch.hearc.gui.utils.Spray;
import ch.hearc.gui.utils.SprayShape;

public class SprayStorage
	{

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			Static				*|
	\*------------------------------*/

	public static void write(List<Spray> sprays, String fileName) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(fileName);
		GZIPOutputStream gos = new GZIPOutputStream(fos);
		BufferedOutputStream bos = new BufferedOutputStream(gos);
		DataOutputStream dos = new DataOutputStream(bos);

		for(Spray spray:sprays)
			{
			dos.writeInt(spray.getX());
			dos.writeInt(spray.getY());
			dos.writeInt(spray.getColor().getRGB());
			dos.writeInt(spray.getNbGouttes());
			dos.writeInt(spray.getTaille());
			dos.writeInt(spray.getShape().ordinal());
			dos.writeInt(spray.getDimension());
			}

		dos.close();
		bos.close();
		gos.close();
		fos.close();
		}

	public static List<Spray> read(String fileName) throws IOException
		{
		FileInputStream fis = new FileInputStream(fileName);
		GZIPInputStream gis = new GZIPInputStream(fis);
		BufferedInputStream bis = new BufferedInputStream(gis);
		DataInputStream dis = new DataInputStream(bis);

		List<Spray> sprays = new LinkedList<>();

		try
			{
			while(true)
				{
				int x = dis.readInt();
				int y = dis.readInt();
				Color color = new Color(dis.readInt(), true);
				int nbGouttes = dis.readInt();
				int taille = dis.readInt();
				SprayShape shape = SprayShape.values()[dis.readInt()];
				int dimension = dis.readInt();

				sprays.add(new Spray(x, y, color, nbGouttes, taille, shape, dimension));
				}
			}
		catch (EOFException e)
			{
			// End of file
			}

		dis.close();
		bis.close();
		gis.close();
		fis.close();

		return sprays;
		}

	public static void append(Spray spray, String fileName) throws IOException
		{
		boolean fileExists = new File(fileName).exists();

		FileOutputStream fos = new FileOutputStream(fileName, true);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos;

		if (!fileExists)
			{
			// Write header if new file
			dos = new DataOutputStream(bos);
			}
		else
			{
			// Append without writing headers
			dos = new DataOutputStream(bos);
			}

		// Write spray data
		dos.writeInt(spray.getX());
		dos.writeInt(spray.getY());
		dos.writeInt(spray.getColor().getRGB());
		dos.writeInt(spray.getNbGouttes());
		dos.writeInt(spray.getTaille());
		dos.writeUTF(spray.getShape().name());
		dos.writeInt(spray.getDimension());

		dos.close();
		bos.close();
		fos.close();
		}

	public static void exportToCsv(List<Spray> sprays, String fileName) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		StringBuilder sb = new StringBuilder();

		for(Spray spray:sprays)
			{
			int x = spray.getX();
			int y = spray.getY();
			int radius = spray.getTaille();
			String colorName = getColorName(spray.getColor());

			// Format: x ; y ; rayon ; couleur
			sb.append(x).append(" ;").append(y).append(" ;").append(radius).append(" ;").append(colorName).append("\n");
			}

		bos.write(sb.toString().getBytes());
		bos.flush();
		bos.close();
		fos.close();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/


	private static String getColorName(Color color)
		{
		if (color.equals(Color.RED))
			{ return "rouge"; }
		if (color.equals(Color.GREEN))
			{ return "vert"; }
		if (color.equals(Color.BLUE))
			{ return "bleu"; }
		if (color.equals(Color.PINK))
			{ return "pink"; }
		if (color.equals(Color.BLACK))
			{ return "noir"; }
		if (color.equals(Color.WHITE))
			{ return "blanc"; }
		if (color.equals(Color.YELLOW))
			{ return "jaune"; }
		if (color.equals(Color.ORANGE))
			{ return "orange"; }
		if (color.equals(Color.CYAN))
			{ return "cyan"; }
		if (color.equals(Color.MAGENTA))
			{ return "magenta"; }

		return "inconnu";
		}

	}
