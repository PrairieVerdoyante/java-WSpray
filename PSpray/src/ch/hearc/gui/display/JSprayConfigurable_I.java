
package ch.hearc.gui.display;

import java.awt.Color;
import java.util.List;

import ch.hearc.gui.dessin.actions.UndoRedo;
import ch.hearc.gui.dessin.calques.DCalque;
import ch.hearc.gui.dessin.calques.JCalques;
import ch.hearc.gui.utils.DSpray;
import ch.hearc.gui.utils.SprayShape;

public interface JSprayConfigurable_I
	{

	public void setColor(Color color);

	public void setNbGouttes(int nbGouttes);

	public void setDimension(int dimension);

	public void setTaille(int taille);

	public void setForme(SprayShape shape);

	public void addCalque(String name);

	public void addCalque(String name, List<DSpray> sprays);

	public void removeLastCalque();

	public void setCalqueVisible(String name, boolean visible);

	public void setCalques(List<DCalque> newCalques);

	public List<DCalque> getCalques();

	public List<DCalque> getSelectedCalques();

	public void reset();

	public void setJCalques(JCalques jCalques);

	public JCalques getJCalques();

	public UndoRedo getHistory();
	}
