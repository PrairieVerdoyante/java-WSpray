
package ch.hearc.gui.dessin.actions.undoRedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.actions.dessin.geste.Geste;
import ch.hearc.gui.spray.display.JDisplay;

public class UndoRedo
	{

	/*------------------------------------------------------------------*\
	|*							Constructor								*|
	\*------------------------------------------------------------------*/

	public UndoRedo(DCalque calque, JDisplay jDisplay)
		{
		this.calque = calque;
		this.jDisplay = jDisplay;
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	public void addActions(List<Geste> sprays)
		{
		undoStack.push(new ArrayList<>(sprays));
		redoStack.clear();
		}

	public void undo()
		{
		if (!undoStack.isEmpty())
			{
			List<Geste> actions = undoStack.pop();
			for(Geste geste:actions)
				{
				geste.undo(calque);
				}
			redoStack.push(actions);

			// Mise à jour du buffer et repaint après undo
			if (jDisplay != null)
				{
				jDisplay.refreshDisplay();
				}
			}
		}

	public void redo()
		{
		if (!redoStack.isEmpty())
			{
			List<Geste> actions = redoStack.pop();
			for(Geste geste:actions)
				{
				geste.redo(calque);
				}
			undoStack.push(actions);

			// Mise à jour du buffer et repaint après redo
			if (jDisplay != null)
				{
				jDisplay.refreshDisplay();
				}
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes private							*|
	\*------------------------------------------------------------------*/
	private Stack<List<Geste>> undoStack;
	private Stack<List<Geste>> redoStack;
	private DCalque calque;
	private JDisplay jDisplay;
	}
