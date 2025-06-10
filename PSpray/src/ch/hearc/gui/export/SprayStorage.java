
package ch.hearc.gui.export;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;

import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.points.PointXY;

public class SprayStorage
	{

	// Write Sprayages to a non compressed binary file
	public static void write(List<Sprayage> sprays, String fileName) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);

		for(Sprayage spray:sprays)
			{
			dos.writeInt(spray.getColor().getRGB());
			dos.writeInt(spray.getTaille());

			List<Point> points = spray.getPoints();
			dos.writeInt(points.size());

			for(Point p:points)
				{
				dos.writeInt(p.x);
				dos.writeInt(p.y);
				}
			}
		dos.close();
		bos.close();
		fos.close();

		}

	// Read Sprayages from a non compressed binary file
	public static List<Sprayage> read(String fileName) throws IOException
		{
		List<Sprayage> sprays = new LinkedList<>();
		FileInputStream fis = new FileInputStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);

		while(true)
			{

			Color color = new Color(dis.readInt(), true);
			int taille = dis.readInt();
			int nbPoints = dis.readInt();

			List<Point> points = new ArrayList<>();
			for(int i = 0; i < nbPoints; i++)
				{
				int x = dis.readInt();
				int y = dis.readInt();
				points.add(new Point(x, y));
				}

			Point originPoint = points.get(0);
			PointXY origin = new PointXY((int)originPoint.getX(), (int)originPoint.getY());
			sprays.add(new Sprayage(origin, points, color, taille));

			dis.close();
			bis.close();
			fis.close();

			return sprays;

			}
		}

	public static void exportToCsv(List<Sprayage> sprays, String fileName, boolean data, boolean labelized) throws IOException
		{
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter writer = new BufferedWriter(fw);

		for(Sprayage spray:sprays)
			{
			String colorName = getColorName(spray.getColor());
			int taille = spray.getTaille();

			for(Point p:spray.getPoints())
				{
				if (labelized)
					{
					// On n'écrit que les coordonnées (labelized = true)
					writer.write(p.x + ";" + p.y);
					}
				else if (data)
					{
					// Sans taille, avec couleur
					writer.write(p.x + ";" + p.y + ";" + colorName);
					}
				else
					{
					// Avec taille et couleur
					writer.write(p.x + ";" + p.y + ";" + taille + ";" + colorName);
					}

				writer.newLine();
				}
			}

		writer.close();
		fw.close();
		}

	// Import basic CSV into Sprayages (one point per spray)
	public static List<Sprayage> importFromCsv(String fileName) throws IOException
		{
		List<Sprayage> sprays = new ArrayList<>();
		int defaultTaille = 5;
		Color defaultColor = Color.BLACK;

		FileReader fr = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(fr);

		String line;
		while((line = reader.readLine()) != null)
			{
			String[] parts = line.split(";");

			if (parts.length < 2 || parts.length > 4)
				{
				continue;
				}

			int x = Integer.parseInt(parts[0].trim());
			int y = Integer.parseInt(parts[1].trim());

			int taille;
			Color color;

			if (parts.length == 2)
				{
				// Labelized : seulement x ; y
				taille = defaultTaille;
				color = defaultColor;
				}
			else if (parts.length == 3)
				{
				// Data : x ; y ; color
				taille = defaultTaille;
				color = parseColor(parts[2].trim());
				}
			else // parts.length == 4
				{
				// Complet : x ; y ; taille ; color
				taille = Integer.parseInt(parts[2].trim());
				color = parseColor(parts[3].trim());
				}

			List<Point> points = new ArrayList<>();
			points.add(new Point(x, y));

			sprays.add(new Sprayage(new PointXY(x, y), points, color, taille));
			}

		reader.close();
		fr.close();

		return sprays;

		}

	public static void exportToPng(List<Sprayage> sprays, String fileName) throws IOException
		{
		int width = 1000;
		int height = 1000;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();

		// Optional: white background
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);

		for(Sprayage spray:sprays)
			{
			spray.draw(g2d, 0, 0);
			}

		g2d.dispose();

		File outputFile = new File(fileName);
		ImageIO.write(image, "png", outputFile);
		}

	//
	//	private void copyToClipboard(List<Sprays> sprays)
	//	{
	//
	//	}

	public static void fileSave(String fileName, Object objectToSave)
		{
		try
			{
			FileOutputStream fos = new FileOutputStream(fileName);
			GZIPOutputStream gos = new GZIPOutputStream(fos);
			BufferedOutputStream bos = new BufferedOutputStream(gos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(objectToSave);

			oos.close();
			bos.close();
			gos.close();
			fos.close();
			}
		catch(IOException e)
			{
			e.printStackTrace();
			}
		}

	public static Object fileLoad(String fileName)
		{
		try
			{
			FileInputStream fis = new FileInputStream(fileName);
			GZIPInputStream gis = new GZIPInputStream(fis);
			BufferedInputStream bis = new BufferedInputStream(gis);
			ObjectInputStream ois = new ObjectInputStream(bis);

			Object obj = ois.readObject();

			ois.close();
			bis.close();
			gis.close();
			fis.close();

			return obj;
			}
		catch(IOException | ClassNotFoundException e)
			{
			e.printStackTrace();
			return null;
			}
		}


	// Color name → String
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
		return String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
		}

	// String → Color
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
					yield Color.GRAY;
					}
				}
			else
				{
				yield Color.GRAY;
				}
			}
			};
		}
	}
