package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Ability;
import com.revature.beans.Pokemon;
import com.revature.dao.PokemonDAO;
import com.revature.util.ConnFactory;

public class PokemonDaoImpl implements PokemonDAO {
    public static ConnFactory cf=ConnFactory.getInstance();
    
    
    @Override
    public Pokemon getPokemonByID(int pid) throws SQLException {
        Connection conn=cf.getConnection();
        String sql = "select * from pokemon where pid=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1, pid);
        ResultSet rs= ps.executeQuery();
        Pokemon a=null;
        while(rs.next()) {
            a=new Pokemon(rs.getInt(1),rs.getString(2));
        }

        return a;
    }

    @Override
    public Ability getAbilitiesByID(int aid) throws SQLException {
        Connection conn=cf.getConnection();
        String sql = "select * from ability where aid=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1, aid);
        ResultSet rs= ps.executeQuery();
        Ability a=null;
        while(rs.next()) {
            a=new Ability(rs.getInt(1),rs.getString(2),rs.getString(3));
        }

        return a;
    }
    
    @Override
    public void insertNewPokemon(Pokemon pokemon, String pk)throws SQLException{
        String query =" INSERT into pokemon(pid, pname,)VALUES (?,?)";

      try{
          PreparedStatement stmnt = makePrStmnt(query);
          stmnt.setInt(1,pokemon.getiD());
          stmnt.setString(2,pokemon.getName());

          stmnt.executeUpdate();

        // pokemonlist.add(pokemon); ??not sure of the list name??
      } catch(SQLException sqe){
          System.out.println("Check EndUser Insert SQL " + sqe.getSQLState() + sqe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }
      }

      
        @Override     
      public PreparedStatement makePrStmnt(String s) throws Exception
	{
//        ConnFactory cf = cf.getConnection();
	    PreparedStatement prstmt = conn.prepareStatement(s);
	    return prstmt;
	}
    
    
}