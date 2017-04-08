# Style et normes de condification du projet :

* Le style Java (par défaut) est utilisé pour l'ensemble de la codification. 
* Le "beautifier" de NetBeans (8.x) est utilisé afin d'uniformiser le format du code source au sein de l'équipe.
* Les méthodes sont d'un maximum de 10 lignes.
* Les lignes sont d'un maximum de 80 caractères.
* La langue utilisée pour la codification des variables et des méthodes est le français:
  * Sauf en ce qui concerne les "getters", "setters", la méthode toString() et la classe Utf8File.
* Les attributs et variables de classes déclarés au début de celle-ci.
* Les variables locales sont déclarées au début des méthodes.
* La nomenclature des méthodes est *verbeComplement* ou *nomComplement* (Exemple: afficherTableau() ou methodeValide()).
  * La nomenclature doit reflété de façon évidente la fonctionnalité de la méthode
* La nomenclature des tests unitaire commence toujour par «test» et est suivi du nom de la méthode testée, puis d'une description en quelques mots de ce qui est testé : *testNomDeLaMethodeTesteeDescriptionDeCeQuiEstTeste* (Exemple: testValiderDateAvecDateInvalide)  
* Seuls les @params, @return et @throws sont utilisés en JavaDoc pour commenter les méthodes (au besoin), mais les "getters", les "setters" ainsi que la méthode toString() n'ont jamais de JavaDoc.
