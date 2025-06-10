
package ch.hearc.gui.spray.display;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import ch.hearc.gui.calques.DCalque;
import ch.hearc.gui.calques.JCalques;
import ch.hearc.gui.dessin.Sprayage.Sprayage;
import ch.hearc.gui.dessin.actions.dessin.ModeGomme;
import ch.hearc.gui.dessin.actions.dessin.geste.Geste;
import ch.hearc.gui.dessin.actions.undoRedo.UndoRedo;
import ch.hearc.gui.utils.SprayShape;

public interface JSprayConfigurable_I
	{

	/* init */

	public void setColor(Color color);

	public void setNbGouttes(int nbGouttes);

	public void setDimension(int dimension);

	public void setTaille(int taille);

	public void setForme(SprayShape shape);

	/* Calques */

	public void addCalque(String name);

	public void addCalque(String name, List<Sprayage> sprays);

	public void removeLastCalque();

	public void removeCurrentCalque();

	public void removeCalque(DCalque calque);

	public void setCalqueVisible(String name, boolean visible);

	public void setCalques(List<DCalque> newCalques);

	public List<DCalque> getCalques();

	public List<DCalque> getSelectedCalques();

	public DCalque getCurrentCalque();

	public void setCurrentCalque(DCalque currentCalque);

	public void setJCalques(JCalques jCalques);

	public JCalques getJCalques();

	public void previewCurrentCalque();

	/* Actions */

	public void reset();

	public UndoRedo getUndoRedo();

	public void setCurrentStroke(List<Geste> sprays);

	public List<Geste> getCurrentStroke();

	public ModeGomme getModeGomme(); // gomme

	public void setModeGomme(ModeGomme mode);

	public void setGommeRayon(int rayon);

	public void setLigneEpaisseur(int epaisseur);

	public void save(String fileName) throws IOException;

	public void load(String fileName) throws ClassNotFoundException, IOException;

	/* getters propriétés sprays */

	public int getCurrentNbGouttes();

	public int getCurrentTaille();

	public int getCurrentDimension();

	public SprayShape getCurrentShape();

	public int getLigneEpaisseur();

	public void refreshDisplay();

	}
