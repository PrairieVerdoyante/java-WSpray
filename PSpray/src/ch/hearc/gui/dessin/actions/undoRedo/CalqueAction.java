
package ch.hearc.gui.dessin.actions.undoRedo;

import java.util.ArrayList;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.actions.dessin.geste.Geste;

public class CalqueAction
	{

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/
//
//	public CalqueAction(DCalque calque/* Geste geste*/)
//		{
//		this.calque = calque;
//		//this.geste = geste;
//		}

	public CalqueAction(DCalque calque2, ArrayList<Geste> geste2)
		{
		// this.geste = new Geste();
		// TODO Auto-generated constructor stub
		this.gestes = new ArrayList<Geste>(geste2);
		this.calque = calque2;
		}

	public DCalque getCalque()
		{
		return calque;
		}

	public Geste getGeste()
		{
		return gestes.getLast();
		}
	public List<Geste> getGestes()
	{

	}

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/

	private final DCalque calque;
	// private final Geste geste; // or Sprayage, depending on your system
	private final List<Geste> gestes;
	}
