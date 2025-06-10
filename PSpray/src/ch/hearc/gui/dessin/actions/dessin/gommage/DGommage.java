
package ch.hearc.gui.dessin.actions.dessin.gommage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.SprayGenerator;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.dessin.geste.Geste;
import ch.hearc.gui.utils.SprayShape;

public class DGommage extends Geste implements Dessin_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DGommage(int taille, int nbGouttes, int dimension, SprayShape shape)
		{
		this.taille = taille;
		this.nbGouttes = nbGouttes;
		this.dimension = dimension;
		this.shape = shape;
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{

		Color white = Color.WHITE; // simulate erasure
		Sprayage spray = SprayGenerator.generate(x, y, white, nbGouttes, taille, shape, dimension);
		calque.addSpray(spray);
		addSpray(spray);

		if (strokeList != null)
			{
			strokeList.add(spray);
			}

		}

	@Override
	public void draw(Graphics2D arg0, int arg1, int arg2)
		{
		// rien
		}

	@Override
	public Color getColor()
		{
		return Color.WHITE;
		}

	public int getNbGouttes()
		{
		return this.nbGouttes;
		}

	public int getDimension()
		{
		return this.dimension;
		}

	public int getTaille()
		{
		return this.taille;
		}

	private final int taille; // taille gouttelettes
	private final int nbGouttes;
	private final int dimension;
	private final SprayShape shape;

	}
