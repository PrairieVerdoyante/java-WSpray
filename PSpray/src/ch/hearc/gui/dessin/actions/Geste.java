
package ch.hearc.gui.dessin.actions;

import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.utils.DSpray;

public abstract class Geste
	{

	public abstract void apply(int x, int y, DCalque calque, List<DSpray> strokeList);
	}
