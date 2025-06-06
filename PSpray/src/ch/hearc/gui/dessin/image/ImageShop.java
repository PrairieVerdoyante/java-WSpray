
package ch.hearc.gui.dessin.image;

package ch.hearc.c_gui.c_dessin.c_image.b_file;

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

	public static final ImageIcon WARNING_48 = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "warning48.png");
	public static final ImageIcon SUN_48 = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "sun48.png");
	public static final ImageIcon SUN_64 = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "sun64.png");

	public static final ImageIcon JAVA = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "java.png");
	public static final ImageIcon LINUX = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "linux.png");
	public static final ImageIcon CERVIN = ImageLoader.loadSynchroneJar(PATH_IN_JAR + "cervin.png");

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
