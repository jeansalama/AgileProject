# Un projet Agile : Calculer des remboursements de réclamations de soins de santé

## Description

Dans le cadre du cours INF2015 (UQAM) "Développement de logiciels dans un environnement Agile", mes coéquipiers et moi-même avons développé un logiciel de calcul de remboursements en Java.

Ce logiciel a été développé de manière itérative et incrémental en suivant les énoncés et spécifications (que vous trouverez [ici](https://github.com/jeansalama/AgileProject/tree/master/%C3%89nonc%C3%A9s)).

## Fonctionnement 

Ce logiciel utilise des fichiers JSON pour les réclamations et les remboursements. Ceci, afin qu'il puisse être utiliser en back-end pour un futur site web.

Pour la construction automatisée, nous utilisons Maven avec des tests JUnit.

Afin d'executer l'application refunds.jar, le fichier [Contrats.json](https://github.com/jeansalama/AgileProject/blob/master/Contrats.json) doit être à la racine de l'application. Un nouveau build copiera ce fichier à la racine du dossier contenant l'executable.
Sinon, copiez-le à la [main](https://github.com/jeansalama/AgileProject.git)
