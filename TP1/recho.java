

/*
*   TP1: recho Java program.
*   Purpose = Mimic the echo shell command with the exception of the result that will be displayed upside down
*   Script should be launched from the shell = javac recho.java | java recho [args...]
*/


public class recho {
    public static void main(String[] args){
        // Displays the last argument
        System.out.print(args[args.length-1]);
        // Displays arguments in the reverse order and separate them by an escape char
        for(int i=args.length-2 ; i>=0 ; i--){
            System.out.print(" " + args[i]);
        }
        // Drop the last line as "Enter keyboard"
        System.out.println("");
    }
}
