
package ch.hearc.gui.dessin.calques;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ch.hearc.gui.utils.Spray;

public class DCalque implements DCalque_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DCalque(String name)
		{
		this.name = name;
		this.visible = true;
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void addSpray(Spray spray)
		{
		sprays.add(spray);
		}

	@Override
	public void draw(Graphics2D g2d)
		{
		if (visible)
			{
			for(Spray spray:sprays)
				{
				spray.draw(g2d);
				}
			}
		}

	@Override
	public void setVisible(boolean visible)
		{
		this.visible = visible;
		}

	@Override
	public boolean isVisible()
		{
		return visible;
		}

	@Override
	public String getName()
		{
		return name;
		}

	@Override
	public void draw(Graphics2D g2d, int arg1, int arg2)
		{
		this.draw(g2d);
		}

	@Override
	public void animationStep()
		{
		// TODO Auto-generated method stub

		}

	/*------------------------------------------------------------------*\
	|*						Attributs Private							*|
	\*------------------------------------------------------------------*/

	private final List<Spray> sprays = new ArrayList<>();
	private boolean visible = true;
	private final String name;

	}
