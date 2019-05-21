import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String NAME = "Yuanheng Li";
    private static final String DATE = "Sun May 19 17:53:21 CEST 2019";
    //To record total sum
    private static int sum = 0;
    //To record how many times user get 6 on a dice
    private static int bonusDiceRound = 0;

    public static void main(String[] args) {
        System.out.println();
        printUserInfo();
        play();
    }

    private static void play(){
        int castAmount = getValidUserInput();
        System.out.println("Antalet du har vålt är: "+castAmount);
        System.out.println();
        castDice(castAmount);
        System.out.println();
        System.out.println("Summan totalt är: "+sum);
        restart();
    }
    private static void printUserInfo(){
        System.out.println(NAME);
        System.out.println(DATE);
        System.out.println();
    }


    //To get valid cast amount
    private static int getValidUserInput(){
        while(true){
            try {
                int userInput = -1;
                System.out.println("Ange hur många tärningar du vill ha:(1-5)");
                Scanner sc = new Scanner(System.in);
                if(sc.hasNext()) {
                    userInput = sc.nextInt();
                    if (userInput < 1 || userInput > 5) {
                        //Handle an integer not within [1,5]
                        throw new SmallerThanZeroOrBiggerThanFiveException();
                    }
                }
                return userInput;
            }catch (InputMismatchException mismatchException){
                System.out.println("Bara ett heltal som som mellan 1 och 5 är giltigt");
            }
            catch (SmallerThanZeroOrBiggerThanFiveException rangeException){
                System.out.println("Ange ett heltal som är mellan 1 och 5.");
            }
        }
    }

    //To cast dice one by one
    private static void castDice(int castAmount){
        for(int diceIndex = 1; diceIndex <= castAmount; diceIndex++){
            int randomDice = (int) Math.floor(Math.random()*6 +1);
            //When get 6 on a dice
            if(randomDice == 6){
               System.out.println("Bonus!Du har fått en sex på din "+ diceIndex + " kast!");
               sum += castBonusDice();
            }else
                sum += randomDice;
            System.out.println("Den "+diceIndex +" Kasten av alla dina "+castAmount+" tärningar är "+randomDice+" ," +
                    "hittills har du totalt fått "+bonusDiceRound+" bonus, nävarande summan = "+sum);
        }
    }
    //Use recursive to handle bonus as to cast two more dices
    private static int castBonusDice(){
        bonusDiceRound++;
        //local bonus field to record which bonus recursive in on
        int localBonusRound = bonusDiceRound;
        //local sum to record sum of each bonus recursive
        int sum = 0;
        System.out.println("Den här är den "+ localBonusRound+" bonus!");
        System.out.println();
        for(int bonusDiceIndex = 1; bonusDiceIndex <= 2; bonusDiceIndex++){

            int randomDice = (int) Math.floor(Math.random()*6 +1);

            if(randomDice == 6){
                System.out.println("Den "+bonusDiceIndex +" kasten på "+localBonusRound+" bonus är "+randomDice);
                sum += castBonusDice();
                System.out.println("Närvarande summan inom "+localBonusRound+" bonus är: "+sum);
                System.out.println();
            }else {
                sum += randomDice;
                System.out.println("Den "+bonusDiceIndex +" kasten på "+localBonusRound+" bonus är "+randomDice);
                System.out.println("Närvarande summan inom "+localBonusRound+" bonus är: "+sum);
                System.out.println();
            }

        }
        return sum;
    }

    //To restart game
    private static void restart() {
        System.out.println();
        System.out.println("Vill du starta programmet om?(Y/N)");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine().trim();
            if (userInput.toUpperCase().equals("Y")) {
                sum = 0;
                bonusDiceRound = 0;
                play();
                break;
            } else if (userInput.toUpperCase().equals("N")) {
                System.out.println("Hej då!");
                System.exit(0);
            } else {
                System.out.println("Input Y or N,pls");
            }
        }
    }
}

class SmallerThanZeroOrBiggerThanFiveException  extends Exception{

}
