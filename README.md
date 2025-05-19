# Frogger

## Sommaire

- [Installation de Java et Maven sur Windows](#installation-de-java-et-maven-sur-windows)
- [Installation de Java et Maven sur Linux](#installation-de-java-et-maven-sur-linux)
- [Exécuter le projet](#exécuter-le-projet)
- [Description](#description)
- [Fonctionnalités principales](#fonctionnalités-principales)
- [Fonctionnalités secondaires](#fonctionnalités-secondaires)

## Installation de Java et Maven sur Windows

1. **Cloner le dépôt GitHub :**

    ```bash
    git clone https://github.com/baptiste-bcn/frogger
    ```
2. **[Installer JDK via le lien suivant](https://www.oracle.com/fr/java/technologies/downloads/#jdk24-windows)**
- Séléctionner la version `x64 Installer`.
- Après l'installation, ouvrir un terminal (`Windows + R` puis `cmd`) et vérifier l'installation avec `java -version`.

3. **Exécuter le projet :**

    ```bash
    java -jar .\frogger-1.0-SNAPSHOT.jar
    ```

## Installation de Java et Maven sur Linux

1. **Cloner le dépôt GitHub :**

    ```bash
    git clone https://github.com/baptiste-bcn/frogger
    ```

2. **Installer Maven + JDK :**
   
    ```bash
    sudo apt update && sudo apt install default-jdk maven
    ```
3. **Vérifier Java :**
   ```bash
    java -version
    ```
4. **Vérifier Maven :**
    ```bash
    mvn -v
    ```


## Exécuter le projet

   Accéder au répertoire racine du projet :

   ```bash
   cd ./frogger
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
