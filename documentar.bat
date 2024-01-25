::
::  Nombre: documentar.bat
::  Descripción: Script para documentar el juego Tafl
::  Organización: Universidad de Burgos
::  @author: José Gallardo Caballero
::  @date: 26/11/2023
::  @version: 1.0
::  @license: BY-NC-SA
::

:: Documentar el juego
javadoc -author -version -private ^
-encoding UTF-8 -charset UTF-8 ^
-sourcepath .\src -d doc -classpath .\lib\*;.\bin ^
-docencoding UTF-8 ^
-link https://docs.oracle.com/en/java/javase/20/docs/api/ ^
-subpackages tafl

:: Pausar la consola para poder ver los resultados.
pause