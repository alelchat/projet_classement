import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import projetLotterie.*;
import projetLotterie.Ressources.ReadCSVEtudiant;
import projetLotterie.Ressources.ReadCSVChambre;

public class MainProjet {
    public static void main(String[] args) {
        ArrayList<Residence> residences = new ArrayList<>();
        ArrayList<Personne> personnes = new ArrayList<>();

        String path1 = "./projetLotterie/Ressources/liste_etudiants.csv";
        String path2 = "./projetLotterie/Ressources/liste_chambres.csv";
        boolean residenceExistante = false;
        File csvFile1 = new File(path1); // to read the CSV file
        File csvFile2 = new File(path2);
        try (BufferedReader reader1 = new BufferedReader(new FileReader(csvFile1))) {
            String line1;
            ReadCSVEtudiant obj_line_etudiant;
            reader1.readLine(); // Dropping first line
            // On lit le premier CSV pour remplir notre ArrayList de personnes
            while ((line1 = reader1.readLine()) != null) { // reader.readLine() -> to get the line
                obj_line_etudiant = new ReadCSVEtudiant(line1.split(";")); // spliting here but it was a choice
                if (!obj_line_etudiant.getContrat().equals("null")) { // Si ce n'est pas null, alors c'est forcément quelqu'un qui travaille (Salarié,EtudiantTravailleur)
                    if (!obj_line_etudiant.getINE().equals("null")) { // Si ce n'est pas null, alors c'est forcément un EtudiantTravailleur (travail + étude)
                        personnes.add(new EtudiantTravailleur(obj_line_etudiant.getId(), obj_line_etudiant.getName(),
                                obj_line_etudiant.getSurname(), obj_line_etudiant.getAge(),
                                obj_line_etudiant.getGender(), obj_line_etudiant.getINE(), obj_line_etudiant.getPromo(),
                                obj_line_etudiant.getNotes(), obj_line_etudiant.getContrat(),
                                obj_line_etudiant.getWorkingHours()));
                    } else {
                        personnes.add(new Salarie(obj_line_etudiant.getId(), obj_line_etudiant.getName(),
                                obj_line_etudiant.getSurname(), obj_line_etudiant.getAge(),
                                obj_line_etudiant.getGender(), obj_line_etudiant.getContrat(),
                                obj_line_etudiant.getWorkingHours()));
                    }
                } else {
                    if (!obj_line_etudiant.getINE().equals("null")) {
                        personnes.add(new Etudiant(obj_line_etudiant.getId(), obj_line_etudiant.getName(),
                                obj_line_etudiant.getSurname(), obj_line_etudiant.getAge(),
                                obj_line_etudiant.getGender(), obj_line_etudiant.getINE(), obj_line_etudiant.getPromo(),
                                obj_line_etudiant.getNotes()));
                    } else {
                        personnes.add(new Personne(obj_line_etudiant.getId(), obj_line_etudiant.getName(),
                                obj_line_etudiant.getSurname(), obj_line_etudiant.getAge(),
                                obj_line_etudiant.getGender()));
                    }
                }

            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured...");
        }

        try (BufferedReader reader2 = new BufferedReader(new FileReader(csvFile2))) {
            String line2;
            ReadCSVChambre obj_line_chambre;
            reader2.readLine(); // Dropping first line
            // On lit le deuxième CSV pour remplir notre ArrayList de résidence (qui contiennent des chambres)
            while ((line2 = reader2.readLine()) != null) { // reader.readLine()
                obj_line_chambre = new ReadCSVChambre(line2.split(";")); // spliting here but it was a choice
                if (residences.size() == 0) {
                    residences.add(new Residence(obj_line_chambre.getAddress(), obj_line_chambre.getCity(),
                            obj_line_chambre.getCity_code(), obj_line_chambre.getResidence()));
                    residences.getLast().getChambre().add(new Chambre(obj_line_chambre.getId(),
                            obj_line_chambre.getSurface(), obj_line_chambre.getCreation_date(),
                            obj_line_chambre.getLatest_renovation_date(), obj_line_chambre.getNb_locations(),
                            obj_line_chambre.getScores(), obj_line_chambre.getName()));
                } else {
                    for (int i = 0; i < residences.size(); i++) {
                        if (obj_line_chambre.getResidence().equals(residences.get(i).getNameResidence())) {
                            residences.get(i).getChambre().add(new Chambre(obj_line_chambre.getId(),
                                    obj_line_chambre.getSurface(), obj_line_chambre.getCreation_date(),
                                    obj_line_chambre.getLatest_renovation_date(), obj_line_chambre.getNb_locations(),
                                    obj_line_chambre.getScores(), obj_line_chambre.getName()));
                            residenceExistante = true;
                        }
                    }
                    if (!residenceExistante) {
                        residences.add(new Residence(obj_line_chambre.getAddress(), obj_line_chambre.getCity(),
                                obj_line_chambre.getCity_code(), obj_line_chambre.getResidence()));
                        residences.getLast().getChambre().add(new Chambre(obj_line_chambre.getId(),
                                obj_line_chambre.getSurface(), obj_line_chambre.getCreation_date(),
                                obj_line_chambre.getLatest_renovation_date(), obj_line_chambre.getNb_locations(),
                                obj_line_chambre.getScores(), obj_line_chambre.getName()));
                    }
                }
                residenceExistante = false;
            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured...");
        }

        // Création de notre variable de classement qui s'occupe de tous le systèmes d'attribution
        Classement classement = new Classement(residences, personnes);
        classement.classerChambre();
        // classement.afficherScoreChambre();
        classement.classerPersonne();
        // classement.afficherScoreEtudiant();
        // classement.attribuerChambre();
        // classement.whoHasRoomInOrder();
        // classement.WhichRoomHasStudentOrder();

        // personnes.get(0).liberer_chambre();

        for (int i = 0; i < residences.size(); i++) {
            for (int j = 0; j < residences.get(i).getChambre().size(); j++) {
                // residences.get(i).getChambre().get(j).afficherPersonne();
            }
        }

        for (int i = 0; i < personnes.size(); i++) {
            // personnes.get(i).afficherChambre();
        }

        String input = "-1";
        String inputChambre = "-1";
        Scanner ecriture = new Scanner(System.in);
        while (!input.equals("0")) { //-> Sinon, on quittera le programme
            //Menu permettant les différentes actions.
            System.out.println("0 - Quitter le programme.");
            System.out.println("1 - Classer et attribuer les chambres et les étudiants");
            System.out.println("2 - Afficher l'étudiant et sa chambre");
            System.out.println("3 - Afficher le score des étudiants ayant obtenu une chambre");
            System.out.println("4 - Afficher la chambre et son étudiant");
            System.out.println("5 - Afficher le score des chambre et l'étudiant qu'elles hébergent");
            System.out.println("6 - Libérer une chambre");
            System.out.println("7 - Ajouter une chambre");
            System.out.println("8 - Ajouter un étudiant");

            System.out.println(" ");
            System.out.print("Veuillez choisir une option : ");

            input = ecriture.nextLine();

            if (input.equals("1")) {
                // -> Classer les chambres.
                classement = new Classement(residences, personnes);
                classement.classerChambre();
                classement.classerPersonne();
                
                classement.attribuerChambre();
                System.out.println("Les chambres et les étudiants ont bien été classésS !");
            } else if (input.equals("2")) {
                // Afficher l'étudiant hébergé 
                for (int i = 0; i < personnes.size(); i++) {
                    personnes.get(i).afficherChambre();
                }
            } else if (input.equals("3")) {
                // Afficher le score des étudiants ayant obtenu une chambre
                classement.whoHasRoomInOrder();
            } else if (input.equals("4")) {
                //Afficher la chambre de chaque étudiant
                for (int i = 0; i < residences.size(); i++) {
                    for (int j = 0; j < residences.get(i).getChambre().size(); j++) {
                        residences.get(i).getChambre().get(j).afficherPersonne();
                    }
                }
            } else if (input.equals("5")) {
                //Afficher le score des chambre et l'étudiant qu'elles hébergent
                classement.whichRoomHasStudentOrder();
            } else if (input.equals("6")) {
                //Libérer une chambre
                System.out.print("Choisissez l'index de l'étudiant devant libérer sa chambre : ");
                inputChambre = ecriture.nextLine();

                if (personnes.get(Integer.parseInt(inputChambre)).getChambre() == null) {
                    System.out.println("Cet étudiant n'avait pas de chambre.");
                } else {
                    personnes.get(Integer.parseInt(inputChambre)).liberer_chambre();
                    System.out.println("Chambre libérée !");
                }
            } else if (input.equals("7")) {
                // Ajouter une chambre
                System.out.print("Donnez l'adresse de la résidence : ");
                String adresse = ecriture.nextLine();
                residenceExistante = false;
                int residenceTrouve = -1;
                String choix = "";
                for (int i = 0; i < residences.size(); i++) {
                    if (residences.get(i).getAddress().equals(adresse)) {
                        residenceExistante = true;
                        residenceTrouve = i;
                    }
                }
                if (residenceExistante == false) {
                    System.out.println("La résidence n'a pas été trouvé.");
                    System.out.println("Souhaitez vous en créer une, ou quitter la création ?");
                    System.out.println("0 - Quitter la création");
                    System.out.println("1 - Créer une résidence");
                    choix = ecriture.nextLine();
                }
                if (choix.equals("1")) {
                    System.out.print("Ville : ");
                    String ville = ecriture.nextLine();
                    System.out.print("Code Postal : ");
                    Integer code_postal = Integer.parseInt(ecriture.nextLine());
                    System.out.print("Nom : ");
                    String name = ecriture.nextLine();
                    
                    residences.add(new Residence(adresse, ville, code_postal, name));
                    if(residences.getLast().getAddress().equals(adresse)){
                        System.out.println("La résidence a bien été crée");
                    }
                    else{
                        System.out.println("Erreur lors de la création");
                    }
                }

                if (residences.getLast().getAddress().equals(adresse) || residenceExistante == true) {
                    System.out.print("ID de la chambre : ");
                    String ID = ecriture.nextLine();
                    System.out.print("Nom : ");
                    String nom = ecriture.nextLine();
                    System.out.print("Surface : ");
                    float surface = Float.parseFloat(ecriture.nextLine());
                    System.out.print("Date de création : ");
                    int date_creation = Integer.parseInt(ecriture.nextLine());
                    if(date_creation > Year.now().getValue()){
                        while(date_creation > Year.now().getValue()){
                            System.out.print("Date de création non valide, veuillez renseigner une date correct : ");
                            date_creation = Integer.parseInt(ecriture.nextLine());
                        }
                    }
                    System.out.print("Date de rénovation : ");
                    int date_renovation = Integer.parseInt(ecriture.nextLine());
                    if(date_renovation > Year.now().getValue() || date_renovation < date_creation){
                        while(date_renovation > Year.now().getValue() || date_renovation < date_creation){
                            System.out.print("Date de rénovation non valide, veuillez renseigner une date correct : ");
                            date_renovation = Integer.parseInt(ecriture.nextLine());
                        }
                    }
                    System.out.print("Nombre de location : ");
                    int nombre_location = Integer.parseInt(ecriture.nextLine());
                    int[] notesChambre = new int[nombre_location];

                    int index = 0;
                    while(index < nombre_location) {
                        System.out.print("Saisissez la note : " + (index+1) + " : ");
                        notesChambre[index] = Integer.parseInt(ecriture.nextLine());
                        if(notesChambre[index] > 5){
                            System.out.println("Veuillez renseigner une note inférieur ou égal à 5");
                        }else{
                            index++;
                        }
                    }
                    if (residenceTrouve != -1) {
                        residences.get(residenceTrouve).getChambre().add(new Chambre(ID, surface, date_creation,
                            date_renovation, nombre_location, notesChambre, nom));  
                            if (residences.get(residenceTrouve).getChambre().getLast().getName().equals(nom)) {
                                System.out.println("La chambre a bien été ajouté.");
                            } 
                            else {
                                System.out.println("Erreur lors de l'ajout.");
                            } 
                    }
                    else{
                        residences.getLast().getChambre().add(new Chambre(ID, surface, date_creation,
                        date_renovation, nombre_location, notesChambre, nom));
                        if (residences.getLast().getChambre().getLast().getName().equals(nom)) {
                            System.out.println("La chambre a bien été ajouté.");
                        } 
                        else {
                            System.out.println("Erreur lors de l'ajout.");
                        }
                    }
                }
            } else if (input.equals("8")) {
                //Ajouter un étudiant
                String contrat = "";
                String INE = "";
                int promo = 0;
                int[] notes = null;
                System.out.print("Donnez le prénom de l'étudiant : ");
                String name = ecriture.nextLine();
                System.out.print("Donnez le nom de l'étudiant : ");
                String surname = ecriture.nextLine();
                System.out.print("ID de l'étudiant : ");
                String id = ecriture.nextLine();
                System.out.print("Age de l'étudiant : ");
                int age = Integer.parseInt(ecriture.nextLine());
                System.out.print("Genre de l'étudiant : ");
                String gender = ecriture.nextLine();
                System.out.println("S - Salarié");
                System.out.println("P - Personne");
                System.out.println("E - Étudiant");
                System.out.println("ET - Étudiant Travailleur");
                System.out.print("Choisissez un type d'étudiant : ");
                String type = ecriture.nextLine();

                if(type.equals("S") || type.equals("ET")){
                    System.out.print("Numéro de contrat : ");
                    contrat = ecriture.nextLine();
                }
                if(type.equals("E") || type.equals("ET")) {
                    System.out.print("Numéro d'INE : ");
                    INE = ecriture.nextLine();
                    System.out.print("Année de promo : ");
                    promo = Integer.parseInt(ecriture.nextLine());
                    if(promo > 2027 || promo < 2025){
                        while(promo > 2027 || promo < 2025){
                            System.out.print("Promo non valide, veuillez renseigner une date correct : ");
                            promo = Integer.parseInt(ecriture.nextLine());
                        }
                    }
                    System.out.print("Nombre de notes : ");
                    int nbNotes = Integer.parseInt(ecriture.nextLine());
                    notes = new int[nbNotes];
                    int index = 0;
                    while (index<notes.length) {
                        System.out.print("Notes numéro " + (index+1) + " de l'étudiant : ");
                        notes[index] = Integer.parseInt(ecriture.nextLine());
                        if (notes[index] > 20 || notes[index] < 0) {
                            System.out.println("Note numéro " + (index+1) + " non valide, veuillez renseigner une promo valide.");
                        }
                        else{
                            index++;
                        }
                    }
                }

                if (type.equals("P")) {
                    personnes.add(new Personne(id, name, surname, age, gender)); 
                }
                if (type.equals("S")) {
                    personnes.add(new Salarie(id, name, surname, age, gender, contrat));
                }
                if (type.equals("E")) {
                    personnes.add(new Etudiant(id, name, surname, age, gender, INE, promo, notes));
                }
                if (type.equals("ET")) {
                    personnes.add(new EtudiantTravailleur(id, name, surname, age, gender, INE, promo, notes, contrat));
                }

                if (personnes.getLast().getName() == name) {
                    System.out.println("Etudiant bien créé");
                }
                else{
                    System.out.println("Erreur dans l'ajout.");
                }
            }
        }
        ecriture.close();
        // System.out.println(residences.size()); // Nombre de résidence dans le CSV (et
        // résultat attendu): 2
        // System.out.println(residences.get(0).getChambre().size()); // Nombre de
        // chambre dans la résidence 1
        // System.out.println(residences.get(1).getChambre().size()); // Nombre de
        // chambre dans la résidence 2

        // personnes.get(103).infoEtudiant(); // Travail + Etudiant
        // personnes.get(0).infoEtudiant(); // Etudiant
        // personnes.get(45).infoEtudiant(); // Travail
        // personnes.get(104).infoEtudiant(); // Aucun des deux
    }
}
