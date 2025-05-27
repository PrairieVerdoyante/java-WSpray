
package ch.hearc.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ch.hearc.gui.dessin.calques.JCalques;
import ch.hearc.gui.display.JDisplay;

public class JOptionsMenu extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public JOptionsMenu()
		{
		super(BoxLayout.X_AXIS);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methods Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.menuBar = new JMenuBar();

		this.fileNew = new JMenuItem("Nouveau");
		this.fileSave = new JMenuItem("Sauvegarder");
		this.fileSaveAs = new JMenuItem("Sauvegarder sous...");
		// TODO: p17 PDF
		this.fileImportCSV = new JMenuItem("Importer un fichier CSV...");
		this.fileExportCSV = new JMenuItem("Exporter au format CSV");

		this.calqueDelete = new JMenuItem("Supprimer le calque sélectionné");
		this.calqueAdd = new JMenuItem("Ajouter un calque");
		this.calqueFusionner = new JMenuItem("Fusionner les calques sélectionnés");
		this.calquePreview = new JMenuItem("Prévisualiser le calque");
		// TODO: 8.3: déplacer tout le contenu du calque (calque -> sélectionner" puis déplacer)

		this.toolsUndo = new JMenuItem("Annuler l'action");
		this.toolsRedo = new JMenuItem("Refaire l'action");

		this.file = new JMenu("Fichier");
		this.file.add(this.fileNew);
		this.file.add(this.fileSave);
		this.file.add(this.fileSaveAs);
		this.file.addSeparator();
		this.file.add(this.fileExportCSV);
		this.file.add(this.fileImportCSV);

		this.calque = new JMenu("Calque(s)");
		this.calque.add(this.calqueDelete);
		this.calque.add(this.calqueAdd);
		this.calque.add(this.calqueFusionner);
		this.calque.add(this.calquePreview);

		this.tools = new JMenu("Outils");
		this.tools.add(this.toolsUndo);
		this.tools.add(this.toolsRedo);

		this.imgToGoutelette = new JMenu("transformer une image en goutelettes");

		this.menuBar.add(this.file);
		this.menuBar.add(this.calque);
		this.menuBar.add(this.tools);
		this.menuBar.add(imgToGoutelette);

		add(menuBar);

		}

	private void control()
		{
		this.fileNew.addActionListener(createNewDrawingListener());
		this.fileSave.addActionListener(SaveFileListener());
		}

	private void appearance()
		{
		// rien
		}

	private ActionListener createNewDrawingListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment créer un nouveau dessin ? Les calques existants seront supprimés.", "Confirmation", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION)
					{
					JDisplay.getInstance().reset();
					}
				else {
				System.out.println("non");
				}
				}
			};
		}

	private ActionListener SaveFileListener()
		{
		return new ActionListener()
			{
			// TODO: file stream

			@Override
			public void actionPerformed(ActionEvent e)
				{
				/*
				for(DCalque calque:JDisplay.getInstance().getSelectedCalques())
					{
					// calque.getSprays();
					}

				}*/
				}
			};
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools
	// TODO: ajouter autres options
	private JMenuBar menuBar;
	private JMenu tools;
	private JMenu file;
	private JMenu calque;
	private JMenu imgToGoutelette;

	private JMenuItem fileNew;
	private JMenuItem fileSave;
	private JMenuItem fileSaveAs;
	private JMenuItem fileImportCSV;
	private JMenuItem fileExportCSV;

	private JMenuItem calqueDelete;
	private JMenuItem calqueAdd;
	private JMenuItem calqueFusionner;
	private JMenuItem calquePreview;

	private JMenuItem toolsUndo;
	private JMenuItem toolsRedo;

	private JCalques jCalques;

	}
