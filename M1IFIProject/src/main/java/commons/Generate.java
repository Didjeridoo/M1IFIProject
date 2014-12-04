package commons;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import creatures.visual.ColorCube;
import creatures.visual.ColorUnique;

/**
 * Permet de g�nerer les plugins, et donc construire un produit � partir
 * de la configuration entr�e par l'utilisateur.
 * 
 * classe singleton
 * 
 * @author Marc
 *
 */
public class Generate {
	private Config config;
	private String[] features;
	private String path;
	private static Generate instance = new Generate(new String[] { "moyen",
			"cube", "VAleatoire", "DAleatoire", "Circular", "CustomCreature",
			"Troupeau" });

	private Generate(String[] args) {
		// TODO Auto-generated constructor stub
		config = Config.getInstance();
		features = args;
		path = new File("").getAbsolutePath();
	}

	/**
	 * 
	 * @return l'instance de la classe Generate
	 */
	public static Generate getInstance() {
		return instance;
	}

	/**
	 * G�n�re les plugins demand�s par l'utilisateur
	 */
	public void generateConfig() {
		generateMoteur(features[0]);
		generateColor(features[1]);
		generateVitesse(features[2]);
		generateDirection(features[3]);
		generateEnvironnement(features[4]);
		generateCreature(features[5]);
		generateDeplacement(features[6]);
	}

	public void generateMoteur(String vitesse) {
		generateVitesseSimu(vitesse);
	}

	/**
	 * 
	 * @String vitesseSimu correspondante � la vitesse d'execution 
	 * de la simulation.
	 * 
	 * lent : Execution delay in milliseconds 20ms 
	 * moyen : Execution delay in milliseconds 10ms
	 * rapide : Execution delay in milliseconds 5ms
	 * 
	 */
	private void generateVitesseSimu(String vitesseSimu) {
		// TODO Auto-generated method stub
		if (vitesseSimu.equalsIgnoreCase("lent")) {
			config.setVitesseSimu(20);
		} else if (vitesseSimu.equalsIgnoreCase("moyen")) {
			config.setVitesseSimu(10);
		} else if (vitesseSimu.equalsIgnoreCase("rapide")) {
			config.setVitesseSimu(5);
		}
	}
	
	/**
	 * 
	 * @String couleur correspondante au plugin de
	 * couleur voulu pour les cr�atures.
	 */
	private void generateColor(String couleur){
		if(couleur.equalsIgnoreCase("cube")){
			config.setColor(new ColorCube(50)); 
		}else if(couleur.equalsIgnoreCase("unique")){
			config.setColor(new ColorUnique());
		}/* else if(couleur.equalsIgnoreCase("group")){
			config.setColor(new Group());
		}*/
	}

	/**
	 * 
	 * @String vitesse correspondante � la vitesse
	 * des cr�atures.
	 */
	public void generateVitesse(String vitesse) {
		if (vitesse.equalsIgnoreCase("VAleatoire")) {
			config.setVitesse(-1);
		} else if (vitesse.equalsIgnoreCase("VFixe")) {
			config.setVitesse(5);
		}
	}

	/**
	 * 
	 * @String direction correspondante � la direction
	 * des cr�atures.
	 */
	public void generateDirection(String direction) {
		if (direction.equalsIgnoreCase("DAleatoire")) {
			config.setDirection(-1);
		} else if (direction.equalsIgnoreCase("DFixe")) {
			config.setDirection(0.d);
		}
	}
	
	/**
	 * 
	 * @String nombre correspondante au nombre de
	 * cr�atures � cr�er.
	 */
	public void generateNombre(String nombre){
		String[] nbTmp = nombre.split(" ");
		if(nombre.equalsIgnoreCase("Fixe")){
			config.setNombre(5);
		} else if(nbTmp[0].equalsIgnoreCase("NAleatoire")){
			if(nbTmp[1].equalsIgnoreCase("Dizaine")){
				Random r = new Random();
				int i1 = (r.nextInt(100 - 10) + 10);
				config.setNombre(i1);
			}else if(nbTmp[1].equalsIgnoreCase("Centaine")){
				Random r = new Random();
				int i1 = (r.nextInt(1000 - 100) + 100);
				config.setNombre(i1);
			}else if(nbTmp[1].equalsIgnoreCase("Millier")){
				Random r = new Random();
				int i1 = (r.nextInt(10000 - 1000) + 1000);
				config.setNombre(i1);
			}
		}
	}

	/**
	 * 
	 * @String environnement correspondante au comportement
	 * aux bords pour les cr�atures.
	 * 
	 * Closed 	: monde ferm�
	 * Toric 	: monde libre
	 * Circular : rebonds en haut et en bas de la fen�tre, libre sur
	 * 			  les cot�s.
	 */
	private void generateEnvironnement(String environnement) {

		Path pathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "comportements" + File.separator
				+ environnement + ".class");
		Path pathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "comportements" + File.separator + environnement + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "comportements" + File.separator
				+ environnement + "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "comportements" + File.separator + environnement + "Test"
				+ ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @String creature correspondante aux types de 
	 * cr�atures que l'utilisateur souhaite pouvoir
	 * selectionner.
	 */
	private void generateCreature(String creature) {

		Path pathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "creatures" + File.separator + creature
				+ ".class");
		Path pathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "creatures"
				+ File.separator + creature + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "creatures" + File.separator + creature
				+ "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator + "creatures"
				+ File.separator + creature + "Test" + ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @String deplacement correspondante au type de d�placement
	 * des cr�atures.
	 * 
	 * Stupid 	: d�placement dans une seule direction.
	 * Troupeau : d�placement en fonction des cr�atures autour d'elle.
	 * Hasard 	: d�placement al�atoire qui change � un tick donn�.
	 */
	public void generateDeplacement(String deplacement) {

		Path pathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "deplacements" + File.separator
				+ deplacement + ".class");
		Path pathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "deplacements" + File.separator + deplacement + ".class");

		Path testPathSource = Paths.get(path + File.separator + "myPluginsList"
				+ File.separator + "deplacements" + File.separator
				+ deplacement + "Test" + ".class");
		Path testPathTarget = Paths.get(path + File.separator + "myplugins"
				+ File.separator + "repository" + File.separator
				+ "deplacements" + File.separator + deplacement + "Test"
				+ ".class");

		try {
			Files.copy(pathSource, pathTarget, REPLACE_EXISTING);
			Files.copy(testPathSource, testPathTarget, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}