allow the user to enter the input through the keyboard. After the user writes the command and presses enter, the string is parsed, and the indicated command executed.
will keep accepting different commands from the user until the user writes “exit”, then the CLI terminates.
 program structure and the list of  commands are listed below.
Program Structure:
Your program should contain 2 major classes: Parser & Terminal.
________________________________________________________________
class Parser {
String commandName;
 String[] args;

//This method will divide the input into commandName and args
//where "input" is the string command entered by the user
public boolean parse(String input){...}
 public String getCommandName(){...}
 public String[] getArgs(){...}
}
______________________________________________________________
public class Terminal {
 Parser parser;
//Implement each command in a method, for example:
public String pwd(){...}
public void cd(String[] args){...}
// ...
//This method will choose the suitable command method to be called
public void chooseCommandAction(){...}
public static void main(String[] args){...}
}
_______________________________________________________________

Commands 
---------
echo Takes 1 argument and prints it.

pwd Takes no arguments and prints the current path.

cd Implement all these cases:
1. cd takes no arguments and changes the current path to the path of your home directory.
2. cd takes 1 argument which is “..” (e.g. cd ..) and changes the current directory to the previous directory.
3. cd takes 1 argument which is either the full path or the relative (short) path and changes the current path to that path.

ls Takes no arguments and lists the contents of the current directory sorted alphabetically.
ls -r Takes no arguments and lists the contents of the current directory in reverse order.

mkdir Takes 1 or more arguments and creates a directory for each argument. Each argument can be:
• Directory name (in this case the new directory is created in the current directory)
• Path (full/short) that ends with a directory name (in this case the new directory is created in the given path)


rmdir Implement all these cases:
1. rmdir takes 1 argument which is “*” (e.g. rmdir *) and removes all the empty directories in the current directory.
2. rmdir takes 1 argument which is either the full path or the
relative (short) path and removes the given directory only if it is empty.


touch Takes 1 argument which is either the full path or the relative (short) path that ends with a file name and creates this file.

cp Takes 2 arguments, both are files and copies the first onto the second.
cp -r Takes 2 arguments, both are directories (empty or not) and copies the first directory (with all its content) into the second one.

rm Takes 1 argument which is a file name that exists in the current directory and removes this file.

cat Takes 1 argument and prints the file’s content or takes 2 arguments and concatenates the content of the 2 files and prints it.
