# Frogger

## Sommaire

- [Installation et Lancement du Projet](#installation-et-lancement-du-projet)
- [Description](#description)
- [Fonctionnalités principales](#fonctionnalités-principales)
- [Fonctionnalités secondaires](#fonctionnalités-secondaires)

## Installation et Lancement du Projet

Pour cloner et lancer ce projet avec Maven, suivez les étapes ci-dessous :

1. **Cloner le dépôt GitHub :**

    ```bash
    git clone https://github.com/baptiste-bcn/frogger
    ```

2. **Installation de Maven :**

    Assurez-vous d'avoir Maven installé sur votre machine. Si ce n'est pas le cas, vous pouvez le télécharger et l'installer depuis [le site officiel de Maven](https://maven.apache.org/).

    - Installez la version **apache-maven-3.9.9-bin.zip**. 
    - Extrayez les fichiers dans `C:\Program Files\Maven`. 
    - Ajoutez au `PATH` la variable d'environnement `C:\Program Files\Maven\apache-maven-3.9.9\bin`. 
    - Vérifiez que l'installation s'est bien effectuée en ouvrant votre terminal et en exécutant :
    ```bash
    mvn -v
    ```

3. **Exécuter le projet :**

    Naviguer dans le fichier racine du projet :
   
   ```bash
   cd .\frogger\
   ```
   
   Nettoyez et installez les dépendances :
        
   ```bash
   mvn clean install
   ```
    Exécutez le projet :
        
   ```bash
   mvn javafx:run
   ```


## Description

Implémentation d’un jeu de type Frogger : le joueur contrôle en temps réel un personnage via les touches
multidirectionnelles, et doit traverser une grille sur laquelle se déplacent des obstacles. Information sur le jeu
d’origine sur [Wikipédia](https://en.wikipedia.org/wiki/Frogger).


## Fonctionnalités principales

Vous êtes libre de modifier le thème et les règles du jeu d’origine tant que :

- Le personnage doit traverser la grille verticalement.
- Les obstacles ou plateformes se déplacent horizontalement.

Les fonctionnalités suivantes doivent être toutes implémentées :

- Une interface lisible et contrôlable en temps réel au clavier.
- Système de scoring.


## Fonctionnalités secondaires

Les fonctionnalités suivantes sont facultatives et indépendantes les unes des autres :

- Sauvegarde des scores les plus élevés.
- Mode Versus : 2 joueurs jouent simultanément, le premier à atteindre l’objectif gagne la partie.
- Toute autre fonctionnalité que vous jugerez utile. 
