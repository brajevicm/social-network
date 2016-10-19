/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author milos
 */

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyService {
    
    public void insertData(User m) throws SQLException {
        String sql = "insert into users(indeks, first_name, last_name, class, email, image) values (?,?,?,?,?,?)";
        Connection conn = MySQL.Connect();
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, m.getIndeks());
        ps.setString(2, m.getFirstName());
        ps.setString(3, m.getLastName());
        ps.setString(4, m.getClassName());
        ps.setString(5, m.getMail());
        ps.setString(6, m.getImage());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<User> showData() throws SQLException {
        List<User> listUser = new ArrayList<>();

        Connection conn = MySQL.Connect();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users");

        while (rs.next()) {
            User m = new User();
            m.setFirstName(rs.getString(4));
            m.setLastName(rs.getString(5));
            m.setIndeks(rs.getInt(13));
            m.setClassName(rs.getString(12));
            m.setImage(rs.getString(14));
            m.setMail(rs.getString(17));
            listUser.add(m);
        }
        return listUser;
    }

    public void updateData(User m) throws SQLException {
        try {
            String sql = "update users set indeks = ?, first_name = ?, last_name = ?, class = ?, email = ? where image = ?";
            Connection conn = MySQL.Connect();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, m.getIndeks());
            ps.setString(2, m.getFirstName());
            ps.setString(3, m.getLastName());
            ps.setString(4, m.getClassName());
            ps.setString(5, m.getMail());
            ps.setString(6, m.getImage());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteData(String id) throws SQLException {
        Connection conn = MySQL.Connect();
        String sql = "delete from users where email = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }
}

