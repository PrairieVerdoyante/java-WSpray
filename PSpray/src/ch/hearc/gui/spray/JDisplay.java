
package ch.hearc.gui.spray;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.calques.JCalques;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.DSprayage;
import ch.hearc.gui.dessin.actions.UndoRedo;
import ch.hearc.gui.dessin.composition.DComposition;
import ch.hearc.gui.utils.SprayShape;

public class JDisplay extends JPanel implements JSprayConfigurable_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/
	public JDisplay() throws IOException
		{
		reset(); // initialize parameters

		geometry();
		control();
		appearance();
		}

	public static synchronized JDisplay getInstance()
		{
		if (instance == null)
			{
			try
				{
				instance = new JDisplay();
				}
			catch (IOException e)
				{
				e.printStackTrace();
				}
			}
		return instance;
		}

	/*------------------------------------------------------------------*\
	|*							Painting								*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		if (dComposition != null)
			{
			dComposition.draw(g2d, currentDimension, currentDimension);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methods private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		}

	private void control()
		{
		addMouseListener(new MouseAdapter()
			{

			@Override
			public void mousePressed(MouseEvent e)
				{
				currentStroke = new ArrayList<>();
				addSprayAtAsync(e.getX(), e.getY(), false);
				}

			@Override
			public void mouseReleased(MouseEvent e)
				{
				if (undoRedo != null && currentStroke != null)
					{
					undoRedo.addActions(currentStroke);
					}
				}
			});

		addMouseMotionListener(new MouseAdapter()
			{

			@Override
			public void mouseDragged(MouseEvent e)
				{
				addSprayAtAsync(e.getX(), e.getY(), false);
				}
			});
		}

	private void appearance()
		{
		setBackground(Color.WHITE);
		}

	private void addSprayAtAsync(int x, int y, boolean logImmediately)
		{
		new Thread(() -> {
		DSprayage spray = new DSprayage(currentColor, currentTaille, currentNbGouttes, currentDimension, currentShape);
		javax.swing.SwingUtilities.invokeLater(() -> {
		spray.apply(x, y, currentCalque, currentStroke);
		repaint();
		});
		}).start();
		}

	/*------------------------------------------------------------------*\
	|*						JSprayConfigurable_I Impl					*|
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
	public void setDimension(int dimension)
		{
		this.currentDimension = dimension;
		}

	@Override
	public void setTaille(int taille)
		{
		this.currentTaille = taille;
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
		repaint();
		}

	@Override
	public void addCalque(String name, List<Sprayage> sprays)
		{
		DCalque newCalque = new DCalque(name);
		newCalque.getSprays().addAll(sprays);
		this.calques.add(newCalque);
		this.currentCalque = newCalque;
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

	@Override
	public void reset()
		{
		this.currentColor = Color.BLACK;
		this.currentNbGouttes = 70;
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

		if (jCalques != null)
			{
			jCalques.resetCalques();
			}

		repaint();
		}

	@Override
	public void setCalques(List<DCalque> calques)
		{
		this.calques = calques != null ? calques : new ArrayList<>();
		this.dComposition.setCalques(this.calques);
		this.currentCalque = !this.calques.isEmpty() ? this.calques.get(this.calques.size() - 1) : null;
		repaint();
		}

	@Override
	public List<DCalque> getCalques()
		{
		return this.calques;
		}

	@Override
	public List<DCalque> getSelectedCalques()
		{
		return null;
		}

	@Override
	public void setJCalques(JCalques jCalques)
		{
		this.jCalques = jCalques;
		}

	@Override
	public JCalques getJCalques()
		{
		return this.jCalques;
		}

	@Override
	public UndoRedo getUndoRedo()
		{
		return this.undoRedo;
		}

	@Override
	public void setCurrentStroke(List<Sprayage> sprays)
		{
		this.currentStroke = sprays;
		}

	@Override
	public List<Sprayage> getCurrentStroke()
		{
		return this.currentStroke;
		}

	/*------------------------------------------------------------------*\
	|*							Attributes								*|
	\*------------------------------------------------------------------*/

	private static JDisplay instance = null;

	private List<DCalque> calques;
	private DCalque currentCalque;
	private DComposition dComposition;
	private JCalques jCalques;

	private Color currentColor;
	private int currentNbGouttes;
	private int currentTaille;
	private int currentDimension;
	private SprayShape currentShape;

	private List<Sprayage> currentStroke;
	private UndoRedo undoRedo;

	}
