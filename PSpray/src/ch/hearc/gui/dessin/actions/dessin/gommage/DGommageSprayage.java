
package ch.hearc.gui.dessin.actions.dessin.gommage;

import java.awt.Color;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.SprayGenerator;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.utils.SprayShape;

public class DGommageSprayage extends DGommage
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DGommageSprayage(int taille, int nbGouttes, int dimension, SprayShape shape)
		{
		super(taille, nbGouttes, dimension, shape);
		this.gommeNumerique = new DGommageNumerique(taille, nbGouttes, dimension, shape, dimension / 2);
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{

		Color white = Color.WHITE; // simulate erasure
		Sprayage spray = SprayGenerator.generate(x, y, white, getNbGouttes(), getTaille(), SprayShape.CERCLE, getDimension());
		calque.addSpray(spray);
		addSpray(spray);

		if (strokeList != null)
			{
			strokeList.add(spray);
			}

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private DGommageNumerique gommeNumerique;

	}
