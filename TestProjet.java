import java.util.ArrayList;

import projetLotterie.*;
public class TestProjet {
    public static void main(String[] args) {
        ArrayList<Residence> residences = new ArrayList<>();
        ArrayList<Personne> personnes = new ArrayList<>();

        int[] notes = {4,4,4};
        int[] notes2 = {15,16,17};

        residences.add(new Residence("Rue du Test","Lannion", 22300, "Test"));
        residences.get(0).getChambre().add(new Chambre("hdhd", 14, 2023, 2024, 3, notes, "B01"));
        residences.get(0).getChambre().add(new Chambre("hdhd", 8, 2017, 2018, 3, notes, "B02"));

        personnes.add(new Personne("ytue", "DUPONT", "MAURICE"));
        personnes.add(new Salarie("dd", "THOMAS", "Paul", "jfjhffjk"));

        Classement classement = (new Classement(residences, personnes));

        //Test 1 : Aucun étudiant, donc aucune chambre n'est attribué.

        classement.classerChambre();
        classement.classerPersonne();
        classement.attribuerChambre();

        boolean test1 = true;

        if(residences.get(0).getChambre().get(0).getPersonne() != null 
        || residences.get(0).getChambre().get(1).getPersonne() != null){
            test1 = false;
        }
        assert test1 == true : "Le test 1 a échoué";


        //Test 2 : Pas assez "d'étudiant" car deux personnes n'ayant pas le droit aux résidences universitaire
        //La chambre B01 est meilleur que la chambre B02 (même notes, mais une est plus grande et plus récente.)

        personnes.add(new Etudiant("yuyu", "DURANT", "Emilie", "hdhdhdhd", 2027, notes2));

        classement = (new Classement(residences, personnes));
        classement.classerChambre();
        classement.classerPersonne();
        classement.attribuerChambre();

        boolean test2 = true;

        if(!personnes.get(2).getChambre().getName().equals(residences.get(0).getChambre().get(0).getName()) 
        || residences.get(0).getChambre().get(1).getPersonne() != null){
            test2 = false;
        }
        assert test2 == true : "Le test 2 a échoué";

        //Test 3 : L'étudiant DURANT Emilie a les mêmes caractéristique que PAUL Emilie sauf l'année de promo (et on privéligie les nouveaux) donc
        //DURANT Emilie est censé obtenir la première chambre (la meilleure) et PAUL Emilie est censé obtenir la deuxième.)

        personnes.add(new Etudiant("yuyu", "PAUL", "Emilie", "hdhdhdhd", 2025, notes2));

        classement = (new Classement(residences, personnes));
        classement.classerChambre();
        classement.classerPersonne();
        classement.attribuerChambre();

        boolean test3 = true;

        if(!personnes.get(2).getChambre().getName().equals(residences.get(0).getChambre().get(0).getName()) 
        || !personnes.get(3).getChambre().getName().equals(residences.get(0).getChambre().get(1).getName())){
            test3 = false;
        }
        assert test3 == true : "Le test 3 a échoué";

        
    }
}
