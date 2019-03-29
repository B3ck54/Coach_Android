package com.example.utisateur.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.utisateur.coach.Outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal { // enregistre les profils au fur et à mesure de la création


    // PROPRIETES
    private String nomBase ="bdCoach.sqlite"; // mémoriser le nom de la base de données
    private Integer versionBase = 1; // version 1
    private MySQLiteOpenHelper accesBD; // objet qui accede à ma classe outils
    private SQLiteDatabase baseDeDonnees;// pour créer des canaux qui permettent d'écrire ou lire dans les bases de données


    /**
     * CONSTRUCTEUR
     * @param contexte
     */
    public AccesLocal(Context contexte){ // Instancier la classe pour pourvoir remplir mon accesDB
        accesBD = new MySQLiteOpenHelper(contexte,nomBase,null,versionBase);
    }


    /**
     * Ajout d'un profil dans la base de données
     * @param profil
     */
    public void ajout(Profil profil){
        baseDeDonnees = accesBD.getWritableDatabase();
        String req = "insert into profil (datemesure, poids, taille, age, sexe) values "; // création de la requête
        req += "(\""+profil.getDateMesure()+"\","+profil.getPoids()+","+profil.getTaille()+","+profil.getAge()+","+profil.getSexe()+")"; // syntaxe pour continuer la chaine
        baseDeDonnees.execSQL(req);//on exécute la requête
    }

    /**
     * Récupération du dernier profil de la base de donnnées
     * @return
     */
    public Profil recupDernier (){
        baseDeDonnees = accesBD.getReadableDatabase();
        Profil profil = null; // initialisé à null contiendra le dernier profil utilisé
        String req = "select * from profil";
        Cursor curseur = baseDeDonnees.rawQuery(req,null);// Lit ligne à ligne les informations récupérée par une requête select,
        curseur.moveToLast();
        if(!curseur.isAfterLast()){ //si je ne suis pas après le dernier à ce moment là je pourrais le récupérer
            Date date = new Date();
            Integer poids = curseur.getInt(1); //le curseur récupère
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(date, poids,taille,age,sexe);
        }
        curseur.close();
        return profil;
    }


}
