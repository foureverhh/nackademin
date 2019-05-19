import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String NAME = "Yuanheng Li";
    private static final Date DATE = new Date();
    private static int sum = 0;
    private static int bonusDiceRound = 0;

    public static void main(String[] args) {
        printUserInfo();
        //int diceAmount = getValidUserInput();
        //System.out.println("Dice amount is: "+diceAmount);
        //castDice(diceAmount);
        sum += castBonusDice();
        System.out.println("Outer sum is: "+sum);
    }

    private static void printUserInfo(){
        System.out.println(NAME);
        System.out.println(DATE);
        System.out.println();
    }

    private static int getValidUserInput(){
        while(true){
            try {
                int userInput = -1;
                System.out.println("Input how many dice you would like to play(1-5)");
                Scanner sc = new Scanner(System.in);
                if(sc.hasNext()) {
                    userInput = sc.nextInt();
                    if (userInput < 1 || userInput > 5) {
                        throw new SmallerThanZeroOrBiggerThanFiveException();
                    }
                }
                return userInput;
            }catch (InputMismatchException mismatchException){
                System.out.println("Only number is acceptable");
            }
            catch (SmallerThanZeroOrBiggerThanFiveException rangeException){
                System.out.println("Choose a number between 1 and 5");
            }

        }
    }

    private static void castDice(int diceAmount){
        StringBuilder str = new StringBuilder("Sum =");

        for(int diceIndex = 1; diceIndex <= diceAmount; diceIndex++){
            int randomDice = (int) Math.floor(Math.random()*6 +1);
            sum += randomDice;
            System.out.println("The "+diceIndex +" dice gets "+randomDice+" ,and current sum = "+sum);
            str.append(" "+randomDice);
            if(diceIndex<diceAmount)
                str.append(" +");
        }
        str.append("= "+sum);
        System.out.println(str);
    }

    private static int castBonusDice(){
        bonusDiceRound++;
        int localBonusRound = bonusDiceRound;
        int sum = 0;
        System.out.println("Bonus!you get a six!This is the "+ localBonusRound+" bonus!");
        System.out.println("Current sum is: "+sum);
        System.out.println();
        //System.out.println("Current sum is: "+sum);
        for(int bonusDiceIndex = 1; bonusDiceIndex <= 2; bonusDiceIndex++){

            int randomDice = (int) Math.floor(Math.random()*6 +1);

            if(randomDice == 6){
                System.out.println("The "+bonusDiceIndex +" dice on "+localBonusRound+" bonus is "+randomDice);
                sum += castBonusDice();
                //System.out.println("Sum after "+localBonusRound+" bonus, and "+bonusDiceIndex+" dice is: "+sum);
                System.out.println("Current sum is: "+sum);
                System.out.println();
            }else {
                sum += randomDice;
                System.out.println("The "+bonusDiceIndex +" dice on "+localBonusRound+" bonus is "+randomDice);
                //System.out.println("Sum after "+bonusDiceIndex +" dice on "+localBonusRound+" bonus is "+sum);
                System.out.println("Current sum is: "+sum);
                System.out.println();
            }

        }
        //System.out.println();
       // System.out.println("Sum for "+bonusDiceRound+" bonus is"+ sum);
       // System.out.println();
        return sum;
    }
   /*
    private int cast(int diceNumber) {
        for (int i = 1; i <= diceNumber; i++) {
            System.out.println("Recursive on " + i + " dice");
            int randomDice = (int) Math.floor(Math.random()*6 +1);

            if (randomDice = 6) {}
        }
    }
    */

}