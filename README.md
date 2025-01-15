# Frogger

## Installation et Lancement du Projet

Pour cloner et lancer ce projet avec Maven, suivez les étapes ci-dessous :

1. **Cloner le dépôt GitHub :**
    ```bash
    git clone https://github.com/votre-utilisateur/frogger.git
    cd frogger
    ```

2. **Construire le projet avec Maven :**
    Assurez-vous d'avoir Maven installé sur votre machine. Si ce n'est pas le cas, vous pouvez le télécharger et l'installer depuis [le site officiel de Maven](https://maven.apache.org/).

    ```bash
    mvn clean install
    ```

3. **Exécuter le projet :**
    Une fois la construction terminée, vous pouvez exécuter le projet en utilisant la commande suivante :
    ```bash
    mvn exec:java -Dexec.mainClass="com.votrepackage.Main"
    ```

    Remplacez `com.votrepackage.Main` par le chemin de votre classe principale.

Vous devriez maintenant être en mesure de voir le jeu Frogger s'exécuter.

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