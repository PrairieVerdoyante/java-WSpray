
package ch.hearc.gui.dessin.actions.dessin.gommage;

import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.SprayGenerator;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.utils.SprayShape;

public class DGommageLigne extends DGommage implements Dessin_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DGommageLigne(int taille, int nbGouttes, int dimension, SprayShape shape, int epaisseur)
		{
		super(taille, nbGouttes, dimension, shape);
		this.gommeNumerique = new DGommageNumerique(taille, nbGouttes, dimension, shape, epaisseur);
		this.epaisseur = epaisseur;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public void apply(int x1, int y1, int x2, int y2, DCalque calque, List<Sprayage> strokeList)
		{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

		// Sprayage visuel
		Sprayage ligneSpray = SprayGenerator.generateLine(x1, y1, x2, y2, epaisseur);
		calque.addSpray(ligneSpray);
		addSpray(ligneSpray); // log in gesture

		if (strokeList != null)
			{
			strokeList.add(ligneSpray);
			}

		// Numerical erasure along the line
		// applyNumericalGomme(x1, y1, x2, y2, calque, strokeList);
		gommeNumerique.apply(x2, y2, calque, strokeList);
		}

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{
		// Not used: line eraser should be applied with 2 points
		throw new UnsupportedOperationException("Use apply(x1, y1, x2, y2, ...)");
		}

	@Override
	public void undo(DCalque calque)
		{
		gommeNumerique.undo(calque);
		super.undo(calque);
		}

	@Override
	public void redo(DCalque calque)
		{
		gommeNumerique.redo(calque);
		super.redo(calque);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void applyNumericalGomme(int x1, int y1, int x2, int y2, DCalque calque, List<Sprayage> strokeList)
		{
		int steps = (int)(Math.hypot(x2 - x1, y2 - y1) / (getDimension() * 0.5)); // more dense points
		for(int i = 0; i <= steps; i++)
			{
			double t = (double)i / steps;
			int xi = (int)(x1 + t * (x2 - x1));
			int yi = (int)(y1 + t * (y2 - y1));
			gommeNumerique.apply(xi, yi, calque, strokeList);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/
	private int x1, y1, x2, y2;
	private int epaisseur;
	private DGommageNumerique gommeNumerique;

	}
