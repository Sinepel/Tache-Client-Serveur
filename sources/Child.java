package fr.ustl.sil.da2i.socket;


import java.io.*;
import java.net.*;
import java.util.*;
//import socket.util.Commands;

public class Child extends Thread {

	private Socket unClient = null;
	

	public Child(Socket client) {
		this.unClient = client;
	
		
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
			boolean quit = false;
			while(!quit)
			{
				String action = reception.readLine();
				System.out.println(action);
				if (action.equals("Ajouter"))
				{
					ajouter(reception);
				}
				else if (action.equals("Lister"))
				{
					//String message = reception.readLine();
					StringBuilder retour  = new StringBuilder();
					//retour.append("Bonjour " + message);
					retour.append(";Libelle\tAuteur\tDate butoire");
					//Afficher les taches
					int indice =0;
					for(Tache t: Serveur.taches)
					{

						retour.append(";"+indice+"\t"+t.getLibelle()+"\t"+t.getAuteur()+"\t"+t.getDate()+"\t"+t.getStatus());
						indice++;
					}
					//envoie de la liste des taches
					envoi.println(retour);
				}
				else if(action.equals("Lister statut"))
				{
					//String message = reception.readLine();
					StringBuilder retour  = new StringBuilder();
					String statut = reception.readLine();
					//retour.append("Bonjour " + message);
					retour.append("Libelle\tAuteur\tDate butoire");
					//Afficher les taches
					int indice =0;
					for(Tache t: Serveur.taches)
					{
						if(t.getStatus().equals(statut))
						{
							retour.append(";"+t.getLibelle()+"\t"+t.getAuteur()+"\t"+t.getDate()+"\t"+t.getStatus());
						}
					}
					//envoie de la liste des taches
					envoi.println(retour);

				}
				else if(action.equals("Affecter"))
				{
					affecter(reception);
				}
				else if(action.equals("Supprimer"))
				{
					supprimer(reception);
				}
				else if(action.equals("Dater"))
				{
					dater(reception);
				}
				else if(action.equals("Terminer"))
				{
					terminer(reception);
				}
				if(action.equals("stop"))
				{
					quit = true;
				}
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
	public void affecter(BufferedReader reception)
	{
		String auteur;
		Integer indice = 0;
		auteur=""; 

		try {
			indice = Integer.parseInt(reception.readLine());
			auteur 	= reception.readLine();
			Serveur.taches.get(indice).setAuteur(auteur);

		} catch( Exception e ) {
			
		}
	}
	public void supprimer(BufferedReader reception)
	{
		Integer indice = 0;

		try {
			indice = Integer.parseInt(reception.readLine());
			//System.out.println(Serveur.taches.remove(Serveur.taches.get(indice)));
			Serveur.taches.remove(indice.intValue());

		} catch( Exception e ) {
			System.out.println(e.toString());
			
		}
	}
	public void terminer(BufferedReader reception)
	{
		Integer indice = 0;

		try {
			indice = Integer.parseInt(reception.readLine());
			//System.out.println(Serveur.taches.remove(Serveur.taches.get(indice)));
			Serveur.taches.get(indice.intValue()).setStatus("Terminée");

		} catch( Exception e ) {
			System.out.println(e.toString());
			
		}
	}
	public void dater(BufferedReader reception)
	{
		String date;
		Integer indice = 0;
		date=""; 

		try {
			indice = Integer.parseInt(reception.readLine());
			date 	= reception.readLine();
			Serveur.taches.get(indice).setDate(date);

		} catch( Exception e ) {
			
		}
	}
	

}
