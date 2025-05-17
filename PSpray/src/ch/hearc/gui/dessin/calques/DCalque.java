
package ch.hearc.gui.dessin.calques;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ch.hearc.gui.utils.Spray;

public class DCalque
{
	public DCalque(String name)
	{
		this.name = name;
	}

	public void addSpray(Spray spray)
	{
		sprays.add(spray);
	}

	public void draw(Graphics2D g2d)
	{
		if (visible)
		{
			for (Spray spray : sprays)
			{
				spray.draw(g2d);
			}
		}
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public String getName()
	{
		return name;
	}

	private final List<Spray> sprays = new ArrayList<>();
	private boolean visible = true;
	private final String name;
}
