
package ch.hearc.gui.dessin.actions.dessin;

import java.awt.Color;
import java.util.List;
import java.util.ListIterator;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.utils.SprayShape;

public class DGommageNumerique extends DGommage implements Dessin_I
	{

	public DGommageNumerique(int taille, int nbGouttes, int dimension, SprayShape shape, int rayon)
		{
		super(taille, nbGouttes, dimension, shape);
		this.rayon = rayon;
		}

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{
		List<Sprayage> sprays = calque.getSprays();
		ListIterator<Sprayage> it = sprays.listIterator(sprays.size());

		while(it.hasPrevious())
			{
			Sprayage spray = it.previous();

			boolean changed = spray.removePointsInCircle(x, y, rayon);

			if (spray.isEmpty())
				{
				it.remove();
				}

			if (changed && strokeList != null)
				{
				strokeList.add(spray);
				}
			}
		}

	@Override
	public Color getColor()
		{
		return Color.WHITE;
		}

	private int rayon;
	}
