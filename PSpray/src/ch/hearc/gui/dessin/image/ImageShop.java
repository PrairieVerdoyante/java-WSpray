
package ch.hearc.gui.dessin.image;

import javax.swing.ImageIcon;

import ch.hearc.c_gui.z_tools.image.io.ImageLoader;

/**
* <pre>
* Contrainte:
*
* 		Les images doivent se trouver dans un jar, et le jar dans le classpath!
*
* Conseil:
*
* 		Pour garantir l'unicite, utiliser dans le jar un systeme de folder correspondant au package du projet
*
* Fomat:
*
* 		- png (conseiller, car couche alpha pour transparence)
* 		- jpeg
*
* </pre>
*/
public class ImageShop
	{

	private static final String PATH_IN_JAR = "ch/hearc/cours/images/";

	/*------------------------------------------------------------------*\
	|*		 Version Synchrone (bloquant)								*|
	\*------------------------------------------------------------------*/

	public static final ImageIcon CARRE = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "carre.png");
	public static final ImageIcon CERCLE = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "cercle.png");
	public static final ImageIcon DEPLACER = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "deplacer.png");
	public static final ImageIcon GOMME = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "gomme.png");
	public static final ImageIcon SELECTIONNER = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "selectionner.png");
	public static final ImageIcon SPRAY = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "spray.png");

	}
