
package ch.hearc.gui.spray.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.calques.JCalques;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.dessin.ModeGomme;
import ch.hearc.gui.dessin.actions.dessin.geste.Geste;
import ch.hearc.gui.dessin.actions.dessin.gommage.DGommageCercle;
import ch.hearc.gui.dessin.actions.dessin.gommage.DGommageNumerique;
import ch.hearc.gui.dessin.actions.dessin.gommage.DGommageSprayage;
import ch.hearc.gui.dessin.actions.dessin.sprayage.DSprayage;
import ch.hearc.gui.dessin.actions.lineClickManager.LigneClickManager;
import ch.hearc.gui.dessin.actions.undoRedo.UndoRedo;
import ch.hearc.gui.dessin.composition.DComposition;
import ch.hearc.gui.export.SprayStorage;
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
		initBuffer();
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

		if (bufferImage != null)
			{
			g.drawImage(bufferImage, 0, 0, null);
			}

		// Dessiner la prévisualisation (curseur)
		Graphics2D g2d = (Graphics2D)g;
		if (mouseX >= 0 && mouseY >= 0)
			{
			g2d.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 128)); // semi-transparence
			int size = (modeGomme == ModeGomme.GOMMECERCLE || modeGomme == ModeGomme.NUMERIQUE) ? rayonGomme * 2 + currentTaille : currentDimension * 2 + currentTaille;

			switch(currentShape)
				{
				case CERCLE:
					g2d.drawOval(mouseX - size / 2, mouseY - size / 2, size, size);
					break;
				case CARRE:
					g2d.drawRect(mouseX - size / 2, mouseY - size / 2, size, size);
					break;
				default:
					g2d.drawOval(mouseX - size / 2, mouseY - size / 2, size, size);
					break;
				}
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
				isDrawing = true; // → On commence à dessiner
				currentStroke = new ArrayList<>();
				addSprayAtAsync(e.getX(), e.getY(), false);

				if (modeGomme == ModeGomme.GOMMELIGNE)
					{
					ligneClickManager.handleClick(e.getX(), e.getY());
					}
				else
					{
					addSprayAtAsync(e.getX(), e.getY(), false);
					}
				updateBuffer();
				repaint();
				}

			@Override
			public void mouseReleased(MouseEvent e)
				{
				isDrawing = false; // → On arrête de dessiner
				if (undoRedo != null && currentStroke != null)
					{
					undoRedo.addActions(currentStroke);
					}
				}

			@Override
			public void mouseExited(MouseEvent e)
				{
				mouseX = -1;
				mouseY = -1;
				repaint();
				}
			});

		addMouseMotionListener(new MouseAdapter()
			{

			@Override
			public void mouseDragged(MouseEvent e)
				{
				mouseX = e.getX(); // → On continue de suivre la souris pendant le dessin
				mouseY = e.getY();
				addSprayAtAsync(e.getX(), e.getY(), false);
				repaint();
				}

			@Override
			public void mouseMoved(MouseEvent e)
				{
				mouseX = e.getX();
				mouseY = e.getY();
				repaint();
				}
			});

		}

	private void appearance()
		{
		setBackground(Color.WHITE);
		}

	/**
	 * Initialise le tampon bufferImage en fonction de la taille actuelle du JPanel.
	 */
	private void initBuffer()
		{
		int w = getWidth();
		int h = getHeight();
		if (w <= 0 || h <= 0)
			{
			return; // éviter erreurs si taille non définie
			}

		bufferImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		updateBuffer();
		}

	/**
	 * Met à jour le contenu du tampon bufferImage en redessinant la composition.
	 */
	private void updateBuffer()
		{
		if (bufferImage == null)
			{ return; }

		Graphics2D g2 = bufferImage.createGraphics();

		// Activation de l'antialiasing
		g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

		// Effacer l'ancien contenu avec fond blanc
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, bufferImage.getWidth(), bufferImage.getHeight());

		// Dessiner la composition
		if (dComposition != null)
			{
			dComposition.draw(g2, currentDimension, currentDimension);
			}

		g2.dispose();
		}

	private void addSprayAtAsync(int x, int y, boolean logImmediately)
		{
		switch(modeGomme)
			{
			case SPRAYAGE:
				DGommageSprayage gommeGeste = new DGommageSprayage(currentTaille, currentNbGouttes, currentDimension, currentShape);
				gommeGeste.apply(x, y, currentCalque, null);
				currentStroke.add(gommeGeste);
				break;
			case GOMMECERCLE:
				DGommageCercle gommeCercle = new DGommageCercle(currentTaille, currentNbGouttes, currentDimension, currentShape, rayonGomme);
				gommeCercle.apply(x, y, currentCalque, null);
				currentStroke.add(gommeCercle);
				break;
			case GOMMELIGNE:
				//DGommageLigne gommeLigne = new DGommageLigne(currentTaille, currentNbGouttes, 0,0, currentDimension, currentShape, ligneEpaisseur);
				//gommeLigne.apply(x, y, currentCalque, null);
				//currentStroke.add(gommeLigne);
				break;
			case NUMERIQUE:
				DGommageNumerique gommeNum = new DGommageNumerique(currentTaille, currentNbGouttes, currentDimension, currentShape, rayonGomme);
				gommeNum.apply(x, y, currentCalque, null);
				currentStroke.add(gommeNum);
				break;
			default: // mode sprayage
				DSprayage sprayGeste = new DSprayage(currentColor, currentTaille, currentNbGouttes, currentDimension, currentShape);

				sprayGeste.apply(x, y, currentCalque, null);
				currentStroke.add(sprayGeste);

				break;
			}

		// Mise à jour du buffer après modification
		updateBuffer();
		repaint();
		}

	/**
	 * Override pour gérer le redimensionnement et recréer le buffer.
	 */
	@Override
	public void invalidate()
		{
		super.invalidate();
		initBuffer();
		}

	@Override
	public void refreshDisplay()
		{
		updateBuffer();
		repaint();
		}

	/*------------------------------------------------------------------*\
	|*						JSprayConfigurable_I Impl					*|
	\*------------------------------------------------------------------*/

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

		// gomme
		this.modeGomme = ModeGomme.NONE; // spray par defaut
		this.rayonGomme = this.currentDimension;
		this.ligneClickManager = new LigneClickManager(this);

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

		initBuffer();
		repaint();
		}

	@Override
	public void addCalque(String name)
		{
		DCalque calque = new DCalque(name);
		this.calques.add(calque);
		this.currentCalque = calque;
		this.undoRedo = new UndoRedo(currentCalque, this);
		initBuffer();
		repaint();
		}

	@Override
	public void addCalque(String name, List<Sprayage> sprays)
		{
		DCalque newCalque = new DCalque(name);
		newCalque.getSprays().addAll(sprays);
		this.calques.add(newCalque);
		this.currentCalque = newCalque;
		initBuffer();
		repaint();
		}

	@Override
	public void removeLastCalque()
		{
		if (calques.size() > 1)
			{
			calques.remove(calques.size() - 1);
			currentCalque = calques.get(calques.size() - 1);
			initBuffer();
			repaint();
			}
		}

	@Override
	public void removeCurrentCalque()
		{
		if (currentCalque != null && calques.size() > 1)
			{
			calques.remove(currentCalque);

			// Sélectionner un autre calque (le précédent par exemple)
			currentCalque = calques.get(calques.size() - 1);

			if (jCalques != null)
				{
				jCalques.resetCalques();
				}
			refreshDisplay();
			repaint();
			}
		}

	@Override
	public void removeCalque(DCalque calque)
		{
		if (calque == null || calques.size() <= 1 || !calques.contains(calque))
			{ return; }

		boolean wasCurrent = (currentCalque == calque);

		// Trouver l'index du calque à supprimer
		int removedIndex = calques.indexOf(calque);

		// Supprimer le calque
		calques.remove(calque);

		if (wasCurrent)
			{
			if (!calques.isEmpty())
				{
				// Choisir calque précédent si possible, sinon le premier calque
				int newIndex = Math.max(removedIndex - 1, 0);
				currentCalque = calques.get(newIndex);
				}
			else
				{
				currentCalque = null;
				}
			}

		// Mettre à jour undoRedo
		if (currentCalque != null)
			{
			undoRedo = new UndoRedo(currentCalque, this);
			}
		else
			{
			undoRedo = null;
			}

		if (jCalques != null)
			{
			jCalques.resetCalques();
			}

		initBuffer();
		repaint();
		}

	/*----------------------------------*\
	|*				Get/set				*|
	\*----------------------------------*/

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
	public void setCalqueVisible(String name, boolean visible)
		{
		for(DCalque calque:calques)
			{
			if (calque.getName().equals(name))
				{
				calque.setVisible(visible);
				initBuffer();
				repaint();
				break;
				}
			}
		}

	@Override
	public void setCalques(List<DCalque> calques)
		{
		this.calques = calques != null ? calques : new ArrayList<>();
		this.dComposition.setCalques(this.calques);
		this.currentCalque = !this.calques.isEmpty() ? this.calques.get(this.calques.size() - 1) : null;
		initBuffer();
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
	public void previewCurrentCalque()
		{
		if (!hasClicked)
			{
			for(DCalque calque:this.calques)
				{
				if (!calque.getName().equals(this.currentCalque.getName()))
					{
					calque.setVisible(false);
					}
				}
			hasClicked = true;
			}
		else
			{
			for(DCalque calque:this.calques)
				{
				if (!calque.getName().equals(this.currentCalque.getName()))
					{
					calque.setVisible(true);
					}
				hasClicked = false;
				}
			}

		updateBuffer();
		repaint();
		}

	@Override
	public DCalque getCurrentCalque()
		{
		return this.currentCalque;
		}

	@Override
	public void setCurrentCalque(DCalque currentCalque)
		{
		this.currentCalque = currentCalque;
		}

	@Override
	public UndoRedo getUndoRedo()
		{
		return this.undoRedo;
		}

	@Override
	public void setCurrentStroke(List<Geste> sprays)
		{
		this.currentStroke = sprays;
		}

	@Override
	public List<Geste> getCurrentStroke()
		{
		return this.currentStroke;
		}

	@Override
	public ModeGomme getModeGomme()
		{
		return this.modeGomme;
		}

	@Override
	public void setModeGomme(ModeGomme mode)
		{
		this.modeGomme = mode;
		}

	@Override
	public void setGommeRayon(int rayon)
		{
		this.rayonGomme = rayon;
		}

	@Override
	public void setLigneEpaisseur(int epaisseur)
		{
		this.ligneEpaisseur = epaisseur;
		}

	@Override
	public int getCurrentNbGouttes()
		{
		return this.currentNbGouttes;
		}

	@Override
	public int getCurrentTaille()
		{
		return this.currentTaille;
		}

	@Override
	public int getCurrentDimension()
		{
		return this.currentDimension;
		}

	@Override
	public SprayShape getCurrentShape()
		{
		return this.currentShape;
		}

	@Override
	public int getLigneEpaisseur()
		{
		return this.ligneEpaisseur;
		}

	@SuppressWarnings("unchecked") // sauvegarde tous les calques
	@Override
	public void save(String fileName) throws IOException
		{

		SprayStorage.fileSave(fileName, this.calques); // Only save the data
		System.out.println("Saved successfully to: " + fileName);

		}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String fileName) throws ClassNotFoundException, IOException
		{
		Object result = SprayStorage.fileLoad(fileName);

		if (result instanceof List<?>)
			{
			List<?> list = (List<?>)result;

			if (!list.isEmpty() && list.get(0) instanceof DCalque)
				{
				List<DCalque> calques = (List<DCalque>)list;
				this.setCalques(calques);
				this.jCalques.resetCalques();
				}
			else
				{
				System.err.println("Les éléments de la liste ne sont pas des DCalque.");
				}
			}
		else
			{
			System.err.println("Le fichier ne contient pas une List.");
			}
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

	private List<Geste> currentStroke;
	private UndoRedo undoRedo;

	private ModeGomme modeGomme;
	private int rayonGomme;
	private int ligneEpaisseur;
	private LigneClickManager ligneClickManager;

	private int mouseX = -1;
	private int mouseY = -1;
	private boolean isDrawing = false;
	private static boolean hasClicked = false;

	private BufferedImage bufferImage;

	}
