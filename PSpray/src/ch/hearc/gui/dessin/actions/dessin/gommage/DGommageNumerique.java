
package ch.hearc.gui.dessin.actions.dessin.gommage;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.undoRedo.ErasedPointsRecord;
import ch.hearc.gui.utils.SprayShape;

public class DGommageNumerique extends DGommage implements Dessin_I
	{

	public DGommageNumerique(int taille, int nbGouttes, int dimension, SprayShape shape, int rayon)
		{
		super(taille, nbGouttes, dimension, shape);
		this.rayon = rayon;
		this.erasedPointsRecords = new ArrayList<>();
		}

	@Override
	public void apply(int x, int y, DCalque calque, List<Sprayage> strokeList)
		{
		erasedPointsRecords.clear(); // clear previous records

		List<Sprayage> sprays = calque.getSprays();
		ListIterator<Sprayage> it = sprays.listIterator(sprays.size());

		while(it.hasPrevious())
			{
			Sprayage spray = it.previous();

			// enlever les points dans le cercle, les récupérer
			List<Point> erasedPoints = spray.removePointsInCircleWithReturn(x, y, rayon);

			if (!erasedPoints.isEmpty())
				{
				// pour undo/redo
				ErasedPointsRecord record = new ErasedPointsRecord(spray, erasedPoints);
				erasedPointsRecords.add(record);

				// Ajouter le spray au geste en cours
				addSpray(spray);

				if (strokeList != null)
					{
					strokeList.add(spray);
					}
				}

			// Enlever le spray s'il est vide
			if (spray.isEmpty())
				{
				it.remove();
				}
			}
		}

	@Override
	public void undo(DCalque calque)
		{
		// Restorer les points effacés
		for(ErasedPointsRecord record:erasedPointsRecords)
			{
			record.spray().addPoints(record.erasedPoints());

			// Le spray doit être dans le calque.
			if (!calque.getSprays().contains(record.spray()))
				{
				calque.addSpray(record.spray());
				}
			}
		}

	@Override
	public void redo(DCalque calque)
		{
		// Enlever les points
		for(ErasedPointsRecord record:erasedPointsRecords)
			{
			record.spray().removePoints(record.erasedPoints());

			if (record.spray().isEmpty())
				{
				calque.removeSpray(record.spray());
				}
			}
		}

	@Override
	public Color getColor()
		{
		return Color.WHITE;
		}

	private int rayon;
	private List<ErasedPointsRecord> erasedPointsRecords;
	}
