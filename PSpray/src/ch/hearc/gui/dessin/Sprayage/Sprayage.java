
package ch.hearc.gui.dessin.Sprayage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.dessin.points.PointXY;

public class Sprayage implements Dessin_I
	{

	public Sprayage(PointXY origin, List<Point> points, Color color, int taille)
		{
		this.origin = origin;
		this.points = points;
		this.color = color;
		this.taille = taille;
		}

	@Override
	public void draw(Graphics2D g2d, int arg1, int arg2)
		{
		g2d.setColor(color);
		for(Point p:points)
			{
			g2d.fillOval(p.x - taille / 2, p.y - taille / 2, taille, taille);
			}
		}

	public List<Point> getPoints()
		{
		return this.points;
		}

	public Color getColor()
		{
		return this.color;
		}

	public int getTaille()
		{
		return this.taille;
		}

	public PointXY getOrigin()
		{
		return this.origin;
		}

	public int getX()
		{
		return origin.x();
		}

	public int getY()
		{
		return origin.y();
		}

	private final List<Point> points;
	private final Color color;
	private final int taille;
	private final PointXY origin;

	}
