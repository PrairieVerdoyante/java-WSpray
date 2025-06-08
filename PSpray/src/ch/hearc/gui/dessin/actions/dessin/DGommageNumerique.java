
package ch.hearc.gui.dessin.actions.dessin;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ch.hearc.c_gui.z_tools.Dessin_I;
import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.UndoRedo.ErasedPointsRecord;
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

			// Remove points inside circle and get removed points
			List<Point> erasedPoints = spray.removePointsInCircleWithReturn(x, y, rayon);

			if (!erasedPoints.isEmpty())
				{
				// Record erased points for undo/redo
				ErasedPointsRecord record = new ErasedPointsRecord(spray, erasedPoints);
				erasedPointsRecords.add(record);

				// Add spray to the current gesture (for redraw or tracking)
				addSpray(spray);

				if (strokeList != null)
					{
					strokeList.add(spray);
					}
				}

			// Remove spray if empty after erasing points
			if (spray.isEmpty())
				{
				it.remove();
				}
			}
		}

	@Override
	public void undo(DCalque calque)
		{
		// Restore erased points
		for(ErasedPointsRecord record:erasedPointsRecords)
			{
			record.spray().addPoints(record.erasedPoints());

			// make sure spray is in the calque.
			if (!calque.getSprays().contains(record.spray()))
				{
				calque.addSpray(record.spray());
				}
			}
		}

	@Override
	public void redo(DCalque calque)
		{
		// Remove points again
		for(ErasedPointsRecord record:erasedPointsRecords)
			{
			record.spray().removePoints(record.erasedPoints());

			// If spray became empty, remove it from calque
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
