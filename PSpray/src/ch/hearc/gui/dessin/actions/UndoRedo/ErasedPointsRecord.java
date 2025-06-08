
package ch.hearc.gui.dessin.actions.UndoRedo;

import java.awt.Point;
import java.util.List;

import ch.hearc.gui.dessin.Sprayage.Sprayage;

public record ErasedPointsRecord(Sprayage spray, List<Point> erasedPoints)
	{

	}
