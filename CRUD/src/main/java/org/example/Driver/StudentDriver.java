package org.example.Driver;

import org.example.Dao.Dbutil;
import org.example.Entity.Student;

import java.util.Scanner;

public class StudentDriver {
    private static int choice = 0;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Dbutil dbutil =new Dbutil();
        Dbutil.connecToDb();
        do{
            System.out.println("1.insert\n2.Delete\n3.Update\n4.DisplayAll\n5.GetByid");
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    Student s1 = new Student();
                    System.out.println("Enter id");
                    s1.setId(sc.nextInt());
                    System.out.println("Enter name");
                    sc.nextLine();
                    s1.setName(sc.nextLine());
                    System.out.println("Enter Course");
                    s1.setCourse(sc.nextLine());
                    int rws = dbutil.insert(s1);
                    if (rws>0){
                        System.out.println(s1.getName() + " User inserted successfully");
                    }else{
                        System.out.println("Issue in inserting");
                    }
                    break;
                case 2:
                    System.out.println("Enter ID");
                    if(dbutil.deleteByid(sc.nextInt())){
                        System.out.println("Data Deleted");
                    }else{
                        System.out.println("issue in deleteing data");
                    }
                    break;
                case 3:
                    Student st = new Student();
                    System.out.println("Enter the id You want to UPDATE");
                    st.setId(sc.nextInt());
                    System.out.println("Enter name You want to Update");
                    sc.nextLine();
                    st.setName(sc.nextLine());
                    System.out.println("Enter the Updated Course name");
                    st.setCourse(sc.nextLine());
                    if(dbutil.update(st)>0){
                        System.out.println("Updated.....");
                    }else{
                        System.out.println("issue in updating");
                    }
                    break;

                case 4 :dbutil.DisplayAll();
                    break;
                case 5:
                    System.out.println("Enter id");
                    dbutil.getByid(sc.nextInt());
                default:
                    System.out.println("Enter valid data");
            }

        }while(choice!=0);



    }
}