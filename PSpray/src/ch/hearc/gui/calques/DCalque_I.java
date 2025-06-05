
package ch.hearc.gui.calques;

import java.awt.Graphics2D;
import java.util.List;

import ch.hearc.c_gui.z_tools.DessinAnimable;
import ch.hearc.gui.utils.DSpray;

public interface DCalque_I extends DessinAnimable
	{
	public void setVisible(boolean visible);

	public boolean isVisible();

	public String getName();

	public void draw(Graphics2D g2d);

	public void addSpray(DSpray spray);

	public void removeSpray(DSpray Spray);

	public List<DSpray> getSprays();

	}
