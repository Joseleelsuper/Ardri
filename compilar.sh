#
#  Nombre: compilar.sh
#  Descripción: ¡Script para compilar el juego Tafl
#  Organización: Universidad de Burgos
#  @author: José Gallardo Caballero
#  @date: 26/11/2023
#  @version: 1.0
#  @license: BY-NC-SA
#

#!/bin/bash

# Crear el directorio bin si no existe
if [ ! -d "bin" ]; then
  mkdir bin
fi

# Compilar el juego
javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./src \
./src/tafl/util/*.java \
./src/tafl/modelo/*.java \
./src/tafl/control/*.java \
./src/tafl/textui/*.java \
./src/tafl/excepcion/*.java

# Pausar la consola para poder ver los resultados.
read -p "Press any key to continue . . ."