package projetLotterie;

public class Salarie extends Personne{
    private String contrat;
    private String[] working_hours;

    public Salarie(String ID, String name, String surname, int age, String gender, String contrat, String[] working_hours){
        // Constructeur complet.
        super(ID, name, surname, age, gender);
        this.contrat = contrat;
        this.working_hours = working_hours;
    }
    public Salarie(String ID, String name, String surname, int age, String gender, String contrat){
        // Constructeur complet.
        super(ID, name, surname, age, gender);
        this.contrat = contrat;
    }
    public Salarie(String ID, String name, String surname, String contrat){
        // Constructeur avec les informations essentielles.
        super(ID, name, surname);
        this.contrat = contrat;
    }
    public void infoEtudiant(){
        System.out.println(this.name + " " + this.surname + " a " + this.age + " ans et est de sexe " + this.gender);
        System.out.println("Cet étudiant travaille " + this.working_hours + " heures par semaine et a pour numéro de contrat " + this.contrat);
    }
    public String getContrat(){
        return this.contrat;
    }
    public String[] getWorkingHours(){
        return this.working_hours;
    }
}
