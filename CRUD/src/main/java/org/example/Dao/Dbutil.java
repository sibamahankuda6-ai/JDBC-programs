package org.example.Dao;


import org.example.Entity.Student;

import java.sql.*;

public class Dbutil {
    private static String url = "jdbc:mysql://localhost:3306/student";
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
    public int insert(Student student){
        String sql = "insert into studentdata values(?,?,?)";
        try {

            PreparedStatement prt =connection.prepareStatement(sql);
            prt.setInt(1,student.getId());
            prt.setString(2, student.getName());
            prt.setString(3, student.getCourse());
            return prt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DisplayAll(){

        String sql = "select * from studentdata";
        try {
            Statement stm =connection.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|"+rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean deleteByid(int id){

        try {
            PreparedStatement prt =connection.prepareStatement("delete from studentdata where id=?");
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
    public int update(Student st){
        String sql= "update studentdata set name =?,course=? where id = ?";
        try {
            PreparedStatement prt =connection.prepareStatement(sql);
            prt.setString(1,st.getName());
            prt.setString(2,st.getCourse());
            prt.setInt(3,st.getId());
            return prt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void getByid(int id) {
        connecToDb();
        try {
            PreparedStatement prt=connection.prepareStatement("select * from studentdata where id =?");
            prt.setInt(1,id);
            ResultSet rt=prt.executeQuery();
            if(rt.next()){
                System.out.println(rt.getInt(1) +" |"+ rt.getString(2)+"|"+rt.getString(3));
            }else{
                System.out.println("Data not Exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}