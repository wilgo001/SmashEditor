package com.example.smasheditor;

public class SuperSmasheurCard extends Carte{

    public String
            nom,
            probabilite,
            description,
            attaque,
            defense,
            groupe1,
            sacrifice;

    public SuperSmasheurCard() {

    }

    public SuperSmasheurCard(String nom, String probabilite, String description, String attaque, String defense, String groupe1, String sacrifice) {
        this.nom = nom;
        this.probabilite = probabilite;
        this.description = description;
        this.attaque = attaque;
        this.defense = defense;
        this.groupe1 = groupe1;
        this.sacrifice = sacrifice;
    }
}
