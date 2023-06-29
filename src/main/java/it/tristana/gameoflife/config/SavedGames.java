package it.tristana.gameoflife.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.tristana.commons.config.Config;
import it.tristana.gameoflife.game.GameBuilder;

public class SavedGames extends Config {

	public static final String SAVED_GAMES = "saved-games";
	
	public SavedGames(File folder) {
		super(new File(folder, "saved-games.yml"));
	}

	@Override
	protected void createDefault() {
		set(SAVED_GAMES, new ArrayList<>(0));
	}
	
	@Override
	protected boolean shouldUpdateConfig() {
		return false;
	}
	
	public void saveGames(Map<String, GameBuilder> games) {
		
	}
	
	public Map<String, GameBuilder> loadGames() {
		return new HashMap<>();
	}
}
