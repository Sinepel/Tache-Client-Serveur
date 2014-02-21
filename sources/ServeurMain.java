package fr.ustl.sil.da2i.socket;
/*
Classe à lancer pour démarrer le serveur.
*/
public class ServeurMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Création du serveur et de la mise en serveur de ce dernier.
		Serveur serveur = new Serveur();		
		serveur.miseEnService();
		
	}

}
