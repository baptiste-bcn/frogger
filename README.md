# Frogger

## Sommaire

- [Installation de Maven sur Windows](#installation-de-maven-sur-windows)
- [Installation de Maven sur Linux](#installation-de-maven-sur-linux)
- [Exécuter le projet](#exécuter-le-projet)
- [Description](#description)
- [Fonctionnalités principales](#fonctionnalités-principales)
- [Fonctionnalités secondaires](#fonctionnalités-secondaires)

## Installation de Maven sur Windows

1. **Cloner le dépôt GitHub :**

    ```bash
    git clone https://github.com/baptiste-bcn/frogger
    ```

2. **Installation de Maven :**

   S'assurer que Maven est installé sur la machine. Si ce n'est pas le cas, le télécharger et l'installer
   depuis [le site officiel de Maven](https://maven.apache.org/).

    - Télécharger la version **apache-maven-3.9.9-bin.zip**.
    - Dans `Téléchargements`, extraire l'archive .zip vers `apache-maven-3.9.9\bin`.
    - Copier le dossier obtenu (`apache-maven-3.9.9-bin`) dans `C:\Program Files` (Programmes) et aussi dans `C:\Program Files (x86)` (Programmes x86) pour être sûr que ça fonctionne.
    - Dans `Téléchargements`, ouvrir le dossier `apache-maven-3.9.9-bin` jusqu'au répertoire `bin`.
    - Copier le chemin d'accès en haut (devrait ressembler à `C:\Users\name\Downloads\apache-maven-3.9.9-bin\apache-maven-3.9.9\bin`).
    - Ajouter à la variable d'environnement `PATH` le chemin `C:\Program Files\Maven\apache-maven-3.9.9\bin`.
    - Pour se faire, rechercher (`Windows + S`) "Variables d'environnement" puis cliquer sur "Modifier les variables d’environnement système".
    - Cliquer sur "Variables d'environnement".
    - Cliquer sur celle qui a le nom "Path" dans la partie "Variables systèmes" puis "Modifier".
    - En ajouter une nouvelle via "Nouveau" puis coller le chemin d'accès à l'endroit où vous l'avez télécharger : `C:\Users\name\Downloads\apache-maven-3.9.9-bin\apache-maven-3.9.9\bin` puis cliquer sur "OK".
    
    - Vérifier l'installation en ouvrant un terminal et en exécutant la commande suivante :
    ```bash
    mvn -v
    ```

## Installation de Maven sur Linux

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
