package projetLotterie;

import java.time.Year;
import java.util.ArrayList;

public class Classement {
    private ArrayList<ArrayList<Float>> scores_chambres = new ArrayList<>(new ArrayList<>());
    private ArrayList<Float> scores_etudiant = new ArrayList<>();
    private ArrayList<Residence> residences = new ArrayList<>();
    private ArrayList<Personne> personnes = new ArrayList<>();
    private ArrayList<Integer> scores_etudiant_order = new ArrayList<>();
    private ArrayList<Integer> index_residence_order = new ArrayList<>();
    private ArrayList<Integer> index_chambre_order = new ArrayList<>();

    // Toute ces informations sont variables, on peut en rajouter donc on a besoin d'ArrayList

    public Classement(ArrayList<Residence> residences, ArrayList<Personne> personnes){
        this.residences = residences;
        this.personnes = personnes;
        int i = 0;
        while (i != residences.size()) {
            scores_chambres.add(new ArrayList<Float>()); //Permet d'initiliser notre nombre de résidence, ce qui fait qu'on peut en rajouter par la suite.
            i++; 
        }
    }
    public void classerChambre(){

        // Nombre de points sur 100 qu'on ramène à une note sur 5 (division par 20), CRITERE :
        // Date de création : 10 points
        // Date de rénovation : 25 points
        // Moyenne des anciennes notes : 40 points
        // Surface : 25 points
        // Les notes ont le plus gros impact car c'est le plus représentatif, mais il faut prendre en compte aussi le reste de la chambre (de petits
        // plaisantins pourrait avoir noté la chambre extrêmement basse pour aucune raison justifiées.)
        float nb_points = 0.0f;
        int point_temp = 25;
        int score = 0;

        for (int c=0; c<residences.size(); c++){
            for (int i=0; i<residences.get(c).getChambre().size(); i++){
                Chambre chambre = residences.get(c).getChambre().get(i);
                // DATE DE CREATION : + la création est vieille, moins la qualité est théoriquement bonne
                if(Year.now().getValue() - chambre.getCreationDate() < 10){
                    nb_points += 10;
                }
                else if (Year.now().getValue() - chambre.getCreationDate() < 25) {
                    nb_points += 5; 
                }
                else if(Year.now().getValue() - chambre.getCreationDate() < 30){
                    nb_points += 2;
                }
    
                // DATE DE RENOVATION : J'ai décidé de d'attribuer ses points car une rénovation peut corriger pas mal de défaut.
                for(int j=2024; j>1999; j--){
                    if (j == chambre.getRenovationDate()) {
                        nb_points += point_temp;
                    }
                    point_temp--;
                }
                // SURFACE : Avoir de l'espace est généralement un atout indispensable pour le choix de la chambre
                if(chambre.getSurface() >= 11.0f){
                    nb_points += 25;
                }
                else if(chambre.getSurface() >= 10.0f){
                    nb_points += 20;
                }
                else if(chambre.getSurface() >= 9.00f) {
                    nb_points += 10;
                }
                else if(chambre.getSurface() >= 8.50f){
                    nb_points += 5;
                }
                // OLD SCORE : Moyenne de tous les scores laissés par les étudiants.
                if(chambre.getOldScore().length == 0){
                    nb_points += 10;
                }
                else{
                    for(int u = 0; u<chambre.getOldScore().length; u++){
                        score += chambre.getOldScore()[u];
                    }
                    nb_points += (float) score * 8/chambre.getNbLocations(); // Multiplication par 8 car on passe d'une note sur 5 à une note sur 40 (nb de points pour les scores)
                }
                scores_chambres.get(c).add(nb_points/20.0f); // On ramène la note globale sur 5
    
                nb_points = 0.0f;
                point_temp = 25;
                score = 0;
            }
        } 
    }
    public void afficherScoreChambre(){
        //Permet d'afficher le score de chaque chambre.
        for(int c=0; c<residences.size(); c++){
            System.out.println("Résidence " + c + " : ");
            for(int i=0; i<residences.get(c).getChambre().size(); i++){
                System.out.println("Chambre " + residences.get(c).getChambre().get(i).getName() + " : " + scores_chambres.get(c).get(i));
            }
        }
    }
    public void afficherScoreEtudiant(){
        //Permet d'afficher le score de chaque étudiant.
        for(int i=0; i<personnes.size(); i++){
            System.out.println(personnes.get(i).getName() + " " + personnes.get(i).getSurname() + " : " + scores_etudiant.get(i));
        }
    }

    public void classerPersonne(){
        //Nombre de points sur 100 qu'on ramène à une note sur 5 (division par 20), CRITERE :
        //Type d'étudiant : 30 points
        //Moyenne de leur note : 50 points 
        //Promo : 20 points

        float nb_points = 0.0f;
        int score = 0;

        for(int i = 0; i<personnes.size(); i++){
            //Si la personne est une "Personne" (quelqu'un qui ne travaille pas et n'étudie pas) ou un "Salarie" (quelqu'un qui travaille uniquement) alors
            //Leur point reste à 0, le but d'une résidence universitaire est que seul des étudiants puissent avoir leur chambre.

            if(personnes.get(i) instanceof Etudiant || personnes.get(i) instanceof EtudiantTravailleur){
                //TYPE D'ÉTUDIANT
                // Si c'est un Etudiant qui cumule un travail, il a déjà le droit à un bonus en terme de points.
                if (personnes.get(i) instanceof Etudiant) {
                    nb_points += 15;
                }
                else{
                    nb_points += 30;
                }

                Etudiant etudiant = (Etudiant) personnes.get(i);

                //MOYENNE DES NOTES
                if (etudiant.getNotes().length == 0) {
                    nb_points += 12.5;
                }
                else{
                    for(int j = 0; j < etudiant.getNotes().length; j++){
                        score += etudiant.getNotes()[j];
                    }
                    nb_points += (float) score*2.5 / etudiant.getNotes().length;
                }

                //PROMO DE L'ÉTUDIANT
                //Ce paramètre n'est pas géré dynamiquement, l'un des soucis que j'ai rencontré était que, si je faisait avec
                //la propriété Year que j'ai utilisé précédemment, il pourrait y avoir des problèmes de scores qui pourrait changer le score final lorsque
                //nous passerons en 2025, il y a probablement une solution simple mais cela fait partie des pistes d'améliorations.
                if(etudiant.getPromo() == 2027){
                    nb_points += 20;
                }
                else if(etudiant.getPromo() == 2026){
                    nb_points += 15;
                }
                else{
                    nb_points += 5;
                }
            }
        scores_etudiant.add(nb_points/20.0f);
        nb_points = 0.0f;
        score = 0;
        }
        
    }

    public void attribuerChambre(){
        int indexEtudiantMax = 0;
        boolean noMoreEtudiant = false;
        int indexChambreMax = 0;
        int index = 0;
        int indexResidenceMax = 0;
        int nb_chambres = 0;

        ArrayList<ArrayList<Float>> copie_score_chambre = new ArrayList<>();
        for(ArrayList<Float> score : scores_chambres){
            copie_score_chambre.add(new ArrayList<>(score));
        }

        ArrayList<Float> copie_score_etudiant = new ArrayList<>(scores_etudiant); // On copie les scores pour garder les originaux intacts

        for(int i = 0; i<residences.size();i++){

            for(int j = 0; j<residences.get(i).getChambre().size(); j++){
                nb_chambres++;
            }
        }

        while(noMoreEtudiant != true && index != nb_chambres) {
            // Tant qu'il y a des étudiant élibibles à des chambres et qu'il y a encore des chambres, alors on cherche l'index de la chambre ayant le meilleur score
            for(int i = 0; i<residences.size();i++){
                for(int j = 0; j<residences.get(i).getChambre().size(); j++){
                    if(copie_score_chambre.get(i).get(j) > copie_score_chambre.get(indexResidenceMax).get(indexChambreMax)){
                        indexResidenceMax = i;
                        indexChambreMax = j;
                    }
                }
            }

            // Tant qu'il y a des étudiant élibibles à des chambres et qu'il y a encore des chambres, alors on cherche l'index de l'étudiant ayant le meilleur score
            for(int c = 1; c<personnes.size();c++){
                if(copie_score_etudiant.get(c) > copie_score_etudiant.get(indexEtudiantMax)){
                    indexEtudiantMax = c;
                }
            }
            if(copie_score_etudiant.get(indexEtudiantMax) != 0){
                // Si c'était égal à 0, cela voudrait dire qu'on a récupéré un Salarié/une Personne ou quelqu'un de déjà classé : On a donc classé tout
                // le monde.
                personnes.get(indexEtudiantMax).setChambre(residences.get(indexResidenceMax).getChambre().get(indexChambreMax));
                residences.get(indexResidenceMax).getChambre().get(indexChambreMax).setPersonne(personnes.get(indexEtudiantMax));
                copie_score_chambre.get(indexResidenceMax).set(indexChambreMax, 0.0f);
                copie_score_etudiant.set(indexEtudiantMax, 0.0f); // On passe son score à 0 pour qu'il n'intervienne plus lors du classement
                scores_etudiant_order.add(indexEtudiantMax); // On ajoute son index dans le tableau de score étudiant (mit dans l'ordre)

                index_residence_order.add(indexResidenceMax);
                index_chambre_order.add(indexChambreMax);

                indexChambreMax = 0;
                indexEtudiantMax = 0;
                indexResidenceMax = 0;
            }
            else{
                noMoreEtudiant = true;
            }
            index++;
        }
    }
    public void afficherEtudiantAndScore(){
        // La fonction permet d'afficher les étudiant et leur score (mais pas dans l'ordre)
        for(int i=0; i<personnes.size(); i++){
            if (personnes.get(i).getChambre() != null) {
                System.out.println(personnes.get(i).getName() + " " + personnes.get(i).getSurname() + " possède la chambre " 
                + personnes.get(i).getChambre().getName() + " (son score est de : " + scores_etudiant.get(i) + ")");
            }
            else{
                System.out.println(personnes.get(i).getName() + " " + personnes.get(i).getSurname() + " n'a pas de chambre, (son score est de : " + scores_etudiant.get(i) + ")");
            }
        }
    }
    public void whoHasRoomInOrder(){
        // La fonction est capable d'afficher uniquement les étudiants ayant obtenu une chambre, mais ils sont mis dans l'ordre selon le score (qui est affiché)
        for(int i=0; i<scores_etudiant_order.size(); i++){
            System.out.println(personnes.get(scores_etudiant_order.get(i)).getName() + " " + personnes.get(scores_etudiant_order.get(i)).getSurname() + " possède la chambre " 
            + personnes.get(scores_etudiant_order.get(i)).getChambre().getName() + " (Score étudiant : " + scores_etudiant.get(scores_etudiant_order.get(i)) + ")");
        }
    }
    public void whichRoomHasStudentOrder(){
        // La fonction est capable d'afficher uniquement les chambres attribuées aux étudiants, mais elles sont dans l'ordre selon leur score (qui est affiché)
        for(int i=0; i<index_chambre_order.size(); i++){
            System.out.println("La chambre " + residences.get(index_residence_order.get(i)).getChambre().get(index_chambre_order.get(i)).getName() + " appartient à " 
            + residences.get(index_residence_order.get(i)).getChambre().get(index_chambre_order.get(i)).getPersonne().getName() + " " 
            + residences.get(index_residence_order.get(i)).getChambre().get(index_chambre_order.get(i)).getPersonne().getSurname()
            + "(Score chambre : " + scores_chambres.get(index_residence_order.get(i)).get(index_chambre_order.get(i)) + ")");
        }
    }
}