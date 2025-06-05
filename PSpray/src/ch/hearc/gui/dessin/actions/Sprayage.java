
package ch.hearc.gui.dessin.actions;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.export.SprayStorage;
import ch.hearc.gui.utils.DSpray;
import ch.hearc.gui.utils.SprayShape;

public class Sprayage extends Geste
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public Sprayage(Color color, int nbGouttes, int taille, int dimension, SprayShape shape, boolean logImmediately)
		{
		this.color = color;
		this.nbGouttes = nbGouttes;
		this.taille = taille;
		this.dimension = dimension;
		this.shape = shape;
		this.logImmediately = logImmediately;
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void apply(int x, int y, DCalque calque, List<DSpray> strokeList)
		{
		if (calque != null)
			{
			DSpray spray = new DSpray(x, y, color, nbGouttes, taille, shape, dimension);
			calque.addSpray(spray);

			if (logImmediately)
				{
				try
					{
					SprayStorage.append(spray, "sprays.bin");
					}
				catch (IOException e)
					{
					e.printStackTrace();
					}
				}

			if (strokeList != null)
				{
				strokeList.add(spray);
				}
			}
		}

	/*------------------------------------------------------------------*\
	|*						Attributes Private							*|
	\*------------------------------------------------------------------*/

	private final Color color;
	private final int nbGouttes;
	private final int taille;
	private final int dimension;
	private final SprayShape shape;
	private final boolean logImmediately;
	}
