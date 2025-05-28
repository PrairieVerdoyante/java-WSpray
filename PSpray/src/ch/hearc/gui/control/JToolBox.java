
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
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

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
		// Chargement des images comme icônes
		buttonSpray = createToolButton("spray.png"); // Image pour le spray
		buttonGomme = createToolButton("gomme.png"); // Image pour la gomme
		buttonSelectionner = createToolButton("selectionner.png"); // Image pour sélectionner
		buttonDeplacer = createToolButton("deplacer.png"); // Image pour déplacer

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
				}
			});

		buttonGomme.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.out.println("Gomme sélectionnée");
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
	}
