
package ch.hearc.gui.dessin.actions.dessin;

import java.util.List;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.SprayGenerator;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.utils.SprayShape;

public class DGommageCercle extends DGommage implements Dessin_I
	{

	public DGommageCercle(int taille, int nbGouttes, int dimension, SprayShape shape, int rayon)
		{
		super(taille, nbGouttes, dimension, shape);
		this.rayon = rayon;
		this.gommeNumerique = new DGommageNumerique(taille, nbGouttes, dimension, shape, rayon);
		}

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{
		int valeur = (int)Math.round(2 * Math.PI * rayon);
		Sprayage gommeVisuelle = SprayGenerator.generateCircle(x, y, valeur, rayon);
		calque.addSpray(gommeVisuelle);
		addSpray(gommeVisuelle); // ajout au geste.

		if (strokeList != null)
			{
			strokeList.add(gommeVisuelle);
			}

		// enlever les cercles qui sont dans l'endroit, numériquement
		//DGommageNumerique gommeNumerique = new DGommageNumerique(getTaille(), getNbGouttes(), getDimension(), SprayShape.CERCLE, rayon);
		gommeNumerique.apply(x, y, calque, strokeList);
		}

	@Override
	public void undo(DCalque calque)
		{
		// Undo the numeric erasing stored in DGommageNumerique
		gommeNumerique.undo(calque);

		// Undo this gesture’s visual spray if needed
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

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/
	private int rayon;
	private DGommageNumerique gommeNumerique;
	}
