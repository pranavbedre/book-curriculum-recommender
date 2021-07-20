import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

class createAndWrite {
  void input() {
    String name, usn, dept;
    int sem;

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter your name: ");
    name = sc.nextLine();

    try {
      File f = new File("student text files\\" + name +  "_details.txt");

      if(f.createNewFile()) {
        System.out.println("File has been created");
      }
      else {
        System.out.println("File exists");
      }
    } catch (Exception e) {
      System.out.println("An error occured in creating the file.");
    }

    System.out.print("Enter your USN: ");
    usn = sc.nextLine();
    System.out.print("Enter which department: ");
    dept = sc.nextLine();
    System.out.print("Enter semester number: ");
    sem = sc.nextInt();

    try {
      FileWriter fw = new FileWriter("student text files\\" + name +  "_details.txt");
      fw.write("Name: " + name + "\n");
      fw.write("USN: " + usn + "\n");
      fw.write("Department: " + dept + "\n");
      fw.write("Semester: " + sem + "\n");
      fw.close();
    } catch (Exception e) {
      System.out.println("An error occured while writing to file.");
    }
    readAndFetch(sem, dept, name);
    sc.close();
  }

  void readAndFetch(int sem, String dept, String name) {
    try {
      String books = Files.readAllLines(Paths.get("departments\\" + dept + ".txt")).get(sem - 1);
      FileWriter f = new FileWriter("student text files\\" + name +  "_book_titles.txt");
      f.write("Book recommendations for you: " + books);
      System.out.println("Please view the file named: " + name + "_details.txt and " + name + "_book_titles.txt to view student details and book recommendations.");
      f.close();
    } catch(IOException e){
      System.out.println("An error occurred while reading from department file.");
    }
  }
}

class controlFlow extends createAndWrite {
  void menu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n\n");
    System.out.println("Welcome to the Cirriculum Book Recommender!!\n");
    System.out.println("Please input '1' if you want to the program details of the student.\n");
    System.out.println("1. Input Student info");
    System.out.println("2. exit");
    int ch = sc.nextInt();
    switch (ch) {
      case 1:
        input();
        break;

        case 2:
        System.exit(0);
        break;

      default:
      System.out.println("wrong choice");
      menu();
        break;
    }
    sc.close();
  }

  public static void main(String[] args) {
    controlFlow ctrl = new controlFlow();
    ctrl.menu();
  }
}
