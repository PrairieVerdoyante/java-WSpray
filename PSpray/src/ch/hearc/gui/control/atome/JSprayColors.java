
package ch.hearc.gui.control.atome;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class JSprayColors extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JSprayColors()
		{
		super(BoxLayout.Y_AXIS);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// Panel pour disposer en grille (ajouté un nombre de colonnes et lignes optimisé)
		JPanel panelColors = new JPanel(new GridLayout(2, 6, 5, 5)); // 2 lignes, 6 colonnes, marges de 5px

		// Création des boutons avec des couleurs supplémentaires
		this.buttonRouge = createColorButton(Color.RED);
		this.buttonVert = createColorButton(Color.GREEN);
		this.buttonBleu = createColorButton(Color.BLUE);
		this.buttonNoir = createColorButton(Color.BLACK);
		this.buttonJaune = createColorButton(Color.YELLOW);
		this.buttonCyan = createColorButton(Color.CYAN);
		this.buttonMagenta = createColorButton(Color.MAGENTA);
		this.buttonOrange = createColorButton(Color.ORANGE);
		this.buttonRose = createColorButton(Color.PINK);
		this.buttonBlanc = createColorButton(Color.WHITE);
		this.buttonPersonnalisee = createColorButton(Color.GRAY);

		// Groupement exclusif
		ButtonGroup groupColor = new ButtonGroup();
		groupColor.add(buttonRouge);
		groupColor.add(buttonVert);
		groupColor.add(buttonBleu);
		groupColor.add(buttonNoir);
		groupColor.add(buttonJaune);
		groupColor.add(buttonCyan);
		groupColor.add(buttonMagenta);
		groupColor.add(buttonOrange);
		groupColor.add(buttonRose);
		groupColor.add(buttonBlanc);
		groupColor.add(buttonPersonnalisee);

		// Ajout dans la grille
		panelColors.add(buttonRouge);
		panelColors.add(buttonVert);
		panelColors.add(buttonBleu);
		panelColors.add(buttonNoir);
		panelColors.add(buttonJaune);
		panelColors.add(buttonCyan);
		panelColors.add(buttonMagenta);
		panelColors.add(buttonOrange);
		panelColors.add(buttonRose);
		panelColors.add(buttonBlanc);
		panelColors.add(buttonPersonnalisee);

		// Ajout du panel au composant principal
		this.add(panelColors);
		}

	private JToggleButton createColorButton(Color color)
		{
		JToggleButton button = new JToggleButton();
		button.setBackground(color);
		button.setPreferredSize(new Dimension(40, 40));
		button.setMaximumSize(new Dimension(40, 40));
		button.setMinimumSize(new Dimension(40, 40));
		button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		button.setFocusPainted(false);
		return button;
		}

	private void control()
		{
		ActionListener listener = new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				if (e.getSource() == buttonPersonnalisee)
					{
					Color couleur = JColorChooser.showDialog(null, "Choisissez votre couleur", Color.WHITE);
					if (couleur != null)
						{
						couleurPersonnalisee = couleur;
						buttonPersonnalisee.setBackground(couleur);
						System.out.println("Couleur personnalisée choisie : " + couleur);
						}
					}
				else
					{
					System.out.println("Couleur standard sélectionnée");
					}
				}
			};

		buttonRouge.addActionListener(listener);
		buttonVert.addActionListener(listener);
		buttonBleu.addActionListener(listener);
		buttonNoir.addActionListener(listener);
		buttonJaune.addActionListener(listener);
		buttonCyan.addActionListener(listener);
		buttonMagenta.addActionListener(listener);
		buttonOrange.addActionListener(listener);
		buttonRose.addActionListener(listener);
		buttonBlanc.addActionListener(listener);
		buttonPersonnalisee.addActionListener(listener);
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder("Couleur du Spray"));

		// debug
		setOpaque(true);
		setBackground(Color.RED);
		}

	/*------------------------------------------------------------------*\
	|*							Public Method							*|
	\*------------------------------------------------------------------*/

	public Color getSelectedColor()
		{
		if (buttonRouge.isSelected())
			{ return Color.RED; }
		if (buttonVert.isSelected())
			{ return Color.GREEN; }
		if (buttonBleu.isSelected())
			{ return Color.BLUE; }
		if (buttonNoir.isSelected())
			{ return Color.BLACK; }
		if (buttonJaune.isSelected())
			{ return Color.YELLOW; }
		if (buttonCyan.isSelected())
			{ return Color.CYAN; }
		if (buttonMagenta.isSelected())
			{ return Color.MAGENTA; }
		if (buttonOrange.isSelected())
			{ return Color.ORANGE; }
		if (buttonRose.isSelected())
			{ return Color.PINK; }
		if (buttonBlanc.isSelected())
			{ return Color.WHITE; }
		if (buttonPersonnalisee.isSelected() && couleurPersonnalisee != null)
			{ return couleurPersonnalisee; }

		return Color.BLACK; // défaut
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JToggleButton buttonRouge;
	private JToggleButton buttonVert;
	private JToggleButton buttonBleu;
	private JToggleButton buttonNoir;
	private JToggleButton buttonJaune;
	private JToggleButton buttonCyan;
	private JToggleButton buttonMagenta;
	private JToggleButton buttonOrange;
	private JToggleButton buttonRose;
	private JToggleButton buttonBlanc;
	private JToggleButton buttonPersonnalisee;
	private Color couleurPersonnalisee;
	}
