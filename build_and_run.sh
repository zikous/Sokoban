#!/bin/bash

# Répertoires
SRC_DIR="src"             # Répertoire original
BUILD_DIR="build"         # Répertoire de build
CLEAN_DIR="$BUILD_DIR/clean_src"   # Répertoire nettoyé
BIN_DIR="$BUILD_DIR/bin"  # Répertoire des fichiers compilés

# Nettoyage des anciens fichiers
echo "Nettoyage des répertoires..."
rm -rf "$BUILD_DIR"       # Supprimer le dossier de build complet
mkdir -p "$CLEAN_DIR" "$BIN_DIR"  # Créer les répertoires nécessaires

# Copier et nettoyer les fichiers Java
echo "Copie et nettoyage des fichiers Java..."
cp -r "$SRC_DIR"/* "$CLEAN_DIR"/
find "$CLEAN_DIR" -name "*.java" -exec sed -i '/import com\.modeliosoft\.modelio\.javadesigner\.annotations\.objid;/d' {} \;
find "$CLEAN_DIR" -name "*.java" -exec sed -i '/@objid/d' {} \;

# Compilation des fichiers nettoyés
echo "Compilation des fichiers Java..."
find "$CLEAN_DIR" -name "*.java" > "$BUILD_DIR/sources.txt"
javac -d "$BIN_DIR" @"$BUILD_DIR/sources.txt"

# Vérification de la compilation
if [ $? -eq 0 ]; then
    echo "Compilation réussie ! Les fichiers compilés sont dans '$BIN_DIR'."
else
    echo "Erreur de compilation. Vérifiez vos fichiers."
    exit 1
fi

# Lancement du jeu
MAIN_CLASS="main.MainSokoban"  # Remplace par le package complet de ta classe
echo "Lancement de l'application..."
java -cp "$BIN_DIR" "$MAIN_CLASS"

# Nettoyage temporaire
rm "$BUILD_DIR/sources.txt"  # Supprimer le fichier temporaire
