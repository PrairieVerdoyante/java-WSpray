
package ch.hearc.gui.dessin.Sprayage;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.hearc.gui.dessin.points.PointXY;
import ch.hearc.gui.utils.SprayShape;

public class SprayGenerator
	{
	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public static Sprayage generate(int x, int y, Color color, int nbGouttes, int taille, SprayShape shape, int dimension)
		{
		List<Point> points = new ArrayList<>();
		Random random = new Random();
		PointXY center = new PointXY(x, y);

		if (shape == SprayShape.CERCLE)
			{
			for(int i = 0; i < nbGouttes; i++)
				{
				double angle = 2 * Math.PI * random.nextDouble();
				double radius = dimension * Math.sqrt(random.nextDouble());
				int px = (int)(x + radius * Math.cos(angle));
				int py = (int)(y + radius * Math.sin(angle));
				points.add(new Point(px, py));
				}
			}
		else if (shape == SprayShape.CARRE)
			{
			for(int i = 0; i < nbGouttes; i++)
				{
				int px = x - dimension / 2 + random.nextInt(dimension);
				int py = y - dimension / 2 + random.nextInt(dimension);
				points.add(new Point(px, py));
				}
			}

		return new Sprayage(center, points, color, taille);
		}

	public static Sprayage generateCircle(int x, int y, int taille, int rayon)
		{
		List<Point> points = new ArrayList<>();
		PointXY center = new PointXY(x, y);
		Color color = Color.WHITE;

		points.add(new Point(x, y)); // Point unique exactement au centre

		return new Sprayage(center, points, color, 2 * rayon);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	}
