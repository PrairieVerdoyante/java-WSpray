
package ch.hearc.gui.display;

import java.awt.Color;
import java.util.List;

import ch.hearc.gui.utils.SprayShape;

public interface Display_I
	{

	public void setColor(Color color);

	public void setNbGouttes(int nbGouttes);

	public void setDimension(int dimension);

	public void setTaille(int taille);

	public void setForme(SprayShape shape);

	public List<String> getSelectedCalques();

	public void addCalque(String name);

	public void removeLastCalque();

	public void setCalqueVisible(String name, boolean visible);

	}
