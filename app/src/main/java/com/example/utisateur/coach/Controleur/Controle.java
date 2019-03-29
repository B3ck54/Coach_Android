package com.example.utisateur.coach.Controleur;
import android.content.Context;

import com.example.utisateur.coach.Outils.Serializer;
import com.example.utisateur.coach.modele.AccesLocal;
import com.example.utisateur.coach.modele.Profil;

import java.util.Date;

public final class Controle {

    /**
     * Propriétés
     */
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";// fichier dans lequel on va faire les enregistrements
    private static AccesLocal accesLocal;

    /**
     * Constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * Création de l'instance
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(contexte);
            profil = accesLocal.recupDernier();
            //SERIALISATION//recupSerialize(context);
        }
        return Controle.instance;
    }

    //*********METHODES**********//

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme 0 pour femme
     */

    public void creerProfil (Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil = new Profil(new Date(),poids, taille, age, sexe); // en faisant un new date on génére la date actuelle
        accesLocal.ajout(profil);
        //SERIALISATION//Serializer.serialize(nomFic,profil,context);
    }

    /**
     * Récupération img de profil
     * @return img
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * Récupération message de profil
      * @return le message
     */
    public String getMessage() {
        return profil.getMessage();
    }

    /**
     * Récupération de l'objet sérialisé (Le profil)
     * @param context
     */

    private static void recupSerialize (Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }

    public Integer getPoids(){
        if (profil==null){
            return null;
        }else{
            return profil.getPoids();
        }
    }


    public Integer getAge(){
        if (profil==null){
            return null;
        }else{
            return profil.getAge();
        }
    }



        public Integer getSexe(){
            if (profil==null){
                return null;
            }else{
                return profil.getSexe();
            }
    }


    public Integer getTaille() {
        if (profil == null) {
            return null;
        } else {
            return profil.getTaille();
        }
    }


}

