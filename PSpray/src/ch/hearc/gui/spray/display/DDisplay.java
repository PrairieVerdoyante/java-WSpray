
package ch.hearc.gui.spray.display;

import java.awt.Color;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.undoRedo.UndoRedo;
import ch.hearc.gui.dessin.composition.DComposition;
import ch.hearc.gui.utils.SprayShape;

public class DDisplay
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/
	public DDisplay()
		{
		// reset();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/
	/*
	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		this.dComposition.draw(g2d, currentDimension, currentDimension);
		}

	@Override
	public void setColor(Color color)
		{
		this.currentColor = color;
		}

	@Override
	public void setNbGouttes(int nbGouttes)
		{
		this.currentNbGouttes = nbGouttes;
		}

	@Override
	public void setTaille(int taille)
		{
		this.currentTaille = taille;
		}

	@Override
	public void setDimension(int dimension)
		{
		this.currentDimension = dimension;
		}

	@Override
	public void setForme(SprayShape shape)
		{
		this.currentShape = shape;
		}

	@Override
	public void addCalque(String name)
		{
		DCalque calque = new DCalque(name);
		this.calques.add(calque);
		this.currentCalque = calque;
		this.undoRedo = new UndoRedo(currentCalque);
		}

	@Override
	public void addCalque(String name, List<Sprayage> sprays)
		{
		DCalque newCalque = new DCalque(name);
		newCalque.getSprays().addAll(sprays);
		this.calques.add(newCalque);
		repaint();
		}

	@Override
	public void removeLastCalque()
		{
		if (calques.size() > 1)
			{
			calques.remove(calques.size() - 1);
			currentCalque = calques.get(calques.size() - 1);
			repaint();
			}
		}

	@Override
	public void setCalqueVisible(String name, boolean visible)
		{
		for(DCalque calque:calques)
			{
			if (calque.getName().equals(name))
				{
				calque.setVisible(visible);
				repaint();
				break;
				}
			}
		}

	/**
	 * Restores JDisplay to default parameters.
	 */
//	@Override
//	public void reset()
//		{
//		this.currentColor = Color.BLACK;
//		this.currentNbGouttes = 70;
//		this.currentDimension = 10;
//		this.currentShape = SprayShape.CERCLE;
//		this.currentTaille = 10;
//
//		this.calques = new ArrayList<>();
//		this.dComposition = new DComposition(this);
//		this.dComposition.setCalques(this.calques);
//
//		addCalque("Calque 1");
//
//		if (!this.calques.isEmpty())
//			{
//			this.currentCalque = this.calques.get(this.calques.size() - 1);
//			}
//		else
//			{
//			this.currentCalque = null;
//			}
//
//		// reset calques
//		if (jCalques != null)
//			{
//			jCalques.resetCalques();
//
//			repaint();
//			}
//		}

	/*------------------------------*\
	|*			Get	/ set			*|
	\*------------------------------*/
	//
	//	@Override
	//	public void setCalques(List<DCalque> calques)
	//		{
	//		this.calques = calques != null ? calques : new ArrayList<>();
	//		this.dComposition.setCalques(this.calques);
	//
	//		// Always reset currentCalque to a valid one if possible
	//		if (!this.calques.isEmpty())
	//			{
	//			this.currentCalque = this.calques.get(this.calques.size() - 1);
	//			}
	//		else
	//			{
	//			this.currentCalque = null;
	//			}
	//
	//		repaint();
	//		}
	//
	//	@Override
	//	public List<DCalque> getCalques()
	//		{
	//		return this.calques;
	//		}
	//
	//	@Override
	//	public List<DCalque> getSelectedCalques()
	//		{
	//		// Pas encore
	//		return null;
	//		}
	//
	//	@Override
	//	public JCalques getJCalques()
	//		{
	//		return this.jCalques;
	//		}
	//
	//	@Override
	//	public UndoRedo getHistory()
	//		{
	//		return this.undoRedo;
	//		}
	//		*/
	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributes Private						*|
	\*------------------------------------------------------------------*/
	private List<DCalque> calques;
	private DCalque currentCalque;
	private DComposition dComposition;
	private Color currentColor;
	private int currentNbGouttes;
	private int currentTaille;
	private int currentDimension;
	private SprayShape currentShape;
	private List<Sprayage> currentStroke;
	private UndoRedo undoRedo;

	}
