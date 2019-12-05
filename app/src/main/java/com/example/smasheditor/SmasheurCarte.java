package com.example.smasheditor;

public class SmasheurCarte extends Carte {

    public String
            nom,
            probabilite,
            description,
            attaque,
            defense,
            groupe1,
            groupe2;

    public SmasheurCarte() {

    }

    public SmasheurCarte(String nom, String probabilite, String description, String attaque, String defense, String groupe1, String groupe2) {
        this.nom = nom;
        this.probabilite = probabilite;
        this.description = description;
        this.attaque = attaque;
        this.defense = defense;
        this.groupe1 = groupe1;
        this.groupe2 = groupe2;
    }
}
