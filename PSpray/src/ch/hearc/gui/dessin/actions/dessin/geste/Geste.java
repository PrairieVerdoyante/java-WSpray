
package ch.hearc.gui.dessin.actions.dessin.geste;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;

public abstract class Geste
	{

	/*------------------------------------------------------------------*\
	|*							Constructor								*|
	\*------------------------------------------------------------------*/

	public Geste()
		{
		this.sprays = new ArrayList<>();
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	public abstract void apply(int x, int y, DCalque calque, List<Sprayage> strokeList);

	/**
	 * Add all generated spray to the calque (used for redo)
	 */
	public void redo(DCalque calque)
		{
		for(Sprayage spray:sprays)
			{
			calque.addSpray(spray);
			}
		}

	/**
	 * Remove all spray from the calque (used for undo)
	 */
	public void undo(DCalque calque)
		{
		for(Sprayage spray:sprays)
			{
			calque.removeSpray(spray);
			}
		}

	public void addSpray(Sprayage spray)
		{
		sprays.add(spray);
		}

	public List<Sprayage> getSprays()
		{
		return sprays;
		}

	public abstract Color getColor();

	public int getX()
		{
		return x;
		}

	public int getY()
		{
		return y;
		}

	public void setPosition(int x, int y)
		{
		this.x = x;
		this.y = y;
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	// tools
	private List<Sprayage> sprays;
	private int x;
	private int y;

	}
