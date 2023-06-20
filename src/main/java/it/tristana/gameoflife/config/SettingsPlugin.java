package it.tristana.gameoflife.config;

import java.io.File;

import it.tristana.commons.config.Settings;

public class SettingsPlugin extends Settings<ConfigPlugin> {

	private String commandCreateHelp;
	private String commandCreateExecuted;
	
	private String commandListHelp;
	private String commandListExecuted;
	
	private String commandPauseHelp;
	private String commandPausePaused;
	private String commandPauseResumed;

	private String invalidWorld;
	private String invalidNumber;
	private String invalidGame;
	private String gameAlreadyExists;
	private String pausedWord;
	private String runningWord;

	public SettingsPlugin(File folder) {
		super(folder, ConfigPlugin.class);
	}

	@Override
	protected void reload(ConfigPlugin config) {
		commandCreateHelp = config.getString(ConfigPlugin.COMMAND_CREATE_HELP);
		commandCreateExecuted = config.getString(ConfigPlugin.COMMAND_CREATE_EXECUTED);
		
		commandListHelp = config.getString(ConfigPlugin.COMMAND_LIST_HELP);
		commandListExecuted = config.getString(ConfigPlugin.COMMAND_LIST_EXECUTED);
		
		commandPauseHelp = config.getString(ConfigPlugin.COMMAND_PAUSE_HELP);
		commandPausePaused = config.getString(ConfigPlugin.COMMAND_PAUSE_PAUSED);
		commandPauseResumed = config.getString(ConfigPlugin.COMMAND_PAUSE_RESUMED);
		
		invalidWorld = config.getString(ConfigPlugin.INVALID_WORLD);
		invalidNumber = config.getString(ConfigPlugin.INVALID_NUMBER);
		invalidGame = config.getString(ConfigPlugin.INVALID_GAME);
		gameAlreadyExists = config.getString(ConfigPlugin.GAME_ALREADY_EXISTS);
		pausedWord = config.getString(ConfigPlugin.PAUSED_WORD);
		runningWord = config.getString(ConfigPlugin.RUNNING_WORD);
	}

	public String getInvalidGame() {
		return invalidGame;
	}

	public String getCommandPauseHelp() {
		return commandPauseHelp;
	}

	public String getCommandPausePaused() {
		return commandPausePaused;
	}

	public String getCommandPauseResumed() {
		return commandPauseResumed;
	}

	public String getCommandListHelp() {
		return commandListHelp;
	}

	public String getCommandListExecuted() {
		return commandListExecuted;
	}

	public String getCommandCreateHelp() {
		return commandCreateHelp;
	}

	public String getCommandCreateExecuted() {
		return commandCreateExecuted;
	}

	public String getInvalidWorld() {
		return invalidWorld;
	}
	
	public String getInvalidNumber() {
		return invalidNumber;
	}

	public String getGameAlreadyExists() {
		return gameAlreadyExists;
	}

	public String getPausedWord() {
		return pausedWord;
	}

	public String getRunningWord() {
		return runningWord;
	}
}
