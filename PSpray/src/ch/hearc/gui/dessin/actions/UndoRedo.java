
package ch.hearc.gui.dessin.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;

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

	public void addActions(List<Sprayage> sprays)
		{
		undoStack.push(new ArrayList<>(sprays));
		redoStack.clear();
		}

	public void undo()
		{
		if (!undoStack.isEmpty())
			{
			List<Sprayage> actions = undoStack.pop();
			for(Sprayage spray:actions)
				{
				calque.removeSpray(spray);
				}
			redoStack.push(actions);
			}
		}

	public void redo()
		{
		if (!redoStack.isEmpty())
			{
			List<Sprayage> actions = redoStack.pop();
			for(Sprayage spray:actions)
				{
				calque.addSpray(spray);
				}
			undoStack.push(actions);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes private							*|
	\*------------------------------------------------------------------*/
	private Stack<List<Sprayage>> undoStack;
	private Stack<List<Sprayage>> redoStack;
	private DCalque calque;
	}
