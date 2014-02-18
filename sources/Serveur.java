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
	private ArrayList<Tache> taches;

	//Création de la tache de test
	private Tache tache = new Tache("boulangc","holla senorita","26/06/2014");
	
	
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
	
	public void miseEnService() {
		Socket unClient = null;
		
		while (true ) {
			try {
				unClient = serveurSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		
			realiseService(unClient);
		}
	}
	
	private void realiseService(Socket unClient) {
		PrintWriter envoi = null;
		BufferedReader reception = null;
		try {
	
			envoi = new PrintWriter(unClient.getOutputStream(), true);
			
			reception = new BufferedReader(
                    new InputStreamReader(unClient.getInputStream()));
	
			String message = reception.readLine();
			StringBuilder retour  = new StringBuilder();
			retour.append("bonjour " + message);
			//Afficher les taches
			for(Tache t: taches)
			{
				retour.append(";"+t.getLibelle());
			}
			envoi.println(retour);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
