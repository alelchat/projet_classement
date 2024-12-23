DOCUMENTATION DU PROJET | ANTHONY LELCHAT IAI1


En voyant ce sujet, j’ai d’abord commencé à mettre en place les classes que je considérais comme utile pour la suite du projet, ce qui m’a permit d’être assez rapide lors de l’implémentation dans le code, j’ai ensuite avancer chaque classe une par une en faisant quelques tests dans mon main pour finir par la classe qui est probablement la plus conséquente, « Classement ». Mon but a travers ce projet était de confirmer mes compétences en programmation orienté objet ayant déjà pu en faire il y a plus d’un an (C++).

COMPILATION et EXECUTION :

Tout d’abord, il faut se placer dans le dossier « Projet_Classement_Anthony » pour que toutes les commandes puissent fonctionner.

Pour compiler le main, il suffit de taper « javac MainProjet.java »
Pour executer le main, il suffit de taper « java MainProjet »

Pour compiler le fichier de test, il suffit de taper « javac TestProjet.java »
Pour executer le fichier de test, il suffit de taper « java -ea TestProjet » (ea pour « enable assertion » permet au fichier test de renvoyer une erreur si un de mes tests échoue »)

MODÉLISATION :

Diagramme de classe (voir dans le dossier le fichier PNG « diagramme de classe PROJET »)

Pour ce diagramme, on a une classe Personne avec ses attributs et ses méthodes, une classe Étudiant et Salarié qui hérite de cette personne et un ÉtudiantTravailleur qui hérite des propriété d’un Étudiant. Au départ, j’avais prévu d’utiliser la classe Personne comme une classe abstraite, mais ayant vu que des salarié (donc des personnes qui ne sont pas en étude) pouvait tenter de s’inscrire dans la liste des demandeurs, je me suis dit que je pouvais utiliser la classe Personne comme quelqu’un qui ne travaille pas et qui n’étudie pas, mais qui demande quand même un logement (cela fait notamment partie des pistes d’évolution du programme). Je voulais aussi que ÉtudiantTravailleur hérite à la fois de la classe Salarié et de la classe Étudiant, mais ce n’était pas possible. J’ai décidé aussi d’avoir une classe Résidence qui contient des instances de classe Chambre (selon moi, si on supprime la résidence, on a plus les chambres qui vont avec) ce qui rajoute une difficulté supplémentaires pour mon programme (on doit passer par les résidences, en sachant qu’il y en a plusieurs, pour agir sur les chambres) mais qui ajoute aussi une touche de réalisme. On a ainsi une classe Classement qui possède des instances de Résidences et de Personnes et qui permet de calculer les scores des chambres et des personnes (sans qu’ils puissent y avoir accès) et qui permet d’attribuer les chambres. Une liaison entre les chambres et les personnes est présente, elle n’est cependant changé que quand la classe Classement attribue les chambres.

Diagramme d’état-transition (voir dans le dossier le fichier PNG « diagramme d’état PROJET »)

Pour ce diagramme, je m’y suis pris plus à la fin de mon projet car je n’avais aucune idée de comment le réalisé (je sais comment en faire un, mais nous en avions jamais fait sur un système aussi gros auparavant), on commence tout simplement par l’exécution du programme qui nous emmène au menu principal, puis, selon l’input que l’utilisateur rentre, on rentre dans différents états (attribuer les chambres, quitter le programme, affichage des chambres, étudiant etc) et enfin, lorsque l’état est terminé (sauf pour quitter le programme, où on ne re-boucle pas) on rentre dans l’état « Input Réalisé » qui permet de revenir au menu principale (cet état me permet juste de rentre le diagramme compréhensible sans qu’il y puisse y avoir des flèches à tout va.) Il faut aussi prendre en compte que chaque état réalise ses fonction bien particulière, par exemple, quand on rentre dans l’état « Ajouter un Étudiant » alors on demande les informations nécessaire à l’utilisateur, mais représenter tous ces états de façon précise et minutieuse aurait probablement pris énormément de temps pour quelque chose de très peu compréhensible. Il existe des erreurs qui pourrait faire sortir du code si par exemple l’utilisateur décide de rentrer des données qui ne sont pas forcément ceux qui étaient attendu, néanmoins, j’ai essayé de gérer le maximum d’erreurs possibles, mais si l'utilisateur décide de rentrer des données qui ne sont pas attendu, comme par exemple une chaine de caractère à la place d'un nombre, le programme va nécessairement planter.

TEST RÉALISÉ :

Pour le moment, j’ai pu réalisé 3 tests qui utilise toutes les classes (Personne, Étudiant, ÉtudiantTravailleur, Salarié, Chambre, Résidence et Classement) ce qui démontre qu’elles sont bien fonctionnelles (mais très probablement perfectibles) ces 3 tests vérifient que :
- Lorsqu’un seul des Salarie et des Personnes candidatent, ceux ci n’obtiennent pas de chambres.
- Lorsqu’on ajoute un Étudiant, seul ce dernier obtient une chambre
- Lorsqu’on a deux chambres et deux étudiants, on vérifie que chaque étudiant possède la bonne chambre.

Si j’avais eu plus de temps, j’aurais très probablement changé l’arborescence de mon projet, qui pour l’instant se limite au stricte minimum : un package, un fichier de main et un fichier de test, j’aurais très probablement aussi fait plus de tests mais je pense (j’espère) que ces 3 tests sont suffisants car ils permettent de vérifier que chaque classe est correctement instanciable et fonctionne, j’aurais aussi essayé de mettre dans les points des EtudiantTravailleur leur temps de travail, que je n’ai pas pu utiliser car trop complexe lors de l’implémentation.

Pour finir, je pense qu’avec plus de temps, j’aurais cherché à corriger les problèmes liés au promo, en fait, pour l’instant j’ai dû mettre une date fixe (promo == 2027) car si je fonctionne avec un Year.now().getValue(), je risque d’avoir des problème lorsque nous passerons en 2025 (il cherchera si promo == 2026,2027,2028 mais les promos en 2025 seront « oublié » et la promo en 2028 n’est jamais présente) je pense que le problème n’est pas si complexe, mais c’est quelques choses qui peut être largement amélioré.
