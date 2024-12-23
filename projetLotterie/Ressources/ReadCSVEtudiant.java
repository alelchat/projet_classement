package projetLotterie.Ressources;

public class ReadCSVEtudiant {
    private String id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String INE;
    private int promo;
    private int[] notes;
    private String contrat;
    private String[] working_hours;

    public ReadCSVEtudiant(String[] line_splited) {
        // just filling variables from the table
        this.id = line_splited[0];
        this.name = line_splited[1];
        this.surname = line_splited[2];
        this.age = Integer.parseInt(line_splited[3]); // had to convert to int
        this.gender = line_splited[4];
        // 'if' one line statements, just to verify if the value is "null"
        this.INE = line_splited[5]; 
        this.promo = ((line_splited[6].equals("null")) ? 0 : Integer.parseInt(line_splited[6]));
        this.notes = ((line_splited[7].equals("null")) ? new int[0] : parse_list_int(line_splited[7]));
        this.contrat = ((line_splited[8].equals("null")) ? "null" : line_splited[8]);
        this.working_hours = ((line_splited[9].equals("null")) ? new String[0] : line_splited[9].split(","));
    }

    private static int[] parse_list_int(String notes) {
        // function to convert the string of notes to a list of int
        String[] n = notes.replace("[", "").replace("]", "").split(",");
        int[] res = new int[n.length];
        for (int i = 0; i<n.length; i++){
            // System.out.println(n[i]);
            res[i] = Integer.parseInt(n[i]);
        }
        return res;
    }

    public void affiche() {
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.surname);
        System.out.println(this.age);
        System.out.println(this.gender);
        System.out.println(this.INE);
        System.out.println(this.promo);
        System.out.println(this.notes);
        System.out.println(this.contrat);
        System.out.println(this.working_hours);
    }
    public int getAge() {
        return age;
    }
    public String getContrat() {
        return contrat;
    }
    public String getGender() {
        return gender;
    }
    public String getINE() {
        return INE;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int[] getNotes() {
        return notes;
    }
    public int getPromo() {
        return promo;
    }
    public String getSurname() {
        return surname;
    }
    public String[] getWorkingHours() {
        return working_hours;
    }
}