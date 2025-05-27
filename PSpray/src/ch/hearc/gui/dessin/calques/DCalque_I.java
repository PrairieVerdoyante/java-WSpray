
package ch.hearc.gui.dessin.calques;

import java.awt.Graphics2D;

import ch.hearc.c_gui.z_tools.DessinAnimable;
import ch.hearc.gui.utils.Spray;

public interface DCalque_I extends DessinAnimable
	{
	public void setVisible(boolean visible);

	public boolean isVisible();

	public String getName();

	public void draw(Graphics2D g2d);

	void addSpray(Spray spray);
	}
