package org.example.Dao;


import org.example.Entity.Employee;

import java.sql.*;

public class Dbutil1 {
    private static String url = "jdbc:mysql://localhost:3306/bank";
    private static String user = "root";
    private static String password = "221020";
    private static Connection connection = null;

    public static void connecToDb(){
        try {
            connection= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int insert(Employee employee){
        String sql = "insert into employee values(?,?,?)";
        try {

            PreparedStatement prt =connection.prepareStatement(sql);
            prt.setInt(1, employee.getEmployee_id());
            prt.setString(2, employee.getEmployee_name());
            prt.setInt(3, employee.getEmployee_salary());
            return prt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DisplayAll(){

        String sql = "select * from employee";
        try {
            Statement stm =connection.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|"+rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean deleteByid(int id){

        try {
            PreparedStatement prt =connection.prepareStatement("delete from employee where employee_id=?");
            prt.setInt(1,id);
            if(prt.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int update(Employee st){
        String sql= "update employee set employee_name =?,employee_salary=? where employee_id = ?";
        try {
            PreparedStatement prt =connection.prepareStatement(sql);
            prt.setString(1,st.getEmployee_name());
            prt.setInt(2,st.getEmployee_salary());
            prt.setInt(3,st.getEmployee_id());
            return prt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void getByid(int id) {
        connecToDb();
        try {
            PreparedStatement prt=connection.prepareStatement("select * from employee where employee_id =?");
            prt.setInt(1,id);
            ResultSet rt=prt.executeQuery();
            if(rt.next()){
                System.out.println(rt.getInt(1) +" |"+ rt.getString(2)+"|"+rt.getInt(3));
            }else{
                System.out.println("Data not Exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}