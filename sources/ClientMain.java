package fr.ustl.sil.da2i.socket;

public class ClientMain {

	

	
	/**
	 * @param args
	 */


	//public 
	public static void main(String[] args) {

		Client client = new Client();
		//Récupération du login en premier paramètre
		client.setLogin(args[0]);
		String resultat = client.envoyer(client.getLogin());
		
		System.out.println(resultat);
		Clavier clavier = new Clavier();
		String entree = "";

		while(!entree.equals("stop"))
		{
			entree=clavier.readString();
		}
	}

}
