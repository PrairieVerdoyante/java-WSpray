
package ch.hearc.gui.calques;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ch.hearc.gui.dessin.Sprayage.Sprayage;

public class DCalque implements DCalque_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public DCalque(String name)
		{
		this.name = name;
		this.visible = true;
		this.sprays = new ArrayList<>();
		}

	/*------------------------------------------------------------------*\
	|*							Methods public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void draw(Graphics2D g2d)
		{
		if (visible)
			{
			for(Sprayage s:sprays)
				{
				s.draw(g2d, 0, 0);
				}
			}
		}

	@Override
	public void draw(Graphics2D g2d, int arg1, int arg2)
		{
		// Appelle la méthode principale de dessin
		draw(g2d);
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
	public void addSpray(Sprayage spray)
		{
		sprays.add(spray);
		}

	@Override
	public void removeSpray(Sprayage spray)
		{
		sprays.remove(spray);
		}

	@Override
	public List<Sprayage> getSprays()
		{
		return this.sprays;
		}

	@Override
	public void animationStep()
		{
		// Animation non implémentée pour l’instant
		}

	/*------------------------------------------------------------------*\
	|*						Attributs Private							*|
	\*------------------------------------------------------------------*/

	private boolean visible;
	private final String name;
	private final List<Sprayage> sprays;

	}
