
package ch.hearc.gui.dessin.actions.UndoRedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.actions.dessin.Geste;

public class UndoRedo
	{

	/*------------------------------------------------------------------*\
	|*							Constructor								*|
	\*------------------------------------------------------------------*/

	public UndoRedo(DCalque calque)
		{
		this.calque = calque;
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
		}

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
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes private							*|
	\*------------------------------------------------------------------*/
	private Stack<List<Geste>> undoStack;
	private Stack<List<Geste>> redoStack;
	private DCalque calque;
	}
