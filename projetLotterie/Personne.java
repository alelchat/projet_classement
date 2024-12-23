package projetLotterie;

public class Personne {
    protected String ID;
    protected String name;
    protected String surname;
    protected int age;
    protected String gender;
    protected Chambre chambre = null;

    public Personne(String ID, String name, String surname, int age, String gender){
        // Constructeur complet.
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;   
    }
    public Personne(String ID, String name, String surname){
        // Constructeur avec les informations essentielles.
        this.ID = ID;
        this.name = name;
        this.surname = surname;  
    }

    public void afficherChambre(){
        if(chambre == null){
            System.out.println(this.name + " " + this.surname + " n'a pas de chambre pour le moment.");
        }
        else{
            System.out.println(this.name + " " + this.surname + " possède la chambre " + this.chambre.getName());
        }
    }
    public void infoEtudiant(){
        System.out.println(this.name + " " + this.surname + " a " + this.age + " ans et est de sexe " + this.gender);
    }
    public void setChambre(Chambre chambre){
        this.chambre = chambre;
    }
    public Chambre getChambre(){
        return this.chambre;
    }
    public String getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public void liberer_chambre(){
        // Lorsque la personne libère sa chambre (si elle en possède unE) alors on enlève d'abord le lien que possède sa chambre puis on enlève son
        // lien vers cette chambre.
        if(chambre != null){
            this.chambre.setPersonne(null);
            this.chambre = null;
        }
    }
}
