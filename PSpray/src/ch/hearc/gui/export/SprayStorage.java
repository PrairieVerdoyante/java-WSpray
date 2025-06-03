
package ch.hearc.gui.export;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import ch.hearc.gui.utils.DSpray;
import ch.hearc.gui.utils.SprayShape;

public class SprayStorage
	{

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			Static				*|
	\*------------------------------*/

	public static void write(List<DSpray> sprays, String fileName) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(fileName);
		GZIPOutputStream gos = new GZIPOutputStream(fos);
		BufferedOutputStream bos = new BufferedOutputStream(gos);
		DataOutputStream dos = new DataOutputStream(bos);

		for(DSpray spray:sprays)
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

	public static List<DSpray> read(String fileName) throws IOException
		{
		FileInputStream fis = new FileInputStream(fileName);
		GZIPInputStream gis = new GZIPInputStream(fis);
		BufferedInputStream bis = new BufferedInputStream(gis);
		DataInputStream dis = new DataInputStream(bis);

		List<DSpray> sprays = new LinkedList<>();

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

				sprays.add(new DSpray(x, y, color, nbGouttes, taille, shape, dimension));
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

	public static void append(DSpray spray, String fileName) throws IOException
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

	public static void exportToCsv(List<DSpray> sprays, String fileName) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		StringBuilder sb = new StringBuilder();

		for(DSpray spray:sprays)
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

	public static List<DSpray> importFromCsv(String fileName) throws IOException
		{
		List<DSpray> sprays = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;

		while((line = reader.readLine()) != null)
			{
			String[] parts = line.split(";");
			if (parts.length != 4)
				{
				continue;
				}

			int x = Integer.parseInt(parts[0].trim());
			int y = Integer.parseInt(parts[1].trim());
			int radius = Integer.parseInt(parts[2].trim());
			String colorName = parts[3].trim();

			Color color = parseColor(colorName);
			DSpray spray = new DSpray(x, y, color, 100, radius, SprayShape.CERCLE, radius); // Default values

			sprays.add(spray);
			}

		reader.close();
		return sprays;
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

		// If not a known color, return RGB format
		return String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
		}

	private static Color parseColor(String name)
		{
		return switch(name.toLowerCase())
			{
			case "rouge" -> Color.RED;
			case "vert" -> Color.GREEN;
			case "bleu" -> Color.BLUE;
			case "pink" -> Color.PINK;
			case "noir" -> Color.BLACK;
			case "blanc" -> Color.WHITE;
			case "jaune" -> Color.YELLOW;
			case "orange" -> Color.ORANGE;
			case "cyan" -> Color.CYAN;
			case "magenta" -> Color.MAGENTA;
			default -> {
			if (name.startsWith("rgb(") && name.endsWith(")"))
				{
				try
					{
					String[] rgb = name.substring(4, name.length() - 1).split(",");
					int r = Integer.parseInt(rgb[0].trim());
					int g = Integer.parseInt(rgb[1].trim());
					int b = Integer.parseInt(rgb[2].trim());
					yield new Color(r, g, b);
					}
				catch (Exception e)
					{
					yield Color.GRAY; // fallback color on parse error
					}
				}
			else
				{
				yield Color.GRAY; // unknown string
				}
			}
			};
		}
	}
