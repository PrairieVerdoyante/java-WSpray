
package ch.hearc.gui.dessin.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.hearc.gui.dessin.calques.DCalque;
import ch.hearc.gui.utils.DSpray;

public class UndoRedo
	{

	private Stack<List<DSpray>> undoStack;
	private Stack<List<DSpray>> redoStack;
	private DCalque calque;

	public UndoRedo(DCalque calque)
		{
		this.calque = calque;
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
		}

	public void addActions(List<DSpray> sprays)
		{
		undoStack.push(new ArrayList<>(sprays));
		redoStack.clear();
		}

	public void undo()
		{
		if (!undoStack.isEmpty())
			{
			List<DSpray> actions = undoStack.pop();
			for(DSpray spray:actions)
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
			List<DSpray> actions = redoStack.pop();
			for(DSpray spray:actions)
				{
				calque.addSpray(spray);
				}
			undoStack.push(actions);
			}
		}
	}
