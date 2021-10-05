# LyC 2021 2C

Repo para el compilador de Lenguajes y Compiladores, grupo 1, 2021 2do cuatrimestre. 

El proyecto esta organizado:

-En entregas estan solo las entregas, subimos esa carpeta al git para tener registro de lo que entregamos.

-En lib estan los binarios que usamos, es para ayudar a la portabilidad del proyecto

-En src/main hay 3 carpetas:

	src/main/cup: esta el codigo de cup
	src/main/jflex: esta el codigo de flex
	src/main/java: lo unico que importa es la que tiene el main, que es el que se puede ejecutar desde eclipse, lo otro es autogenerado.

-El Main toma el prueba.txt de la raiz del proyecto y escribe el ts.txt

## JFLEX y CUP

Tenemos los binarios de JFLEX y JCUP en /lib, para no tener que instalarlos en el path de windows y toda la bola, deberia correr asi pelado.

## .BAT
Tambien esta el bat *run-jflex.bat*, que agarra los archivos de .jflex y .cup en las rutas:

*\src\main\jflex\lexer.jflex*

*\src\main\cup\syntax.cup*

Y saca los Analizadores en la carpeta:

*\src\main\java\Analizadores*

## DEVOPS
Asi basicamente, cada vez que querramos probar como quedo el analizador, simplemente ejecutamos ese .bat y ya esta.

## Para correr el exe
Esta compilado con JRE 17, descargar de aca:
https://www.oracle.com/java/technologies/downloads/#jdk17-windows