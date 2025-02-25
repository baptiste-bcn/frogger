# Frogger

## Sommaire

- [Installation et lancement du projet](#installation-et-lancement-du-projet)
- [Description](#description)
- [Fonctionnalités principales](#fonctionnalités-principales)
- [Fonctionnalités secondaires](#fonctionnalités-secondaires)

## Installation et lancement du projet

Pour cloner et lancer ce projet avec Maven, suivre les étapes ci-dessous :

1. **Cloner le dépôt GitHub :**

    ```bash
    git clone https://github.com/baptiste-bcn/frogger
    ```

2. **Installation de Maven :**

   S'assurer que Maven est installé sur la machine. Si ce n'est pas le cas, le télécharger et l'installer
   depuis [le site officiel de Maven](https://maven.apache.org/).

    - Télécharger la version **apache-maven-3.9.9-bin.zip**.
    - Extraire les fichiers dans `C:\Program Files\Maven`.
    - Ajouter à la variable d'environnement `PATH` le chemin `C:\Program Files\Maven\apache-maven-3.9.9\bin`.
    - Vérifier l'installation en ouvrant un terminal et en exécutant la commande suivante :
    ```bash
    mvn -v
    ```

3. **Exécuter le projet :**

   Accéder au répertoire racine du projet :

   ```bash
   cd .\frogger\
   ```

   Nettoyer et installer les dépendances :

   ```bash
   mvn clean install
   ```

   Lancer le projet :

   ```bash
   mvn javafx:run
   ```

## Description

Implémentation d'un jeu de type Frogger. Le joueur contrôle un personnage en temps réel à l'aide des touches
directionnelles, et doit traverser une grille où se déplacent des obstacles. Pour plus d'informations sur le jeu d'
origine, consulter [la page Wikipédia](https://en.wikipedia.org/wiki/Frogger).

## Fonctionnalités principales

Le thème et les règles du jeu d'origine peuvent être modifiés, à condition que les critères suivants soient respectés :

- Le personnage doit traverser la grille de manière verticale.
- Les obstacles ou plateformes doivent se déplacer horizontalement.

Les fonctionnalités suivantes doivent impérativement être implémentées :

- Une interface claire et contrôlable en temps réel à l'aide du clavier.
- Un système de scoring.

## Fonctionnalités secondaires

Les fonctionnalités suivantes sont facultatives et indépendantes les unes des autres :

- Sauvegarder les scores les plus élevés.
- Mode Versus : deux joueurs jouent simultanément, et le premier à atteindre l'objectif remporte la partie.
- Toute autre fonctionnalité jugée utile.