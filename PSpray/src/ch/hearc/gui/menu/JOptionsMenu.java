
package ch.hearc.gui.menu;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		// rien
		}

	private void appearance()
		{
		// rien
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

	}
