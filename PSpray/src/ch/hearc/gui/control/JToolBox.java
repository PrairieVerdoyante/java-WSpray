package ch.hearc.gui.control;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

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
        }

    /*------------------------------------------------------------------*\
    |*                           Methods Private                        *|
    \*------------------------------------------------------------------*/

    private void geometry()
        {
        // Chargement des images comme icônes
        buttonSpray = createToolButton("spray.png");   // Image pour le spray
        buttonGomme = createToolButton("gomme.png");   // Image pour la gomme
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
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icons/" + imageName));

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
        // Tu peux ajouter des listeners ici pour détecter quel outil est choisi
        }

    private void appearance()
        {
        setBorder(BorderFactory.createTitledBorder("Outils"));

        // debug
        setOpaque(true);
        setBackground(Color.ORANGE);
        }

    /*------------------------------------------------------------------*\
    |*                           Attributs Private                      *|
    \*------------------------------------------------------------------*/

    private JToggleButton buttonSpray;
    private JToggleButton buttonGomme;
    private JToggleButton buttonSelectionner;
    private JToggleButton buttonDeplacer;
    }
