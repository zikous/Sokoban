
# Projet Sokoban

## Description

Ce projet est une implémentation du jeu classique Sokoban. Il est conçu pour être compatible avec les environnements où les annotations Modelio Java Designer sont bien configurées, comme pour des projets scolaires. De plus, si vous êtes intéressé par le code ou souhaitez l'utiliser dans un environnement Java standard, un script bash est inclus pour simplifier la configuration.

## Fonctionnalités

- Compatible avec les environnements configurés pour Modelio Java Designer.
- Portable dans n'importe quel environnement Java standard.
- Inclus un script bash pour nettoyer, compiler et exécuter le projet sans dépendances spécifiques.

## Prérequis

- Kit de développement Java (JDK) 8 ou supérieur.
- Bash (pour exécuter le script fourni).
- Si vous utilisez des fonctionnalités spécifiques à Modelio, assurez-vous que votre environnement est correctement configuré.

## Utilisation

### Pour les environnements configurés pour Modelio
Le projet peut être directement ouvert et utilisé dans des environnements comme Modelio Java Designer. Assurez-vous que les annotations sont bien configurées pour ce projet.

### Pour les environnements Java standard
Si vous souhaitez utiliser le projet hors d'un environnement spécifique à Modelio :

1. Donnez les droits d'exécution au script bash :
   ```bash
   chmod +x build_and_run.sh
   ```
2. Lancez le script pour nettoyer, compiler et préparer le projet :
   ```bash
   ./build_and_run.sh
   ```
3. Le script créera un répertoire appelé `different_files` contenant tous les fichiers nettoyés et compilés.
4. Naviguez dans le dossier `different_files` pour trouver le projet prêt à être utilisé dans n'importe quel environnement Java.

## Exécution du jeu

1. Après avoir exécuté le script, le jeu se lancera automatiquement si la compilation est réussie.
2. Vous pouvez également trouver les fichiers nettoyés et compilés dans le répertoire `different_files`.

## Remarques

- Le script bash fourni supprime les annotations et dépendances spécifiques à Modelio, rendant le projet plus portable dans un autre dossier.
- Cette configuration garantit que le projet peut être exécuté dans n'importe quel environnement avec un JDK compatible.

## Contact

N'hésitez pas à explorer, modifier ou utiliser ce projet à des fins éducatives. Toute contribution est la bienvenue !
