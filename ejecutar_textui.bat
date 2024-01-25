::
::  Nombre: ejecutar_textui.bat
::  Descripción: Script para ejecutar el juego Tafl con consola de comandos.
::  Organización: Universidad de Burgos
::  @author: José Gallardo Caballero
::  @date: 26/11/2023
::  @version: 1.0
::  @license: BY-NC-SA
::

:: Quita información no necesaria de la consola
@echo off
:: Preguntar si se quiere jugar al Brandubh o al Ardri
:inicio
set /p juego="Que juego quieres jugar? (Brandubh o Ardri): "
if /I "%juego%"=="Brandubh" goto fin
if /I "%juego%"=="Ardri" goto fin
:: Si no devuelve nada, elegir Brandubh
if "%juego%"=="" goto fin_null
echo Por favor, elige Brandubh o Ardri.
goto inicio


:fin
cls
:: Ejecutar el juego mostrando la información de la consola
@echo on
java -cp .\bin;.\lib\tafl-gui-lib-2.0.0.jar tafl.textui.Tafl %juego%
@echo off
goto pauses

:fin_null
cls
:: Ejecutar el juego mostrando la información de la consola
@echo on
java -cp .\bin;.\lib\tafl-gui-lib-2.0.0.jar tafl.textui.Tafl Brandubh

:: Pausar la consola para poder ver los resultados.
:pauses
pause