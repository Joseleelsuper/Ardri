#!/bin/bash

# 
#  Nombre: ejecutar_textui.sh
#  Descripción: Script para ejecutar el juego Tafl con consola de comandos.
#  Organización: Universidad de Burgos
#  @autor: José Gallardo Caballero
#  @fecha: 26/11/2023
#  @versión: 1.0
#  @licencia: BY-NC-SA
# 

# Pregunta si se quiere jugar al Brandubh o al Ardri
while true; do
  read -p "¿Qué juego quieres jugar? (Brandubh o Ardri): " juego
  juego=$(echo "$juego" | tr '[:upper:]' '[:lower:]')

  if [ "$juego" == "brandubh" ] || [ "$juego" == "ardri" ]; then
    break
  elif [ -z "$juego" ]; then
    juego="brandubh"
    break
  else
    echo "Por favor, elige Brandubh o Ardri."
  fi
done

# Ejecutar el juego mostrando la información de la consola
java -cp ./bin:./lib/tafl-gui-lib-2.0.0.jar tafl.textui.Tafl $juego

# Pausar la consola para poder ver los resultados. 
read -p "Presione cualquier tecla para continuar . . ."