
package ch.hearc.gui.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DSpray
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DSpray(int x, int y, Color color, int nbGouttes, int taille, SprayShape shape, int dimension)
		{
		this.x = x;
		this.y = y;
		this.color = color;
		this.taille = taille;
		this.shape = shape;
		this.dimension = dimension;
		this.points = new ArrayList<>();

		Random random = new Random();

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
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public void draw(Graphics2D g2d)
		{
		g2d.setColor(color);
		for(Point p:points)
			{
			int r = taille;
			g2d.fillOval(p.x - r / 2, p.y - r / 2, r, r);
			}
		}

	public void draw(Graphics g)
		{
		g.setColor(color);
		g.fillOval(x - taille / 2, y - taille / 2, taille, taille);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getX()
		{
		return this.x;
		}

	public int getY()
		{
		return this.y;
		}

	public Color getColor()
		{
		return this.color;
		}

	public int getNbGouttes()
		{
		return this.nbGouttes;
		}

	public int getTaille()
		{
		return this.taille;
		}

	public SprayShape getShape()
		{
		return this.shape;
		}

	public int getDimension()
		{
		return this.dimension;
		}

	public boolean isNear(int x2, int y2)
		{
		// TODO Auto-generated method stub
		return false;
		}
	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	private int x, y;
	private Color color;
	private int nbGouttes;
	private int taille;
	private int dimension;
	private SprayShape shape;

	private List<Point> points;

	}
