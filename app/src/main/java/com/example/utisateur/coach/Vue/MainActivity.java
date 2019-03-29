package com.example.utisateur.coach.Vue;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utisateur.coach.Controleur.Controle;
import com.example.utisateur.coach.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //*********PROPRIETES**********//
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;


    /**
     * Initialisation des liens avec les objets graphique
     */
    private void init() {
        txtPoids = (EditText)findViewById(R.id.txtPoids);
        txtTaille = (EditText)findViewById(R.id.txtTaille);
        txtAge = (EditText)findViewById(R.id.txtAge);
        rdHomme = (RadioButton)findViewById(R.id.rdHomme);
        rdFemme = (RadioButton)findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView)findViewById(R.id.imgSmiley);

        this.controle = Controle.getInstance(this);

        ecouteCalcul();

        recupProfil();
    }

    /**
     * Ecoute événement sur bouton Calcul
     */
    private void ecouteCalcul(){
        ((Button)findViewById(R.id.btnCalcul)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT ).show();
                //Log.d("message", "clic ok sur le bouton calcul **********************

                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;
                // Récupération des données saisies
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){ }
                if(rdHomme.isChecked()){
                    sexe = 1;
                }
                // Contrôle des données saisies
                if(poids==0 || taille==0 || age==0){
                    Toast.makeText(MainActivity.this,"Saisie incorrecte",Toast.LENGTH_SHORT ).show();
                }else {
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affichage de l'IMG, du message et de l'image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe){
        // Création du profil et récupération des informations
        this.controle.creerProfil(poids, taille, age, sexe,this); //this, on lui envoit lui même car on est déjà dans le contexte)
        float img = this.controle.getImg();
        String message = this.controle.getMessage();
        // Affichage image
        if(message == "normal"){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        }else{
            lblIMG.setTextColor(Color.RED);
            if (message == "trop faible"){
                imgSmiley.setImageResource(R.drawable.maigre);
            }else{
                imgSmiley.setImageResource(R.drawable.graisse);
            }
        }
        lblIMG.setText(String.format("%.01f",img )+" : IMG " + message);
    }

    /**
     * Récupération du profil s'il a été sérialis&
     */
    private void recupProfil(){
        if(controle.getPoids()!=null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSexe() == 1){
                rdHomme.setChecked(true);
            }
            //Simule le clic sur le bouton calcul
            ((Button) findViewById(R.id.btnCalcul)).performClick();
        }


    }
}
