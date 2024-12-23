package projetLotterie;

public class Etudiant extends Personne{ // Un étudiant est une personne, mais il a la particularité d'être dans une école.
    protected String INE;
    protected int promo;
    protected int[] notes;

    public Etudiant(String ID, String name, String surname, int age, String gender, String INE, int promo, int[] notes){
        // Constructeur complet.
        super(ID, name, surname, age, gender);
        this.INE = INE;
        this.promo = promo;
        this.notes = notes;
    }
    public Etudiant(String ID, String name, String surname, String INE, int promo, int[] notes){
        // Constructeur avec les informations essentielles.
        super(ID, name, surname);
        this.INE = INE;
        this.promo = promo;
        this.notes = notes;
    }
    public int getPromo(){
        return promo;
    }
    public String getINE(){
        return INE;
    }
    public int[] getNotes(){
        return notes;
    }
    public void infoEtudiant(){
        System.out.println(this.name + " " + this.surname + " a " + this.age + " ans et est de sexe " + this.gender);
        System.out.println("De plus, cet étudiant est de la promo " + this.promo + " et a le numéro d'INE " + this.INE);
    }
}

