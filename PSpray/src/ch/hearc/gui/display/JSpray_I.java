
package ch.hearc.gui.display;

import java.awt.Color;
import java.util.Map;

import ch.hearc.gui.dessin.sprays.PointXY;

@FunctionalInterface
public interface JSpray_I
	{

	public Map<Color, PointXY[]> getClusters();

	}
