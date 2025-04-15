import java.util.ArrayList;
import java.util.Scanner;

class arraystudent{
int id;
String name, course;

public arraystudent(int id,String name,String course){
this.id=id;
this.name=name;
this.course=course;



}
public void display(){
System.out.println("Student Id "+ id);
System.out.println("Student Name "+ name);
System.out.println("Student Course " + course);
}



}
public class arrayliststudent{
public static void main(String[] args){

Scanner sc= new Scanner(System.in);

System.out.println("Enter the number of students");
int noofstudents = sc.nextInt();
sc.nextLine();


ArrayList<arraystudent> students = new ArrayList<>();


for(int i=0;i<noofstudents;i++){
System.out.println("Enter the details for student"+(i+1));


System.out.println("Enter the student Id :");
int id = sc.nextInt();
sc.nextLine();

System.out.println("Enter the student Name :");
String name = sc.nextLine();

System.out.println("Enter the student Course :");
String course = sc.nextLine();

 students.add(new arraystudent(id,name,course));
}

 System.out.println("\nStudent Details:");
        for (int i = 0; i <students.size(); i++) {
            students.get(i).display();
        }

        sc.close(); // Closing Scanner
    }
}



































