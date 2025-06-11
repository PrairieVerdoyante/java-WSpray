
package ch.hearc.gui.dessin.Sprayage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.dessin.points.PointXY;

public class Sprayage implements Dessin_I, Serializable
	{

	/*------------------------------------------------------------------*\
	|*							Constructor								*|
	\*------------------------------------------------------------------*/

	public Sprayage(PointXY origin, List<Point> points, Color color, int taille)
		{
		this.origin = origin;
		this.points = points;
		this.color = color;
		this.taille = taille;
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

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

	public boolean isEmpty()
		{
		return this.points.isEmpty();
		}

	public boolean removePointsInCircle(int x, int y, int r)
		{
		// Supposons qu'il y a une liste interne de Point ou coordonnées
		Iterator<Point> it = this.points.iterator();
		boolean removed = false;

		while(it.hasNext())
			{
			Point p = it.next();
			int dx = p.x - x;
			int dy = p.y - y;
			if (dx * dx + dy * dy <= r * r)
				{
				it.remove();
				removed = true;
				}
			}
		return removed;
		}

	public List<Point> removePointsInCircleWithReturn(int x, int y, int radius)
		{
		List<Point> removedPoints = new ArrayList<>();
		Iterator<Point> it = this.points.iterator();

		while(it.hasNext())
			{
			Point p = it.next();
			int dx = p.x - x;
			int dy = p.y - y;

			if (dx * dx + dy * dy <= radius * radius)
				{
				removedPoints.add(new Point(p)); // add a copy to removedPoints
				it.remove();
				}
			}

		return removedPoints;
		}

	/**
	 * rajoute les points au spray.
	 */
	public void addPoints(List<Point> pointsToAdd)
		{
		this.points.addAll(pointsToAdd);
		}

	/**
	 * enlève les points du spray.
	 */
	public void removePoints(List<Point> pointsToRemove)
		{
		this.points.removeAll(pointsToRemove);
		}

	/*------------------------------------------------------------------*\
	|*						Attributes private							*|
	\*------------------------------------------------------------------*/
	// inputs
	private final List<Point> points;
	private final Color color;
	private final int taille;
	private final PointXY origin;

	}
