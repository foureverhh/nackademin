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
        int diceAmount = getValidUserInput();
        System.out.println("Dice amount is: "+diceAmount);
        castDice(diceAmount);

    }

    private static void printUserInfo(){
        System.out.println(NAME);
        System.out.println(DATE);

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

    private static void castBonusDice(){
        bonusDiceRound++;
        for(int bonusDiceIndex = 1; bonusDiceIndex <= 2; bonusDiceIndex++){
            
        }
    }
}