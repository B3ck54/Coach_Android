package com.example.utisateur.coach.Outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CJ on 28/01/2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // PROPRIETES
    private String creation = "create table profil ("
            + "datemesure TEXT PRIMARY KEY,"
            + "poids INTEGER NOT NULL,"
            + "taille INTEGER NOT NULL,"
            + "age INTEGER NOT NULL,"
            + "sexe INTEGER NOT NULL);";

    /**
     * CONSTRUCTEUR
     * @param context
     * @param name
     * @param factory
     * @param version
     */

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    /**
     *  S'éxécute que s'il y a un changement de base de données. Se fait qu'une seule fois
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation); //l'exécute une seule fois

    }


    /**
     * S'éxécute si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
