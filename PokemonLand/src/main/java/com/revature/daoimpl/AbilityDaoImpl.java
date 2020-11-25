package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Ability;
import com.revature.dao.AbilityDAO;
import com.revature.util.ConnFactory;

public class AbilityDaoImpl implements AbilityDAO{

    public static ConnFactory cf = ConnFactory.getInstance();

//    @Override
//    public void catchNewAbiliy(Ability p) throws SQLException {
//        Connection conn = cf.getConnection();
//        String sql = "insert into ability values(?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1, p.getAid()); // Only referring to 1stps ?
//        ps.setString(2,  p.getAname()); // referring to 2nd ?
//        ps.setString(2,  p.getAdescription()); // referring to 3nd ?
//        ps.executeUpdate();
//    }

    public Ability retrievedById(int aid) throws SQLException {
        Connection conn = cf.getConnection();
        String sql = "select * from ability where aid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, aid); // Only referring to 1stps ?
        ResultSet rs = ps.executeQuery();
        Ability attack = null;
            while(rs.next()) {
                attack = new Ability(rs.getInt(1), rs.getString(2), rs.getString(2));
                }
            return attack;

    }


}