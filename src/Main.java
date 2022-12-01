import java.util.Hashtable;
import java.util.Scanner;
public class Main implements Boat{
    static String river =  ConsoleColors.BLUE_BOLD_BRIGHT + "_______" + ConsoleColors.RESET ;//+ ConsoleColors.BLUE_BACKGROUND  +
    static String boat = ConsoleColors.YELLOW_BOLD_BRIGHT + "<__>"  + ConsoleColors.RESET;
    static String message = ConsoleColors.RED + ConsoleColors.BLACK_BACKGROUND + "Don't Leave More cannibals on each side than missionaries or you will lose" + ConsoleColors.RESET;;
    static Hashtable<String,Cannibal> RCL = new Hashtable<>(), LCL= new Hashtable<>();;
    static Hashtable<String,Missionary> RML= new Hashtable<>(), LML= new Hashtable<>();;

    static boolean win = false, lose = false;
    static boolean left = false, right= true;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        setupGame();
        System.out.println(message);
        printGame();
        while (!lose){

            String char1 = input.next();
            transportCharacter(char1);
            String char2 = input.next();
            if(!char2.equals("0")) {
                transportCharacter(char2);
            }
            changeSides();
            checkSides();
            printGame();
        }
    }

    private static void changeSides() {
        left = !left;
        right=!right;
    }
     public static void transportCharacter(String name) {
        int i=Integer.parseInt(name);
        if(i>5){
            transportMissionary(name);
        }else{
            transportCannibal(name);
        }
    }
    private static void transportCannibal(String name) {
        if(right){
            Cannibal cannibal = RCL.get(name);
            RCL.remove(name);
            LCL.put(name,cannibal);
        }else {
            Cannibal cannibal = LCL.get(name);
            LCL.remove(name);
            RCL.put(name,cannibal);
        }
    }
    private static void transportMissionary(String name) {
        if(right){
            Missionary missionary = RML.get(name);
            RML.remove(name);
            LML.put(name,missionary);
        }else {
            Missionary missionary = LML.get(name);
            LML.remove(name);
            RML.put(name,missionary);
        }
    }

    private static void setupGame() {
        Cannibal cannibal  = new Cannibal("1","C1");
        RCL.put(cannibal.name,cannibal);

        cannibal = new Cannibal("2","C2");
        RCL.put(cannibal.name,cannibal);

        cannibal = new Cannibal("3","C3");
        RCL.put(cannibal.name,cannibal);

        Missionary missionary  = new Missionary("7","M7");
        RML.put(missionary.name,missionary);

        missionary  = new Missionary("8","M8");
        RML.put(missionary.name,missionary);

        missionary  = new Missionary("9","M9");
        RML.put(missionary.name,missionary);
    }

    static void checkSides(){
        if(RCL.size() > 0 && RML.size() > 0){
            if(RCL.size() > RML.size() ){
                lose = true;
                System.out.println("You LOSE");
            }
        }
        if(LCL.size() > 0 && LML.size() > 0){
            if(LCL.size() > LML.size() ){
                lose = true;
                System.out.println("You LOSE");
            }
        }
        if(LCL.size() == 3 && LML.size()  == 3){
            win = true;
            System.out.println("You WIN");
        }
    }
    public static void printGame(){
        for (Cannibal can:RCL.values()) {
            System.out.print(ConsoleColors.RED_BOLD_BRIGHT + "["+can.tag+"]" + ConsoleColors.RESET);
        }
        for (Missionary mis:RML.values()) {
            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "["+mis.tag+"]" + ConsoleColors.RESET);
        }
        if(right){System.out.print(boat + river);}
        else{System.out.print(river + boat);}

        for (Missionary mis:LML.values()) {
            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "["+mis.tag+"]" + ConsoleColors.RESET);
        }
        for (Cannibal can:LCL.values()) {
            System.out.print(ConsoleColors.RED_BOLD_BRIGHT + "["+can.tag+"]"+ ConsoleColors.RESET);
        }
        System.out.println("");
    }
}
