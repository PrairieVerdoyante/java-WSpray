
package ch.hearc.gui.menu;

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
import javax.swing.filechooser.FileNameExtensionFilter;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.export.SprayStorage;
import ch.hearc.gui.spray.display.JDisplay;

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
	|*							Methods Private							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		this.menuBar = new JMenuBar();

		this.fileNew = new JMenuItem("Nouveau");
		this.fileSave = new JMenuItem("Sauvegarder");
		this.fileSaveAs = new JMenuItem("Sauvegarder sous...");
		// TODO: p17 PDF, transparence couleurs
		this.fileImportCSV = new JMenuItem("Importer un fichier CSV...");
		this.fileExportCSV = new JMenuItem("Exporter au format CSV");
		this.fileExportPNG = new JMenuItem("Exporter au format PNG...");

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
		this.file.addSeparator();
		this.file.add(this.fileExportPNG);

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
		this.fileNew.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment créer un nouveau dessin ? Les calques existants seront supprimés.", "Confirmation", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION)
					{
					JDisplay.getInstance().reset();
					}
				}

			});

		this.fileSave.addActionListener(new ActionListener()
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

			});
		this.toolsUndo.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().getUndoRedo().undo();
				JDisplay.getInstance().repaint();
				}
			});

		this.toolsRedo.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().getUndoRedo().redo();
				JDisplay.getInstance().repaint();
				}
			});

		this.fileExportCSV.addActionListener(exportCSVListener());
		this.fileImportCSV.addActionListener(importCSVListener());
		this.fileExportPNG.addActionListener(exportPNGListener());

		this.calqueAdd.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				String name = JOptionPane.showInputDialog(null, "Nom du nouveau calque :", "Ajouter un calque", JOptionPane.PLAIN_MESSAGE);
				if (name != null && !name.trim().isEmpty())
					{
					JDisplay.getInstance().addCalque(name.trim());
					JDisplay.getInstance().getJCalques().resetCalques(); // optional: refresh the UI checkboxes
					}
				}
			});

		this.calqueDelete.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				JDisplay.getInstance().removeCurrentCalque();
				}
			});
		}

	private void appearance()
		{
		// rien
		}

	private ActionListener exportCSVListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				List<Sprayage> allSprays = new ArrayList<>();
				for(DCalque calque:JDisplay.getInstance().getCalques())
					{
					if (calque.isVisible())
						{
						allSprays.addAll(calque.getSprays());
						}
					}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisir l'emplacement");
				int userSelection = fileChooser.showSaveDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION)
					{
					File fileToSave = fileChooser.getSelectedFile();
					try
						{
						int result = JOptionPane.showConfirmDialog(null, "Voulez-vous le rayon ?", "Confirmation", JOptionPane.YES_NO_OPTION);
						boolean data = !(result == JOptionPane.YES_OPTION);

						int labelized = JOptionPane.showConfirmDialog(null, "Souhaitez-vous un dataset labelisé? (retourne un dataset sans la couleur)");
						boolean isLabelized = (labelized == JOptionPane.YES_OPTION);

						String filePath = fileToSave.getAbsolutePath();
						if (!filePath.toLowerCase().endsWith(".csv"))
							{
							filePath += ".csv";
							}

						SprayStorage.exportToCsv(allSprays, filePath, data, isLabelized);
						}
					catch (IOException e1)
						{
						e1.printStackTrace();
						}
					}
				}
			};

		}

	private ActionListener importCSVListener()
		{
		return new ActionListener()
			{

			public void actionPerformed(ActionEvent e)
				{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Importer un fichier CSV");

				// Only allow .csv files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers CSV (*.csv)", "csv");
				fileChooser.setFileFilter(filter);

				int userSelection = fileChooser.showOpenDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION)
					{
					File selectedFile = fileChooser.getSelectedFile();

					if (!selectedFile.getName().toLowerCase().endsWith(".csv"))
						{
						JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fichier CSV valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
						}

					try
						{
						List<Sprayage> importedSprays = SprayStorage.importFromCsv(selectedFile.getAbsolutePath());

						DCalque newCalque = new DCalque("Import CSV");
						newCalque.getSprays().addAll(importedSprays);

						String name = "Import CSV - " + (JDisplay.getInstance().getCalques().size() + 1);
						JDisplay.getInstance().addCalque(name, importedSprays);
						JDisplay.getInstance().getJCalques().resetCalques(); // update checkboxes
						}
					catch (IOException ex)
						{
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erreur lors de l'importation du fichier.", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			};
		}

	private ActionListener exportPNGListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				List<Sprayage> allSprays = new ArrayList<>();
				for(DCalque calque:JDisplay.getInstance().getCalques())
					{
					if (calque.isVisible())
						{
						allSprays.addAll(calque.getSprays());
						}
					}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisir l'emplacement");
				int userSelection = fileChooser.showSaveDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION)
					{
					File fileToSave = fileChooser.getSelectedFile();
					try
						{
						String filePath = fileToSave.getAbsolutePath();
						if (!filePath.toLowerCase().endsWith(".png"))
							{
							filePath += ".png";
							}
						SprayStorage.exportToPng(allSprays, filePath);
						}
					catch (IOException e1)
						{
						e1.printStackTrace();
						}
					}
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
	private JMenuItem fileExportPNG;

	private JMenuItem calqueDelete;
	private JMenuItem calqueAdd;
	private JMenuItem calqueFusionner;
	private JMenuItem calquePreview;

	private JMenuItem toolsUndo;
	private JMenuItem toolsRedo;

	}
