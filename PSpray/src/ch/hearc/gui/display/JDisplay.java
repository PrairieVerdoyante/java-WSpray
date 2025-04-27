
package ch.hearc.gui.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hearc.gui.utils.Spray;
import ch.hearc.gui.utils.SprayShape;

public class JDisplay extends JPanel implements Display_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private JDisplay()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

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
	public void setCalques(int n)
		{
		// Pas encore
		}

	@Override
	public List<String> getSelectedCalques()
		{
		// Pas encore
		return null;
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for(Spray spray:sprays)
			{
			spray.draw(g2d);
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
		this.sprays = new ArrayList<>();
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
		// Quand on clique : créer un nouveau spray à l'endroit cliqué
		Spray spray = new Spray(x, y, currentColor, currentNbGouttes, currentTaille, currentShape, currentDimension);
		sprays.add(spray);
		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static JDisplay instance = null;

	private List<Spray> sprays;

	// Paramètres courants
	private Color currentColor = Color.BLACK;
	private int currentNbGouttes = 100;
	private int currentTaille = 10;
	private int currentDimension = 10;
	private SprayShape currentShape = SprayShape.CERCLE;
	}
