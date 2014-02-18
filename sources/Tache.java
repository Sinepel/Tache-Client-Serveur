package fr.ustl.sil.da2i.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Tache{


	private String status;
	private String libelle;
	private String auteur; 
	private String date;

	public Tache(String auteur, String libelle,String date)
	{
		this.status = "Libre";
		this.libelle = libelle;
		this.auteur = auteur;
		this.date = date;
	}

	public String getStatus() {
	    return this.status;
	}

	public String getLibelle() {
	    return this.libelle;
	}

	public String getAuteur() {
	    return this.auteur;
	}

	public String getDate() {
	    return this.date;
	}

	public void setStatus(String status) {
	    this.status = status;
	}

	public void setLibelle(String libelle) {
	    this.libelle = libelle;
	}

	public void setAuteur(String auteur) {
	    this.auteur = auteur;
	}

	public void setDate(String date) {
	    this.date = date;
	}

	

}