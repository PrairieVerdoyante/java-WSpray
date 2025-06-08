
package ch.hearc.gui.spray;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hearc.gui.dessin.actions.dessin.Geste;
import ch.hearc.gui.dessin.points.PointXY;
import ch.hearc.gui.spray.display.JDisplay;

public class JSpray extends JDisplay implements JSpray_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/
	public JSpray() throws IOException
		{
		super();
		}
	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/**
	 * JSpray_I
	 */
	@Override
	public Map<Color, PointXY[]> getClusters()
		{
		Map<Color, List<PointXY>> map = new HashMap<>();

		for(Geste spray:getCurrentStroke())
			{
			Color color = spray.getColor();
			PointXY point = new PointXY(spray.getX(), spray.getY());

			map.computeIfAbsent(color, k -> new ArrayList<>()).add(point);
			}

		// Conversion en tableaux
		Map<Color, PointXY[]> clusters = new HashMap<>();
		for(Map.Entry<Color, List<PointXY>> entry:map.entrySet())
			{
			clusters.put(entry.getKey(), entry.getValue().toArray(new PointXY[0]));
			}

		return clusters;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/
	}
