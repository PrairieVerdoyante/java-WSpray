
package ch.hearc.gui.display;

import java.awt.Color;
import java.util.List;

public interface Display_I
	{

	public void setBrushColor(Color color);

	public void setBackgroundColor(Color color);

	public void setCalques(int n);

	public List<String> getSelectedCalques();

	}
