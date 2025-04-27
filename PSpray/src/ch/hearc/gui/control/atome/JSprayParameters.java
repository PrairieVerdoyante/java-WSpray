package ch.hearc.gui.control.atome;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSprayParameters extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JSprayParameters()
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
	    // Surface de sprayage
	    this.buttonCercle = createToolButton("cercle.png");
	    this.buttonRectangle = createToolButton("carre.png");

	    // Groupement exclusif des boutons
	    ButtonGroup groupForme = new ButtonGroup();
	    groupForme.add(buttonCercle);
	    groupForme.add(buttonRectangle);

	    // Panel pour aligner les boutons sur la même ligne
	    JPanel panelButtons = new JPanel();
	    panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
	    panelButtons.setOpaque(false); // Rendre le panel transparent
	    panelButtons.add(buttonCercle);
	    panelButtons.add(Box.createHorizontalStrut(10)); // Espacement horizontal de 10px
	    panelButtons.add(buttonRectangle);

	    // Label pour la forme de sprayage
	    JLabel labelForme = new JLabel("Forme de sprayage");
	    labelForme.setOpaque(false);
	    labelForme.setForeground(Color.WHITE);
	    this.add(labelForme);

	    // Ajout du panel contenant les boutons
	    this.add(panelButtons);

	    // Taille des gouttelettes
	    this.sliderTaille = new JSlider(1, 100, 10); // min 1, max 100, initial 10
	    sliderTaille.setOpaque(false);
	    JLabel labelTaille = new JLabel("Taille des gouttelettes");
	    labelTaille.setOpaque(false);
	    labelTaille.setForeground(Color.WHITE);
	    this.add(labelTaille);
	    this.add(sliderTaille);

	    // Nombre de gouttelettes
	    this.sliderNbGouttes = new JSlider(1, 500, 50); // min 1, max 500, initial 50
	    sliderNbGouttes.setOpaque(false);
	    JLabel labelNbGouttes = new JLabel("Nombre de gouttelettes");
	    labelNbGouttes.setOpaque(false);
	    labelNbGouttes.setForeground(Color.WHITE);
	    this.add(labelNbGouttes);
	    this.add(sliderNbGouttes);
	}

	private void control()
		{
		this.sliderTaille.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				int taille = sliderTaille.getValue();
				System.out.println("Nouvelle taille de gouttelette : " + taille);
				}
			});

		this.buttonCercle.addActionListener(e -> System.out.println("Spray en cercle sélectionné"));
		this.buttonRectangle.addActionListener(e -> System.out.println("Spray en rectangle sélectionné"));

		this.sliderNbGouttes.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				int nb = sliderNbGouttes.getValue();
				System.out.println("Nouveau nombre de gouttelettes : " + nb);
				}
			});
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Paramètres du Spray", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE));

		// debug
		setOpaque(true);
		setBackground(new Color(71, 71, 71, 255));
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JSlider sliderTaille;
	private JSlider sliderNbGouttes;
	private JToggleButton buttonCercle;
	private JToggleButton buttonRectangle;
	}
