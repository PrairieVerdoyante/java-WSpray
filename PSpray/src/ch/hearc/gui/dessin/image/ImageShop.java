
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

	/*------------------------------------------------------------------*\
	|*		Version Assynchrone	(non bloquant)							*|
	\*------------------------------------------------------------------*/

	//		public static final ImageIcon WARNING_48 = ImageLoader.loadAsynchroneJar(PATH_IN_JAR+"warning48.png");
	//		public static final ImageIcon SUN_48 = ImageLoader.loadAsynchroneJar(PATH_IN_JAR + "sun48.png");
	//		public static final ImageIcon SUN_64 = ImageLoader.loadAsynchroneJar(PATH_IN_JAR + "sun64.png");
	//
	//		public static final ImageIcon JAVA = ImageLoader.loadAsynchroneJar(PATH_IN_JAR+"java.png");
	//		public static final ImageIcon LINUX = ImageLoader.loadAsynchroneJar(PATH_IN_JAR+"linux.png");
	//		public static final ImageIcon CERVIN = ImageLoader.loadAsynchroneJar(PATH_IN_JAR+"cervin.png");

	}
