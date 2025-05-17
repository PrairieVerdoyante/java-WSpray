
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
import ch.hearc.gui.utils.Spray;
import ch.hearc.gui.utils.SprayShape;

public class JDisplay extends JPanel implements Display_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private JDisplay()
		{
		this.calques = new ArrayList<>();
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

		for(DCalque calque:calques)
			{
			calque.draw(g2d);
			}
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
	public List<String> getSelectedCalques()
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

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static synchronized JDisplay getInstance()
		{
		if (instance == null)
			{
			instance = new JDisplay();
			}

		return instance;
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		addCalque("Calque 1");
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
	// Paramètres courants
	private Color currentColor = Color.BLACK;
	private int currentNbGouttes = 100;
	private int currentTaille = 10;
	private int currentDimension = 10;
	private SprayShape currentShape = SprayShape.CERCLE;
	}
