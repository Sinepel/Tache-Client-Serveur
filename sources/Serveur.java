package fr.ustl.sil.da2i.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.lang.StringBuilder;


public class Serveur {
	private ServerSocket serveurSocket = null;
	public static ArrayList<Tache> taches;

	//Création de la tache de test
	private Tache tache = new Tache("boulangc","Partir end vacances","21/02/2014");
	
	/*
	*Constructeur du serveur, il crée la socket, initialise la liste des taches
	*/
	public Serveur() {
		try {
			serveurSocket = new ServerSocket(8599);
			//Création de la liste de tache
			taches = new ArrayList<Tache>();
			taches.add(tache);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	/*
	* Méthode qui gère les clients. A chaque client, elle crée en "Child".
	*/
	public void miseEnService() {
		Socket unClient = null;
		
		while (true) {
			try 
			{
				//Nouvel enfant quand un client se connecte
				unClient = serveurSocket.accept();
				//On lui envoie le client et les taches.
				new Child(unClient).start();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		
			
		}
	}
	
	
}
