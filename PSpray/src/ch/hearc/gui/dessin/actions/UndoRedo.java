
package ch.hearc.gui.dessin.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.hearc.gui.dessin.calques.DCalque;
import ch.hearc.gui.utils.Spray;

public class UndoRedo
	{

	private Stack<List<Spray>> undoStack;
	private Stack<List<Spray>> redoStack;
	private DCalque calque;

	public UndoRedo(DCalque calque)
		{
		this.calque = calque;
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
		}

	public void addActions(List<Spray> sprays)
		{
		undoStack.push(new ArrayList<>(sprays));
		redoStack.clear();
		}

	public void undo()
		{
		if (!undoStack.isEmpty())
			{
			List<Spray> actions = undoStack.pop();
			for(Spray spray:actions)
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
			List<Spray> actions = redoStack.pop();
			for(Spray spray:actions)
				{
				calque.addSpray(spray);
				}
			undoStack.push(actions);
			}
		}
	}
