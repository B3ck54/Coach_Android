package com.example.utisateur.coach.Outils;

import android.content.ContentValues;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;


public class Serializer {

    /**
     * Sérialisation d'un objet // mémorise un objet dans un fichier binaire
     * @param filename
     * @param object
     * @param context
     */

    public static void serialize(String filename, Object object, Context context){
        try {
            FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos;
            try {
                oos = new ObjectOutputStream(file);
                oos.writeObject(object);
                oos.flush(); // pousser l'info
                oos.close();// et fermer le flux
            }catch (IOException e) {
                //erreur de sérialisation
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            //fichier non trouvé
            e.printStackTrace();
        }

    }



    /**
     * permet de récupérer cet objet
     */
    public static Object deSerialize(String filename,Context context){
        try{
            FileInputStream file = context.openFileInput(filename); // On ouvre le fichier en input
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(file); // flux qui permet d'éccéder au contenu du fichier
                try {
                    Object object = ois.readObject(); // on le récup objet dans un type objet
                    ois.close();//ferme le flux
                    return object; // on retourne l'objet
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                }catch (StreamCorruptedException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }catch (FileNotFoundException e){
                // fichier non trouvé
                e.printStackTrace();
            }
            return null;

        }

}
