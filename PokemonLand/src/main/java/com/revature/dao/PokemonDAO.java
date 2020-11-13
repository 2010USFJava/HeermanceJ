package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Ability;
import com.revature.beans.Pokemon;

public interface PokemonDAO {
	
    public Pokemon getPokemonByID(int pid) throws SQLException;
    public Ability getAbilitiesByID(int aid) throws SQLException;
    
    public void insertNewPokemon(Pokemon pokemon, String pk) throws SQLException;
    
    public PreparedStatement makePrStmnt(String s) throws Exception;
//	public List<Ability> getTheseAbilities() throws SQLException;
	
//	public List<Pokemon> getAllPokemon() throws SQLException;
	
	

}
