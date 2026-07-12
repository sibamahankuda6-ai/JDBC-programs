package org.example.Driver;

import org.example.Dao.Dbutil2;
import org.example.Entity.User;

import java.util.Scanner;

public class UserDriver {
    private static int choice = 0;
  private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Dbutil2 dbutil2 =new Dbutil2();
        Dbutil2.connecToDb();
        do{
            System.out.println("1.insert\n2.Delete\n3.Update\n4.DisplayAll\n5.GetByid");
            System.out.println("Enter your choice");
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    User s1 = new User();
                    System.out.println("Enter user_id");
                    s1.setUser_id(sc.nextInt());
                    System.out.println("Enter user_name");
                    sc.nextLine();
                    s1.setUser_name(sc.nextLine());
                    System.out.println("Enter User_book");
                    s1.setUser_book(sc.nextLine());
                    int rws = dbutil2.insert(s1);
                    if (rws>0){
                        System.out.println(s1.getUser_name() + " User inserted successfully");
                    }else{
                        System.out.println("Issue in inserting");
                    }
                    break;
                case 2:
                    System.out.println("Enter ID");
                    if(dbutil2.deleteByid(sc.nextInt())){
                        System.out.println("Data Deleted");
                    }else{
                        System.out.println("issue in deleteing data");
                    }
                    break;
                case 3:
                    User st = new User();
                    System.out.println("Enter the id You want to UPDATE");
                    st.setUser_id(sc.nextInt());
                    System.out.println("Enter name You want to Update");
                    sc.nextLine();
                    st.setUser_name(sc.nextLine());
                    System.out.println("Enter the Updated Salary");
                    st.setUser_book(sc.nextLine());
                    if(dbutil2.update(st)>0){
                        System.out.println("Updated.....");
                    }else{
                        System.out.println("issue in updating");
                    }
                    break;

                case 4 :dbutil2.DisplayAll();
                    break;
                case 5:
                    System.out.println("Enter id");
                    dbutil2.getByid(sc.nextInt());
                default:
                    System.out.println("Enter valid data");
            }

        }while(choice!=0);



    }
}