package com.example.utisateur.coach.modele;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;

public class Profil implements Serializable {

    //*********CONSTANTES**********//
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // surpoids si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // surpoids si au dessus



    //*********PROPRIETES**********//
    private Date dateMesure; // memorise une date, une heure précise
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private Float img;
    private String message;


    //*********CONSTRUCTEURS**********//
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }


    //*********GETTER**********//
    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public Float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() { return dateMesure;
    }


    //*********METHODES**********//
    private void calculIMG() {
        float tailleM = ((float) taille / 100);
        this.img = (float) ((1.2 * poids / (tailleM * tailleM)) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    private void resultIMG() {
        Integer min;
        Integer max;

        if (sexe == 0) { // femme
            min = minFemme;
            max = maxFemme;
        } else {
            min = minHomme;
            max = maxHomme;
        }
        // Messages correspondants
        message = "normal";
        if (img<min){
            message = "trop faible";
        }else{
            if (img>max) {
                message = "trop élevé";
            }
        }

    }

}
