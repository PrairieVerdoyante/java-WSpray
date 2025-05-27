
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
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void draw(Graphics2D g2d, int x, int y)
		{
		for(DCalque calque:calques)
			{
			calque.draw(g2d);
			}
		}

	@Override
	public void animationStep()
		{
		for(DCalque calque:calques)
			{
			calque.animationStep();
			}

		}

	public void setCalques(List<DCalque> calques)
		{
		this.calques = calques;
		}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	//inputs
	private JComponent jComponent;
	//tools
	private List<DCalque> calques;

	}
