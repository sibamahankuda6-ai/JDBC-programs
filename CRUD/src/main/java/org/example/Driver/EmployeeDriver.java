package org.example.Driver;

import org.example.Dao.Dbutil1;
import org.example.Entity.Employee;

import java.util.Scanner;

public class EmployeeDriver {
    private static int choice = 0;
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        Dbutil1 dbutil1 =new Dbutil1();
        Dbutil1.connecToDb();
        do{
            System.out.println("1.insert\n2.Delete\n3.Update\n4.DisplayAll\n5.GetByid");
            System.out.println("Enter your choice");
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    Employee s1 = new Employee();
                    System.out.println("Enter employee_id");
                    s1.setEmployee_id(sc.nextInt());
                    System.out.println("Enter Employee_name");
                    sc.nextLine();
                    s1.setEmployee_name(sc.nextLine());
                    System.out.println("Enter Employee_salary");
                    s1.setEmployee_salary(sc.nextInt());
                    int rws = dbutil1.insert(s1);
                    if (rws>0){
                        System.out.println(s1.getEmployee_name() + " Employee inserted successfully");
                    }else{
                        System.out.println("Issue in inserting");
                    }
                    break;
                case 2:
                    System.out.println("Enter ID");
                    if(dbutil1.deleteByid(sc.nextInt())){
                        System.out.println("Data Deleted");
                    }else{
                        System.out.println("issue in deleteing data");
                    }
                    break;
                case 3:
                    Employee st = new Employee();
                    System.out.println("Enter the id You want to UPDATE");
                    st.setEmployee_id(sc.nextInt());
                    System.out.println("Enter name You want to Update");
                    sc.nextLine();
                    st.setEmployee_name(sc.nextLine());
                    System.out.println("Enter the Updated Salary");
                    st.setEmployee_salary(sc.nextInt());
                    if(dbutil1.update(st)>0){
                        System.out.println("Updated.....");
                    }else{
                        System.out.println("issue in updating");
                    }
                    break;

                case 4 :dbutil1.DisplayAll();
                    break;
                case 5:
                    System.out.println("Enter id");
                    dbutil1.getByid(sc.nextInt());
                default:
                    System.out.println("Enter valid data");
            }

        }while(choice!=0);



    }
}