
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
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JDisplay() throws IOException

		{
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

	@Override
	public List<DCalque> getSelectedCalques()
		{
		// Pas encore
		return null;
		}

	@Override
	public JCalques getJCalques()
		{
		return this.jCalques;
		}

	@Override
	public UndoRedo getHistory()
		{
		return this.undoRedo;
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
				currentStroke = new ArrayList<>();
				addSprayAtAsync(e.getX(), e.getY(), false);
				}

			@Override
			public void mouseReleased(MouseEvent e)
				{
				undoRedo.addActions(currentStroke);
				//				try
				//					{
				//					for(Sprayage spray:currentStroke)
				//						{
				//						SprayStorage.append(spray, "sprays.bin");
				//						}
				//					}
				//				catch (IOException ex)
				//					{
				//					ex.printStackTrace();
				//					}
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

	/**
	 * Adds a spray to the selected calque.
	 * @param x coordinate of the spray position
	 * @param y coordinate of the spray position
	 */
	private void addSprayAt(int x, int y, boolean logImmediately)
		{

		if (currentCalque == null)
			{
			// Afficher un message d’erreur ou logger
			System.err.println("Aucun calque sélectionné !");
			return;
			}
		Runnable runnable = new Runnable()
			{

			@Override
			public void run()
				{
				// Création du spray (thread secondaire, pas de problème)
				DSprayage spray = new DSprayage(currentColor, currentTaille, currentNbGouttes, currentDimension, currentShape);

				// Ajout du spray dans le calque et dans l'UI
				javax.swing.SwingUtilities.invokeLater(new Runnable()
					{

					@Override
					public void run()
						{
						spray.apply(x, y, currentCalque, currentStroke);
						repaint();
						}
					});

				}
			};
		}

	//
	//			if (logImmediately)
	//				{
	//				try
	//					{
	//					SprayStorage.append(spray, "sprays.bin");
	//					}
	//				catch (IOException e)
	//					{
	//					// Afficher un message d’erreur ou logger
	//					System.err.println("Erreur lors de la sauvegarde du spray : " + e.getMessage());
	//					}
	//				}

	//			if (currentStroke != null)
	//				{
	//				currentStroke.add(spray);
	//				}

	private void addSprayAtAsync(int x, int y, boolean logImmediately)
		{
		Runnable runnable = new Runnable()
			{

			@Override
			public void run()
				{
				// Création du spray (thread secondaire)
				DSprayage spray = new DSprayage(currentColor, currentDimension, currentNbGouttes, currentDimension, currentShape);

				// Ajout du spray dans le calque et dans l'UI
				javax.swing.SwingUtilities.invokeLater(new Runnable()
					{

					@Override
					public void run()
						{
						spray.apply(x, y, currentCalque, currentStroke);
						repaint();
						}
					});

				//				// Sauvegarde sur disque (thread secondaire, OK)
				//				if (logImmediately)
				//					{
				//					try
				//						{
				//						SprayStorage.append(spray, "sprays.bin");
				//						}
				//					catch (IOException e)
				//						{
				//						System.err.println("Erreur lors de la sauvegarde du spray : " + e.getMessage());
				//						}
				//					}
				}
			};

		new Thread(runnable).start();
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

	// undo/redo
	private List<Sprayage> currentStroke;
	private UndoRedo undoRedo;

	}
