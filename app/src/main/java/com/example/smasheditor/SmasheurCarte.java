package com.example.smasheditor;

public class SmasheurCarte extends Carte {

    public String nom;
    public String probabilite;
    public String description;
    public String attaque;
    public String defense;
    public String groupe;

    public SmasheurCarte() {

    }

    public SmasheurCarte(String nom, String probabilite, String description, String attaque, String defense, String groupe) {
        this.nom = nom;
        this.probabilite = probabilite;
        this.description = description;
        this.attaque = attaque;
        this.defense = defense;
        this.groupe = groupe;
    }
}
