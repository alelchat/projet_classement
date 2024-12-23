package projetLotterie;

import java.util.ArrayList;

public class Residence {
    private String adresse;
    private String ville;
    private int city_code;
    private String name_residence;
    private ArrayList<Chambre> chambreResidence = new ArrayList<>(); // La taille est variable (ajout par l'interface par exemple, donc nécessité d'un "tableau")

    public Residence(String adresse, String ville, int city_code, String name_residence){
        this.name_residence = name_residence;
        this.adresse = adresse;
        this.ville = ville;
        this.city_code = city_code;
    }
    public ArrayList<Chambre> getChambre(){
        return chambreResidence;        
    }
    public void infoResidence(){
        System.out.println("La résidence se trouve au " + adresse + ", " + ville + ", " + city_code);
    }
    public String getAddress(){
        return this.adresse;
    }
    public String getNameResidence(){
        return this.name_residence;
    }
}