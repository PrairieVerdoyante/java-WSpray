
package ch.hearc.gui.export;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

	// Export to CSV
	public static void exportToCsv(List<Sprayage> sprays, String fileName) throws IOException
		{
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter writer = new BufferedWriter(fw);

		for(Sprayage spray:sprays)
			{
			String colorName = getColorName(spray.getColor());
			int taille = spray.getTaille();

			for(Point p:spray.getPoints())
				{
				writer.write(p.x + ";" + p.y + ";" + taille + ";" + colorName);
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

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
			{
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
				int taille = Integer.parseInt(parts[2].trim());
				Color color = parseColor(parts[3].trim());

				List<Point> points = new ArrayList<>();
				points.add(new Point(x, y));

				sprays.add(new Sprayage(new PointXY(x, y), points, color, taille));
				}
			}

		return sprays;
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
