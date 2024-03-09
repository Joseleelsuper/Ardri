# Resumen del proyecto

Se trata de un proyecto en la asignatura de 2º Grado, **Metodología de la programación**, de la [**Universidad de Burgos**](https://www.ubu.es/) (UBU).  
Los juegos se llaman [Ardri](https://arcana-artesania.es/producto/ard-ri/) y [Brandubh](https://arcana-artesania.es/producto/brandubh/). Se tratan de dos juegos de mesa Vikingos.  
El objetivo era crear un videojuego utilizando **Java** y **Eclipse**.
El proyecto se puede ejecutar en modo texto, aunque en un futuro se intentará crear el modo gráfico de cero.

# Requerimientos
## Para Windows

Es necesario tener instalado [jdk-20.0.1_windows-x64_bin](https://download.oracle.com/java/20/archive/jdk-20.0.1_windows-x64_bin.exe) o superior para poder ejecutarlo.

## Para Linux

Es necesario tener instalado [jdk-20.0.1_linux-x64_bin](https://download.oracle.com/java/20/archive/jdk-20.0.1_linux-x64_bin.deb) o superior para poder ejecutarlo.

## Otros

Puede buscar la versión que prefieras en [este enlace](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)

# Reglas del juego
## Reglas Brandubh

- 8 piezas negras, 4 piezas blancas, 1 rey (blanco).
- Empiezan las negras.
- Los negros ganan la partida si se comen al rey.
- Los blancos ganan la partida si el rey llega a una provincia (esquinas).
- Las piezas pueden moverse como las torres del ajedrez (x casillas en horizontal ó vertical).
- Para comer, es necesario 2 piezas del mismo color: colocadas de la siguiente forma: X0X ó 0X0 y en vertical lo mismo.
- Las provincias y el trono actúan como "pieza neutral" para comerse a piezas de otro color.
- Las piezas normales no pueden establecerse encima de provincias o el trono (centro del mapa).
- Para comer al rey, si está en el trono, serán necesarias 4 piezas negras. Si está adyacente al trono, son 3 piezas negras. Si no se encuentra en ninguna de esas dos situaciones, se come normal.

## Reglas Ardri

- 16 piezas negras, 9 piezas blancas, 1 rey (blanco).
- Empiezan las negras.
- Los negros ganan la partida si se comen al rey.
- Los blancos ganan la partida si el rey llega a una de las 4 paredes del tablero.
- Las piezas pueden moverse sólo 1 casilla en horizontal ó vertical.
- Para comer, es necesario 2 piezas del mismo color: colocadas de la siguiente forma: X0X ó 0X0 y en vertical lo mismo.
- Las provincias y el trono actúan como "pieza neutral" para comerse a piezas de otro color.
- Las piezas normales no pueden establecerse encima de provincias o el trono (centro del mapa).
- Para comer al rey, si está en el trono, serán necesarias 4 piezas negras. Si está adyacente al trono, son 3 piezas negras. Si no se encuentra en ninguna de esas dos situaciones, se come normal.

# Ejecutar el juego
## Para Windows

Una vez instalados los [Requerimientos](#requerimientos), debe ejecutar `compilar.bat` para crear el directorio **bin** y después `ejecutar_textui.bat` para el modo texto.
Le saldrán las instrucciones en la salida de comandos y podrá jugar libremente.

## Para Linux

Una vez instalados los [Requerimientos](#requerimientos), debe ejecutar `compilar.sh` para crear el directorio **bin** y después `ejecutar_textui.sh` para el modo texto.
Le saldrán las instrucciones en la salida de comandos y podrá jugar libremente.

# Contribuciones

Si desea contribuir, puede abrir un issue o enviar un pull request. Si desea realizar un cambio importante, le sugiero que abras un issue para discutirlo.

# Licencia

Este proyecto está licenciado bajo la licencia MIT. Para más información, puede leer el archivo [LICENSE](LICENSE).