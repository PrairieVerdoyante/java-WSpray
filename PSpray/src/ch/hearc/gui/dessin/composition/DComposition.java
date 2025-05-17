
package ch.hearc.gui.dessin.composition;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import ch.hearc.c_gui.z_tools.DessinAnimable;
import ch.hearc.gui.dessin.calques.DCalque;

public class DComposition implements DessinAnimable
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/
	public DComposition(JComponent jComponent)
		{
		this.jComponent = jComponent;
		this.calques = new ArrayList<>();

		// Add initial calques here
		addCalque(new DCalque(jComponent));

		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/
	public void addCalque(DessinAnimable calque)
		{
		this.calques.add(calque);
		}

	public void removeCalque(DessinAnimable calque)
		{
		this.calques.remove(calque);
		}

	@Override
	public void draw(Graphics2D g2d, int w, int h)
		{
		for(DessinAnimable calque:calques)
			{
			calque.draw(g2d, w, h);
			}
		}

	@Override
	public void animationStep()
		{
		for(DessinAnimable calque:calques)
			{
			calque.animationStep();
			}
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	//inputs
	private JComponent jComponent;
	//tools
	private List<DessinAnimable> calques;
	}
