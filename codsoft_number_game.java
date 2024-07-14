import java.util.random.*;
import java.util.Random ;
import java.util.Scanner ;
import java.io.*;

class RandomGenerator
{
    public int Random_number_predictor()
    {
        Random random = new Random();
        return random.nextInt(100)+1;
    }
}

class MainClass
{
    public static void main(String[] args) 
    {   
        int no_of_rounds = 1 ;
        int points = 0 ;
        String yes_or_no ;
        int round_no ;
        RandomGenerator random_number = new RandomGenerator();
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("WELCOME TO MY NUMBER GAME");
        System.out.println();
        System.out.println("INSTRUCTION - MAXIMUM ATTEMPT PER ROUND IS 10");
        do
        { 
            System.out.println();
            System.out.println("ROUND-"+(no_of_rounds));
            round_no = no_of_rounds;
            int limit = 10 ;
            System.out.println();
            boolean isPredictionCorrect = false;
            int random_num =   random_number.Random_number_predictor();

            for(int i = 1 ; i <= limit ; i++)
            {
                System.out.print("USER GUESS : ");
                int user_number = scanner.nextInt(); 
                if(user_number == random_num)
                {
                    System.out.println("CONGRATS...YOUR GUESS IS CORRECT...YOU WON ROUND-"+no_of_rounds);
                    points++ ;
                    isPredictionCorrect = true ;
                    break;
                }
                else if(user_number > random_num)
                {
                    System.out.println("GUESS IS TOO HIGH");
                }
                else 
                {
                    System.out.println("GUESS IS TOO LOW");
                }
                
                if(limit - i > 0)
                {
                    System.out.println("REMAINING ATTEMPTS: " + (limit-i));
                    System.out.println();
                }
                else if(limit - i == 0)
                {
                    System.out.println("ATTEMPTS OVER");
                }
            }
            if(!isPredictionCorrect)
            {
                System.out.println("YOU LOSE ROUND-"+no_of_rounds);
                System.out.println();
                System.out.println("CORRECT NUMBER: "+random_num);
                System.out.println();
            }
            System.out.println();
            System.out.println("DO YOU WANT ANOTHER ROUND(y/n) : ");
            yes_or_no = scanner.next();
            scanner.nextLine();
            if(yes_or_no.equalsIgnoreCase("y"))
            {
                no_of_rounds++;
            }
        }while(yes_or_no.equalsIgnoreCase("y"));
        System.out.println("TOTAL ROUNDS WON : "+points);
        System.out.println("WIN PERCENTAGE: " + String.format("%.2f%%", ((double) points / round_no) * 100));
        System.out.println("...THANK YOU FOR PLAYING...HAVE A NICE DAY");
        scanner.close();
    }
}