package org.example.Dao;


import org.example.Entity.User;

import java.sql.*;

public class Dbutil2 {
    private static String url = "jdbc:mysql://localhost:3306/user";
    private static String user = "root";
    private static String password = "221020";
    private static Connection connection = null;

    public static void connecToDb() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int insert(User user) {
        String sql = "insert into userdata values(?,?,?)";
        try {

            PreparedStatement prt = connection.prepareStatement(sql);
            prt.setInt(1, user.getUser_id());
            prt.setString(2, user.getUser_name());
            prt.setString(3, user.getUser_book());
            return prt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DisplayAll() {

        String sql = "select * from userdata";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|" + rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteByid(int id) {

        try {
            PreparedStatement prt = connection.prepareStatement("delete from userdata where user_id=?");
            prt.setInt(1, id);
            if (prt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int update(User st) {
        String sql = "update userdata set user_name =?,user_book=? where user_id = ?";
        try {
            PreparedStatement prt = connection.prepareStatement(sql);
            prt.setString(1, st.getUser_name());
            prt.setString(2, st.getUser_book());
            prt.setInt(3, st.getUser_id());
            return prt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
       public void getByid( int id){
            connecToDb();
            try {
                PreparedStatement prt = connection.prepareStatement("select * from userdata where user_id =?");
                prt.setInt(1, id);
                ResultSet rt = prt.executeQuery();
                if (rt.next()) {
                    System.out.println(rt.getInt(1) + " |" + rt.getString(2) + "|" + rt.getString(3));
                } else {
                    System.out.println("Data not Exist");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
