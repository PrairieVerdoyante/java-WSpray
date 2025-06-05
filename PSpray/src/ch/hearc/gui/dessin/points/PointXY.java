
package ch.hearc.gui.dessin.points;

import java.io.Serializable;

/**
 * Inaltérable
 */
public record PointXY(int x, int y) implements Serializable ,Cloneable
	{

	@Override
	public PointXY clone()
		{
		return this;
		}
	}
