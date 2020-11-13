package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Trainer;
import com.revature.dao.TrainerDAO;
import com.revature.util.ConnFactory;

public class TrainerDaoImpl implements TrainerDAO {
    public static ConnFactory cf = ConnFactory.getInstance();
    @Override
    public void makeNewTrainer(Trainer t) throws SQLException {
        Connection conn =cf.getConnection();
    
        //Use prepared statement
        String sql = "insert into trainer values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        //index refers to which question mark in ^^ statement
        //first index is 1
        ps.setString(1, t.getTname());
        ps.executeUpdate(); //int returned is how many rows were updated
    }
    @Override
    public Trainer retrieveTrainerById(int id) throws SQLException {
        Connection conn = cf.getConnection();
        String sql= "select * from trainer where tid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Trainer newTrainer = new Trainer(rs.getInt(1), rs.getString(2));
         return newTrainer;
        
    }
    
}