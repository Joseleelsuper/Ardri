#!/bin/bash

# 
#  Nombre: documentar.sh
#  Descripción: Script para documentar el juego Tafl
#  Organización: Universidad de Burgos
#  @autor: José Gallardo Caballero
#  @fecha: 26/11/2023
#  @versión: 1.0
#  @licencia: MIT
# 

# Documentar el juego
javadoc -author -version -private \
-encoding UTF-8 -charset UTF-8 \
-sourcepath ./src -d doc -classpath ./lib/*:./bin \
-docencoding UTF-8 \
-link https://docs.oracle.com/en/java/javase/20/docs/api/ \
-subpackages tafl

# Pausar la consola para poder ver los resultados.
read -p "Presione cualquier tecla para continuar . . ."