SET JFLEX_JAR=".\lib\jflex\jflex-full-1.8.2.jar"
SET JCUP_JAR=".\lib\cup\java-cup-11b.jar"

SET LEXER=".\src\main\jflex\lexer.jflex"
SET SINTAXIS=".\src\main\cup\syntax.cup"

SET ANALIZADORES_OUT=".\src\main\java\Analizadores"
cd /d %ANALIZADORES_OUT%
for /F "delims=" %%i in ('dir /b') do (rmdir "%%i" /s/q || del "%%i" /s/q)
cd ..
cd ..
cd ..
cd ..
java -jar %JFLEX_JAR% -d %ANALIZADORES_OUT% %LEXER% 
pause

java -jar %JCUP_JAR% -destdir %ANALIZADORES_OUT% -package "Analizadores" -parser AnalizadorSintactico -symbols Simbolos %SINTAXIS%
pause



