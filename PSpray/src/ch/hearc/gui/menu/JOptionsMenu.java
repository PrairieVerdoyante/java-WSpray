
package ch.hearc.gui.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ch.hearc.gui.dessin.calques.DCalque;
import ch.hearc.gui.dessin.calques.JCalques;
import ch.hearc.gui.display.JDisplay;
import ch.hearc.gui.export.SprayStorage;
import ch.hearc.gui.utils.Spray;

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
		this.toolsUndo.addActionListener(createUndoListener());
		this.toolsRedo.addActionListener(createRedoListener());
		this.fileExportCSV.addActionListener(exportCSVListener());
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
				else
					{
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

	private ActionListener createUndoListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().getHistory().undo();
				JDisplay.getInstance().repaint();
				}
			};
		}

	private ActionListener createRedoListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().getHistory().redo();
				JDisplay.getInstance().repaint();
				}
			};
		}

	private ActionListener exportCSVListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				List<Spray> allSprays = new ArrayList<>();
				for(DCalque calque:JDisplay.getInstance().getCalques())
					{
					if (calque.isVisible())
						{
						allSprays.addAll(calque.getSprays());
						}
					}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisir un fichier CSV");
				int userSelection = fileChooser.showSaveDialog(null);

				try
					{

					if (userSelection == JFileChooser.APPROVE_OPTION)
						{
						File fileToSave = fileChooser.getSelectedFile();
						SprayStorage.exportToCsv(allSprays, fileToSave.getAbsolutePath() + ".csv");
						}
					}
				catch (IOException e1)
					{
					e1.printStackTrace();
					}

				}
			};
		}

	private static String getColorName(Color color)
		{
		    if (color.equals(Color.RED))
				{
					return "rouge";
					}
		    if (color.equals(Color.GREEN))
				{
					return "vert";
					}
		    if (color.equals(Color.BLUE))
				{
					return "bleu";
					}
		    if (color.equals(Color.PINK))
				{
					return "pink";
					}
		    if (color.equals(Color.BLACK))
				{
					return "noir";
					}
		    if (color.equals(Color.WHITE))
				{
					return "blanc";
					}
		    if (color.equals(Color.YELLOW))
				{
					return "jaune";
					}
		    if (color.equals(Color.ORANGE))
				{
					return "orange";
					}
		    if (color.equals(Color.CYAN))
				{
					return "cyan";
					}
		    if (color.equals(Color.MAGENTA))
				{
					return "magenta";
					}

		    return "inconnu";
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
