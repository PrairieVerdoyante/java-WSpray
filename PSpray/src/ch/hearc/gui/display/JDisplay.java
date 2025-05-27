
package ch.hearc.gui.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hearc.gui.dessin.calques.DCalque;
import ch.hearc.gui.dessin.calques.JCalques;
import ch.hearc.gui.dessin.composition.DComposition;
import ch.hearc.gui.utils.Spray;
import ch.hearc.gui.utils.SprayShape;

public class JDisplay extends JPanel implements Display_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private JDisplay()

		{
		//		this.calques = new ArrayList<>();
		//		this.dComposition = new DComposition(this);
		//		this.dComposition.setCalques(this.calques);
		reset(); // sets the default parameters

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

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
	public List<DCalque> getSelectedCalques()
		{
		// Pas encore
		return null;
		}

	@Override
	public void addCalque(String name)
		{
		DCalque calque = new DCalque(name);
		this.calques.add(calque);
		this.currentCalque = calque;
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
	@Override
	public void reset()
		{
		this.currentColor = Color.BLACK;
		this.currentNbGouttes = 100;
		this.currentDimension = 10;
		this.currentShape = SprayShape.CERCLE;
		this.currentTaille = 10;

		this.calques = new ArrayList<>();
		this.dComposition = new DComposition(this);
		this.dComposition.setCalques(this.calques);

		addCalque("Calque 1");

		if (!this.calques.isEmpty())
			{
			this.currentCalque = this.calques.get(this.calques.size() - 1);
			}
		else
			{
			this.currentCalque = null;
			}

		// reset calques
		if (jCalques != null)
			{
			jCalques.resetCalques();

			repaint();
			}
		}

	@Override
	public void setCalques(List<DCalque> calques)
		{
		this.calques = calques != null ? calques : new ArrayList<>();
		this.dComposition.setCalques(this.calques);

		// Always reset currentCalque to a valid one if possible
		if (!this.calques.isEmpty())
			{
			this.currentCalque = this.calques.get(this.calques.size() - 1);
			}
		else
			{
			this.currentCalque = null;
			}

		repaint();
		}

	@Override
	public List<DCalque> getCalques()
		{
		return this.calques;
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static synchronized JDisplay getInstance()
		{
		if (instance == null)
			{
			try
				{
				instance = new JDisplay();
				}
			catch (Exception e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}

		return instance;
		}

	@Override
	public void setJCalques(JCalques jCalques)
		{
		this.jCalques = jCalques;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// rien
		}

	private void control()
		{
		addMouseListener(new MouseAdapter()
			{

			@Override
			public void mousePressed(MouseEvent e)
				{
				addSprayAt(e.getX(), e.getY());
				}
			});

		addMouseMotionListener(new MouseAdapter()
			{

			@Override
			public void mouseDragged(MouseEvent e)
				{
				addSprayAt(e.getX(), e.getY());
				}
			});
		}

	private void appearance()
		{
		setBackground(Color.WHITE);
		}

	/**
	 * Adds a spray to the selected calque.
	 * @param x coordinate of the spray position
	 * @param y coordinate of the spray position
	 */
	private void addSprayAt(int x, int y)
		{
		if (currentCalque != null)
			{
			Spray spray = new Spray(x, y, currentColor, currentNbGouttes, currentTaille, currentShape, currentDimension);
			currentCalque.addSpray(spray);
			repaint();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// inputs
	private static JDisplay instance = null;

	// tools
	private List<DCalque> calques;
	private DCalque currentCalque;
	private DComposition dComposition;
	private JCalques jCalques;
	// Paramètres courants
	private Color currentColor;
	private int currentNbGouttes;
	private int currentTaille;
	private int currentDimension;
	private SprayShape currentShape;

	}
