package fr.ustl.sil.da2i.socket;


import java.io.*;
import java.net.*;
import java.util.*;
//import socket.util.Commands;

public class Child extends Thread {

	private Socket unClient = null;
	private boolean isRunning;
	

	public Child(Socket client) {
		this.unClient = client;
	
		this.isRunning = true;
		System.out.println("Nouveau Client");
	}

	public void run() {
		System.out.println("Run Client");
		realiseService();
	}

	private void realiseService() {
		PrintWriter envoi = null;
		BufferedReader reception = null;
		try {
	
			envoi = new PrintWriter(unClient.getOutputStream(), true);
			
			reception = new BufferedReader(
                    new InputStreamReader(unClient.getInputStream()));
	
			


			//Gestion de l'action
			String action = reception.readLine();
			if (action.equals("Ajouter"))
			{
				ajouter(reception);
			}
			else if (action.equals("Lister"))
			{
				String message = reception.readLine();
				StringBuilder retour  = new StringBuilder();
				retour.append("Bonjour " + message);
				retour.append(";Libelle\tAuteur\tDate butoire");
				//Afficher les taches
				for(Tache t: Serveur.taches)
				{
					retour.append(";"+t.getLibelle()+"\t"+t.getAuteur()+"\t"+t.getDate());
				}
				//envoie de la liste des taches
				envoi.println(retour);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void ajouter(BufferedReader reception)
	{
		String libelle, auteur;
		libelle = "";
		auteur=""; 

		try {
			auteur 	= reception.readLine();
			libelle = reception.readLine();
		} catch( Exception e ) {
			
		}
		Serveur.taches.add(new Tache(auteur,libelle,"date"));
	}
	

}