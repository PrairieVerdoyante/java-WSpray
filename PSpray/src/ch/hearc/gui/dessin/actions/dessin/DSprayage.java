
package ch.hearc.gui.dessin.actions.dessin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.SprayGenerator;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.utils.SprayShape;

public class DSprayage extends Geste implements Dessin_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DSprayage(Color color, int taille, int nbGouttes, int dimension, SprayShape shape)
		{
		this.color = color;
		this.taille = taille;
		this.nbGouttes = nbGouttes;
		this.dimension = dimension;
		this.shape = shape;
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/


	@Override
	public void draw(Graphics2D g2d, int arg1, int arg2)
		{
		// rien à dessiner ici directement
		}

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{
		this.setPosition(x, y);

		Sprayage spray = SprayGenerator.generate(x, y, color, nbGouttes, taille, shape, dimension);
		calque.addSpray(spray);
		addSpray(spray); // ajout au geste
		System.out.println("Application d'un sprayage.");

		if (strokeList != null)
			{
			strokeList.add(spray);
			}
		}

	@Override
	public Color getColor()
	{
	return this.color;
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// tools
	private final Color color;
	private final int taille; // taille gouttelettes
	private final int nbGouttes;
	private final int dimension;
	private final SprayShape shape;
	}
