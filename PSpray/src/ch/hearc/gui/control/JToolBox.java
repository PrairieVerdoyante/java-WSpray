
package ch.hearc.gui.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import ch.hearc.gui.dessin.actions.Sprayage.ModeGomme;
import ch.hearc.gui.spray.display.JDisplay;

public class JToolBox extends Box
	{

	/*------------------------------------------------------------------*\
	|*                           Constructors                           *|
	\*------------------------------------------------------------------*/

	public JToolBox()
		{
		super(BoxLayout.Y_AXIS);

		geometry();
		control();
		appearance();

		buttonSpray.setSelected(true); // init
		}

	/*------------------------------------------------------------------*\
	|*                           Methods Private                        *|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		gommeMenu = new JPopupMenu();
		gommageFormeMenu = new JPopupMenu();
		// Chargement des images comme icônes
		buttonSpray = new JToggleButton("Spray");//createToolButton("spray.png"); // Image pour le spray
		buttonGomme = new JToggleButton("Gomme");//createToolButton("gomme.png"); // Image pour la gomme
		//buttonGommeEcrasementSprayage = new JToggleButton("Effacer par sprayage");
		//buttonGommeEcrasementForme = new JToggleButton("Effacer par ecrasement de forme");
		//buttonGommeNumerique = new JToggleButton("Gomme numérique");

		buttonSelectionner = new JToggleButton("Create");//createToolButton("selectionner.png"); // Image pour sélectionner
		buttonDeplacer = new JToggleButton("Deplacer");//createToolButton("deplacer.png"); // Image pour déplacer

		itemSprayage = new JMenuItem("Ecrasement par Sprayage");
		itemForme = new JMenuItem("Écrasement par Forme");
		itemNumerique = new JMenuItem("Numérique");

		itemFormeCercle = new JMenuItem("Cercle");
		itemFormeLigne = new JMenuItem("Ligne");
		itemFormePolygone = new JMenuItem("Polygone");

		// choix de type de gommage
		gommeMenu.add(itemSprayage);
		gommeMenu.add(itemForme);
		gommeMenu.add(itemNumerique);

		// choix de la forme
		gommageFormeMenu.add(itemFormeCercle);
		gommageFormeMenu.add(itemFormeLigne);
		gommageFormeMenu.add(itemFormePolygone);

		// Groupe exclusif (un seul outil sélectionné à la fois)
		ButtonGroup groupTools = new ButtonGroup();
		groupTools.add(buttonSpray);
		groupTools.add(buttonGomme);

		groupTools.add(buttonSelectionner);
		groupTools.add(buttonDeplacer);

		// Ajout dans la colonne
		this.add(buttonSpray);
		this.add(buttonGomme);

		this.add(buttonSelectionner);
		this.add(buttonDeplacer);

		}

	private JToggleButton createToolButton(String imageName)
		{
		JToggleButton button = new JToggleButton();

		// Chargement de l'image
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/" + imageName));

		button.setIcon(icon); // Affectation de l'icône
		button.setPreferredSize(new Dimension(50, 50)); // Taille des boutons
		button.setMaximumSize(new Dimension(50, 50));
		button.setMinimumSize(new Dimension(50, 50));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		return button;
		}

	private void control()
		{
		buttonSpray.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.out.println("Spray sélectionné");
				JDisplay.getInstance().setModeGomme(ModeGomme.NONE);
				}
			});

		buttonGomme.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.out.println("Gomme sélectionnée");
				gommeMenu.show(buttonGomme, 0, buttonGomme.getHeight());
				}
			});

		buttonSelectionner.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.out.println("Sélectionner activé");

				}
			});

		buttonDeplacer.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.out.println("Déplacer activé");
				}
			});

		itemSprayage.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().setModeGomme(ModeGomme.SPRAYAGE);
				}
			});

		itemForme.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().setModeGomme(ModeGomme.FORME);
				// affichage menu
				gommageFormeMenu.show(buttonGomme, 0, buttonGomme.getHeight());
				}
			});

		itemNumerique.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().setModeGomme(ModeGomme.NUMERIQUE);
				}
			});

		itemFormeCercle.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().setModeGomme(ModeGomme.GOMMECERCLE);
				}
			});

		itemFormeLigne.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().setModeGomme(ModeGomme.GOMMELIGNE);
				}
			});

		itemFormePolygone.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().setModeGomme(ModeGomme.GOMMEPOLYGONE);
				}
			});
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Outils", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

		// debug
		setOpaque(true);
		setBackground(new Color(50, 50, 50, 255));
		}

	/*------------------------------------------------------------------*\
	|*                           Attributs Private                      *|
	\*------------------------------------------------------------------*/

	private JToggleButton buttonSpray;
	private JToggleButton buttonGomme;
	private JToggleButton buttonSelectionner;
	private JToggleButton buttonDeplacer;
	private JPopupMenu gommeMenu;
	private JMenuItem itemSprayage;
	private JMenuItem itemForme;
	private JMenuItem itemNumerique;
	private JPopupMenu gommageFormeMenu;
	private JMenuItem itemFormeCercle;
	private JMenuItem itemFormeLigne;
	private JMenuItem itemFormePolygone;
	}
