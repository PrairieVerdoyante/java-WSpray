@echo off

rem -------------------------------------------
rem 			Rappel
rem -------------------------------------------

rem Separateur:
rem
rem		Windows		;
rem		Linux/Mac	: 
rem
rem Classpath (-cp)
rem
rem 		.			=> 	met dans le cp tous       .class   du repertoire courant 
rem 		./*			=> 	met dans le cp tous les    jar     du repertoire courant
rem			./ext/* 	=>	met dans le cp tous les    jar	   du folder ext
rem 
rem	Memoire:
rem
rem 	-Xms200m	memoire reservee par l'OS, que la jvm l'emploie ou pas!
rem 	-Xmx500m	memoire max que l'OS peut allouer au processus
rem
rem 	-verbose:gc		=> utile pour optimisation, affiche l'activite du garbage collector  (Warning: ne pas livrer chez le client)
rem
rem Propriete System:
rem	
rem		-Da=1 -Dc=3 -Db=3.14
rem

rem -------------------------------------------
rem 			Tools
rem -------------------------------------------

rem ---------------
rem deploy local
rem ---------------

rem set JRE_PATH=./jvm/bin
rem set JRE_PATH=C:\Soft\java64\jdk\bin
rem set PATH=%JRE_PATH%;%PATH%

rem ---------------
rem debug
rem ---------------

rem java -version
echo.

rem -------------------------------------------
rem 			Main
rem -------------------------------------------

rem configure jvm

set CLASSPATH=.;./*;./ext/*

set MEMORY_MIN=-Xms100m
set MEMORY_MAX=-Xmx500m
set MEMORY= %MEMORY_MIN% %MEMORY_MAX%

rem run

java -cp %CLASSPATH% %MEMORY% ch.hearc.gui.JFrameSpray

rem -------------------------------------------
rem 			end
rem -------------------------------------------

echo.
pause