
package ch.hearc.gui.dessin.actions.Sprayage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.SprayGenerator;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.spray.display.JDisplay;
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
		switch(JDisplay.getInstance().getModeGomme())
			{
			case ModeGomme.SPRAYAGE:
				Color white = Color.WHITE; // simulate erasure
				Sprayage spray = SprayGenerator.generate(x, y, white, nbGouttes, taille, shape, dimension);
				calque.addSpray(spray);
				if (strokeList != null)
					{
					strokeList.add(spray);
					}
				break;
			default:
				break;
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

	private final int taille; // taille gouttelettes
	private final int nbGouttes;
	private final int dimension;
	private final SprayShape shape;

	}
