package commons;

import creatures.visual.ColorCube;

public class Generate {
	private Config config;
	private String[] features;
	private static Generate instance = new Generate(new String[] {"moyen", "cube"});

	private Generate(String[] args) {
		// TODO Auto-generated constructor stub
		config = Config.getInstance();
		features = args;
	}
	
	public static Generate getInstance(){
		return instance;
	}
	
	public void generateConfig() {
		generateMoteur(features[0]);
		generateColor(features[1]);
	}

	public void generateMoteur(String vitesse) {
		generateVitesseSimu(vitesse);
	}

	private void generateVitesseSimu(String vitesseSimu) {
		// TODO Auto-generated method stub
		if(vitesseSimu.equalsIgnoreCase("lent")){
			config.setVitesseSimu(20);
		} else if(vitesseSimu.equalsIgnoreCase("moyen")){
			config.setVitesseSimu(10);
		} else if(vitesseSimu.equalsIgnoreCase("rapide")){
			config.setVitesseSimu(5);
		}
	}
	
	private void generateColor(String couleur){
		if(couleur.equalsIgnoreCase("cube")){
			config.setColor(new ColorCube(50)); 
		} /*else if(couleur.equalsIgnoreCase("unique")){
			config.setColor(new Unique());
		} else if(couleur.equalsIgnoreCase("group")){
			config.setColor(new Group());
		}*/
	}
}