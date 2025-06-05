
package ch.hearc.gui.spray;

import java.awt.Color;
import java.util.Map;

import ch.hearc.gui.dessin.points.PointXY;

@FunctionalInterface
public interface JSpray_I
	{

	public Map<Color, PointXY[]> getClusters();

	}
