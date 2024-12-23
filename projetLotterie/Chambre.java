package projetLotterie;

public class Chambre {
    private String ID;
    private float surface;
    private String name;
    private int creation_date;
    private int latest_renovation_date;
    private int nb_locations;
    private int old_scores[];
    private Personne personne = null;


    public Chambre(String ID, float surface, int creation_date, 
    int latest_renovation_date, int nb_locations, int[] old_scores, String name){
        this.ID = ID;
        this.surface = surface;
        this.creation_date = creation_date;
        this.latest_renovation_date = latest_renovation_date;
        this.nb_locations = nb_locations;
        this.old_scores = old_scores;
        this.name = name;
    }

    public int[] getOldScore(){
        return this.old_scores;
    }
    public int getNbLocations(){
        return this.nb_locations;
    }
    public int getRenovationDate(){
        return this.latest_renovation_date;
    }
    public int getCreationDate(){
        return this.creation_date;
    }
    public float getSurface(){
        return this.surface;
    }
    public String getID(){
        return this.ID;
    }
    public Personne getPersonne(){
        return this.personne;
    }
    public void setPersonne(Personne personne){
        this.personne = personne;
    }
    public void infoChambre(){
        System.out.println("La chambre a le nom " + this.name + " et possède une surface de " + this.surface + " m²");
    }
    public String getName(){
        return this.name;
    }
    public void afficherPersonne(){
        if(personne == null){
            System.out.println("La chambre " + this.getName() + " n'héberge personne.");
        }
        else{
            System.out.println("La chambre " + this.getName() + " héberge l'étudiant " + this.personne.getName() + " " + this.personne.getSurname());
        }
    }

}

