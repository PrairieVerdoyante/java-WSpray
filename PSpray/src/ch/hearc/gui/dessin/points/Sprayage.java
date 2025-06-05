
package ch.hearc.gui.dessin.points;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Sprayage
	{

	private final List<PointXY> points;
	private final Color color;

	public Sprayage(List<PointXY> points, Color color)
		{
		this.points = new ArrayList<>(points);
		this.color = color;
		}

	public List<PointXY> getPoints()
		{
		return new ArrayList<>(points);
		}

	public Color getColor()
		{
		return color;
		}
	}
