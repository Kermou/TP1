// Purpose = Mimic the echo shell command with the exception of the result that will be displayed upside down
// Script should be launched from the shell = javac recho.java | java recho toto tata tutu

public class recho {
    public static void main(String[] args){
        System.out.print(args[args.length-1]);
        for(int i=args.length-2 ; i>=0 ; i--){
            System.out.print(" " + args[i]);
        }
        System.out.println("");
    }
}
