@ECHO OFF

if NOT exist class MKDIR class

javac *.java -d class

IF ["%ERRORLEVEL%"] == ["0"] (
	rem java -cp class; App %1
	java -cp class; App "Maze_21x21"
)
