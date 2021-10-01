@ECHO OFF

if NOT exist class MKDIR class

javac *.java -d class

IF ["%ERRORLEVEL%"] == ["0"] (
	java -cp class; App %1 %2 %3 %4
)
