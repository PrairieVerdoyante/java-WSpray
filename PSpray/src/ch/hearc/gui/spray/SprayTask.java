
package ch.hearc.gui.spray;

import java.awt.Color;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.Sprayage.DSprayage;
import ch.hearc.gui.utils.SprayShape;

/**
 * Pour eviter le freeze ou les bugs(FreezeNo classe)
 */
public abstract class SprayTask implements Runnable
	{

	private int x, y;
	private DCalque calque;
	private List<Sprayage> stroke;
	private DSprayage spray;

	public SprayTask(int x, int y, Color color, int taille, int nbGouttes, int dimension, SprayShape shape, DCalque calque, List<Sprayage> stroke)
		{
		this.x = x;
		this.y = y;
		this.currentColor = color;
		this.currentTaille = taille;
		this.currentNbGouttes = nbGouttes;
		this.currentDimension = dimension;
		this.currentShape = shape;

		this.calque = calque;
		this.stroke = stroke;
		}

	@Override
	public void run()
		{
		try
			{
			this.spray = new DSprayage(this.currentColor, this.currentTaille, this.currentNbGouttes, this.currentDimension, this.currentShape); // tous les paramètres nécessaires
			spray.apply(x, y, calque, stroke);

			onSuccess(spray);
			}
		catch (Exception e)
			{
			onFailure(e);
			}
		}

	public abstract void onSuccess(DSprayage spray);

	public abstract void onFailure(Exception e);

	private DCalque currentCalque; // TODO: repetition code
	private Color currentColor;
	private int currentNbGouttes;
	private int currentTaille;
	private int currentDimension;
	private SprayShape currentShape;
	}
