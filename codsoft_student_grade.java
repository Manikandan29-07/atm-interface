/*Input: Take marks obtained (out of 100) in each subject.
Calculate Total Marks: Sum up the marks obtained in all subjects.
Calculate Average Percentage: Divide the total marks by the total number of subjects to get the
average percentage.
Grade Calculation: Assign grades based on the average percentage achieved.
Display Results: Show the total marks, average percentage, and the corresponding grade to the user */

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
class Student
{
    int no_of_sub ;
    int[] marks ;
    String name ;
    int reg_no ;
    double percentage ;
    String grade ;
    int sum = 0 ;
    
    Student(String name , int reg_no ,int no_of_sub ,int [] marks)
    {
        this.name = name ;
        this.reg_no = reg_no ;
        this.no_of_sub = no_of_sub ;
        this.marks = Arrays.copyOf(marks,no_of_sub);
    }

    public double Average_percentage()
    {
        
        for(int i = 0 ; i < marks.length ;i++)
        {
            sum += this.marks[i] ;
        }
        percentage = (double)sum / (double)no_of_sub;
        return percentage ;
    }

    public String Calc_Grade()
    {
         
        if(percentage >= 90)
        {
            grade = "S";
        }
        else if(percentage >= 80 && percentage < 90)
        {
            grade = "A" ;
        }
        else if(percentage >= 70 && percentage < 80)
        {
            grade =  "B" ;
        }
        else if(percentage >= 60 && percentage < 70)
        {
            grade =  "C" ;
        }
        else if(percentage >= 50 && percentage < 60)
        {
            grade =  "D" ;
        }
        else
        {
            grade =  "F" ;
        }
        return grade ;
    }

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("STUDENT RESULT");
        System.out.println();
        System.out.println("NAME               : " + this.name);
        System.out.println("REG NO             : " + this.reg_no);
        System.out.println("TOTAL(OUT OF"+(100*no_of_sub)+")   : "+sum);
        System.out.println(String.format("Average percentage : %.2f", this.percentage));
        System.out.println("Grade              : " + this.grade );
    }
}

class MainClass
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the Name of the Student : ");    
        String name = sc.nextLine();
        System.out.print("Enter the Reg_no : ");
        int reg_no = sc.nextInt();
        System.out.print("Enter the no of Subjects : ");
        int no_of_sub = sc.nextInt();

        int[] marks = new int[no_of_sub];
        System.out.println("Enter the Marks of Each Subject(Max mark per Subject : 100)");
        for(int i = 0 ; i < no_of_sub ; i++)
        {
            System.out.print("Subject_"+(i+1)+" : ");
            marks[i] = sc.nextInt();
        }
        
        Student student = new Student(name, reg_no, no_of_sub, marks) ;
        student.Average_percentage();
        student.Calc_Grade();
        student.DisplayDetails();
    }
}