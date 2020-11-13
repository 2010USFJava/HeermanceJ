package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

public interface PokemonDAO {
	
	public List<Ability> getTheseAbilities() throws SQLException;
	
	public List<Pokemon> getAllPokemon() throws SQLException;

}
