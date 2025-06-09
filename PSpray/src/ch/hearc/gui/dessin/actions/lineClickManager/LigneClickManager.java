
package ch.hearc.gui.dessin.actions.lineClickManager;

import java.util.List;

import ch.hearc.gui.dessin.actions.dessin.geste.Geste;
import ch.hearc.gui.dessin.actions.dessin.gommage.DGommageLigne;
import ch.hearc.gui.dessin.actions.undoRedo.UndoRedo;
import ch.hearc.gui.spray.display.JDisplay;
import ch.hearc.gui.utils.SprayShape;

public class LigneClickManager
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public LigneClickManager(JDisplay display)
		{
		this.display = display;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public void handleClick(int x, int y)
		{
		if (!waitingForSecondClick)
			{
			x1 = x;
			y1 = y;
			waitingForSecondClick = true;
			}
		else
			{
			int x2 = x;
			int y2 = y;

			display.getCurrentShape();
			DGommageLigne gommeLigne = new DGommageLigne(display.getCurrentTaille(), display.getCurrentNbGouttes(), display.getCurrentDimension(), SprayShape.CARRE, display.getLigneEpaisseur());

			gommeLigne.apply(x1, y1,x2, y2, display.getCurrentCalque(), null);

			List<Geste> currentStroke = display.getCurrentStroke();
			if (currentStroke != null)
				{
				currentStroke.clear();
				currentStroke.add(gommeLigne);
				}

			UndoRedo undoRedo = display.getUndoRedo();
			if (undoRedo != null)
				{
				undoRedo.addActions(List.of(gommeLigne));
				}

			display.repaint();
			waitingForSecondClick = false;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	private boolean waitingForSecondClick = false;
	private int x1, y1;

	private final JDisplay display;

	}
