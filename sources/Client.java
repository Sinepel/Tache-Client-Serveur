package fr.ustl.sil.da2i.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

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
		    envoi.println(message);
		    
		    try {
		    	String recu = reception.readLine();
		    	recu = recu.replaceAll(";","\n"); 
				return recu;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		    return null;
	}

}
