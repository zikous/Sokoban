#!/bin/bash

# Répertoires
WORKING_DIR="different_files"     # Répertoire principal pour les opérations
SRC_DIR="src"                    # Répertoire original contenant les sources
CLEAN_DIR="$WORKING_DIR/src"  # Répertoire nettoyé
BIN_DIR="$WORKING_DIR/bin"       # Répertoire des fichiers compilés

# Création et nettoyage des répertoires
echo "Création du répertoire de travail '$WORKING_DIR'..."
rm -rf "$WORKING_DIR"            # Supprimer le dossier précédent s'il existe
mkdir -p "$CLEAN_DIR" "$BIN_DIR" # Créer les répertoires nécessaires

# Copier et nettoyer les fichiers Java
echo "Copie et nettoyage des fichiers Java dans '$CLEAN_DIR'..."
cp -r "$SRC_DIR"/* "$CLEAN_DIR"/
find "$CLEAN_DIR" -name "*.java" -exec sed -i '/import com\.modeliosoft\.modelio\.javadesigner\.annotations\.objid;/d' {} \;
find "$CLEAN_DIR" -name "*.java" -exec sed -i '/@objid/d' {} \;

# Compilation des fichiers nettoyés
echo "Compilation des fichiers Java dans '$BIN_DIR'..."
find "$CLEAN_DIR" -name "*.java" > "$WORKING_DIR/sources.txt"
javac -d "$BIN_DIR" @"$WORKING_DIR/sources.txt"

# Vérification de la compilation
if [ $? -eq 0 ]; then
    echo "Compilation réussie ! Les fichiers compilés sont dans '$BIN_DIR'."
else
    echo "Erreur de compilation. Vérifiez vos fichiers."
    exit 1
fi

# Lancement de l'application
MAIN_CLASS="main.MainSokoban"  # Remplace par le package complet de ta classe
echo "Lancement de l'application..."
java -cp "$BIN_DIR" "$MAIN_CLASS"

# Nettoyage des fichiers temporaires
echo "Nettoyage des fichiers temporaires..."
rm "$WORKING_DIR/sources.txt" # Supprime le fichier temporaire sources.txt
