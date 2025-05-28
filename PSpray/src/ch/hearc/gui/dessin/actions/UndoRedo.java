
package ch.hearc.gui.dessin.actions;

import java.util.Stack;

import ch.hearc.gui.dessin.calques.DCalque;
import ch.hearc.gui.utils.Spray;

public class UndoRedo
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public UndoRedo(DCalque currentCalque)
		{
		this.currentCalque = currentCalque;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	public void addAction(Spray spray)
		{
		undoStack.push(spray);
		redoStack.clear();
		}

	public void undo()
		{
		if (!undoStack.isEmpty() && currentCalque != null)
			{
			Spray spray = undoStack.pop();
			currentCalque.removeSpray(spray); // Implement in DCalque
			redoStack.push(spray);
			}
		}

	public void redo()
		{
		if (!redoStack.isEmpty() && currentCalque != null)
			{
			Spray spray = redoStack.pop();
			currentCalque.addSpray(spray);
			undoStack.push(spray);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	private final Stack<Spray> undoStack = new Stack<>();
	private final Stack<Spray> redoStack = new Stack<>();
	private final DCalque currentCalque;
	}
