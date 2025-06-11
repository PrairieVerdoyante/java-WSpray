
package ch.hearc.gui.dessin.actions.dessin.geste;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;

public abstract class Geste implements Serializable
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
	 * ajouter tous les sprays au calque (pour redo)
	 */
	public void redo(DCalque calque)
		{
		for(Sprayage spray:sprays)
			{
			calque.addSpray(spray);
			}
		}

	/**
	 * Enlever tous les sprays du calque (undo)
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
