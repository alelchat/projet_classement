package projetLotterie;

public class EtudiantTravailleur extends Etudiant { // Un EtudiantTravailleur est un étudiant qui est une personne, mais il a la particularité 
    // de travailler en + de ses études.
    private String contrat;
    private String[] working_hours;

    public EtudiantTravailleur(String ID, String name, String surname, int age, String gender,
    String INE, int promo, int[] notes, String contrat, String[] working_hours){
        // Constructeur complet.
        super(ID, name, surname, age, gender, INE, promo, notes);
        this.contrat = contrat;
        this.working_hours = working_hours;
    }
    public EtudiantTravailleur(String ID, String name, String surname, int age, String gender,
    String INE, int promo, int[] notes, String contrat){
        // Constructeur complet sans les heures de travail (pas utilisé dans le système de classement actuel).
        super(ID, name, surname, age, gender, INE, promo, notes);
        this.contrat = contrat;
    }

    public EtudiantTravailleur(String ID, String name, String surname, String INE, int promo, 
    int[] notes, String contrat){
        // Constructeur avec les informations essentielles.
        super(ID, name, surname, INE, promo, notes);
        this.contrat = contrat;
    }

    public String getContrat(){
        return contrat;
    }
    public String[] getWorkingHours(){
        return working_hours;
    }
    public void infoEtudiant(){
        System.out.println(this.name + " " + this.surname + " a " + this.age + " ans et est de sexe " + this.gender);
        System.out.println("De plus, cet étudiant est de la promo " + this.promo + " et a le numéro d'INE " + this.INE);
        System.out.println("Cet étudiant travaille " + this.working_hours + " heures par semaine et a pour numéro de contrat " + this.contrat);
    }
}
