package fr.ustl.sil.da2i.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Scanner;


public class Client {
	Socket clientSocket = null;
	PrintWriter envoi = null;
	BufferedReader reception = null;
	//Login de la personne connectée
	private String login;
	public String getLogin() {return this.login;}
	public void setLogin(String login) {this.login = login;}


	public Client() 
	{
		try 
		{
			clientSocket = new Socket("localhost",8599);
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	    try 
	    {
			envoi = new PrintWriter(clientSocket.getOutputStream(), true);
			reception = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public String envoyer(String message) {
   		
		String recu = "";
		/*envoi.println(this.getLogin());
	    
	    try {
	    	//Gestion du retour
	    	String recu = reception.readLine();
	    	recu = recu.replaceAll(";","\n"); 
			return recu;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}*/

		//envoi.println(message);

		Scanner sc = new Scanner(System.in);
		if(message.equals("Ajouter")) {

			envoi.println(message);
			envoi.println(this.getLogin());
			System.out.println("Quel est le nom de la tache ?");
			envoi.println( sc.nextLine() );

			return recu;

		}
		else if (message.equals("Lister"))
		{
			envoi.println(message);
	    
		    try {
		    	//Gestion du retour
		    	recu = reception.readLine();
		    	recu = recu.replaceAll(";","\n"); 
				return recu;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			envoi.println(message);
		}
		else if (message.equals("Affecter"))
		{
			envoi.println(message);
			System.out.println("Saisissez l'id de la tache :");
			envoi.println(sc.nextLine());
			System.out.println("Saisissez le login de l'utilisateur a affecté :");
			envoi.println(sc.nextLine());
	    	}
		else if (message.equals("Supprimer"))
		{
			envoi.println(message);
			System.out.println("Saisissez l'id de la tache a supprimer :");
			envoi.println(sc.nextLine());
		}
	    return recu;
	}

}
