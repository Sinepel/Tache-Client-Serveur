package fr.ustl.sil.da2i.socket;

public class ClientMain {

	



	public static void main(String[] args) {

		Client client = new Client();
		//Récupération du login en premier paramètre
		client.setLogin(args[0]);

		//client.envoyer(client.getLogin());
		String resultat = "";
		//System.out.println(resultat);

		Clavier clavier = new Clavier();
		String entree = "";

		//Saisie utilisateur
		entree=clavier.readString();
		while(!entree.equals("stop"))
		{
			resultat = client.envoyer(entree);
			System.out.println(resultat);
			entree=clavier.readString();

		}
	}

}
