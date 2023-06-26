package it.tristana.gameoflife.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigPlugin extends Config {

	public static final String FILE_NAME = "plugin.yml";

	private static final String HELP = "help";
	private static final String EXECUTED = "executed";
	private static final String COMMANDS = "commands.";

	private static final String COMMAND_CREATE = COMMANDS + "create.";
	public static final String COMMAND_CREATE_HELP = COMMAND_CREATE + HELP;
	public static final String COMMAND_CREATE_EXECUTED = COMMAND_CREATE + EXECUTED;

	private static final String COMMAND_LIST = COMMANDS + "list.";
	public static final String COMMAND_LIST_HELP = COMMAND_LIST + HELP;
	public static final String COMMAND_LIST_EXECUTED = COMMAND_LIST + EXECUTED;

	private static final String COMMAND_PAUSE = COMMANDS + "pause.";
	public static final String COMMAND_PAUSE_HELP = COMMAND_PAUSE + HELP;
	public static final String COMMAND_PAUSE_PAUSED = COMMAND_PAUSE + "paused";
	public static final String COMMAND_PAUSE_RESUMED = COMMAND_PAUSE + "resumed";

	private static final String COMMAND_DELETE = COMMANDS + "delete.";
	public static final String COMMAND_DELETE_HELP = COMMAND_DELETE + HELP;
	public static final String COMMAND_DELETE_EXECUTED = COMMAND_DELETE + EXECUTED;

	public static final String INVALID_WORLD = "invalid-world";
	public static final String INVALID_NUMBER = "invalid-number";
	public static final String INVALID_GAME = "invalid-game";
	public static final String GAME_ALREADY_EXISTS = "game-already-exists";
	public static final String PAUSED_WORD = "paused-word";
	public static final String RUNNING_WORD = "running-word";

	public ConfigPlugin(File folder) {
		super(new File(folder, FILE_NAME));
	}

	@Override
	protected void createDefault() {
		set(COMMAND_CREATE_HELP, "Creates a new Conway's game of life instance");
		set(COMMAND_CREATE_EXECUTED, "&aLife created!");

		set(COMMAND_LIST_HELP, "Lists the current games of life, the status and their locations");
		set(COMMAND_LIST_EXECUTED, "List of games:");

		set(COMMAND_PAUSE_HELP, "Toggles the pause status of the given game");
		set(COMMAND_PAUSE_PAUSED, "Game &cpaused");
		set(COMMAND_PAUSE_RESUMED, "Game &aresumed");

		set(COMMAND_DELETE_HELP, "Deletes the given game");
		set(COMMAND_DELETE_EXECUTED, "Game deleted");

		set(INVALID_WORLD, "&cCan't find the specified world!");
		set(INVALID_NUMBER, "&cThat's not a number!");
		set(INVALID_GAME, "&cThere isn't a game with that id!");
		set(GAME_ALREADY_EXISTS, "&cA game with that name already exists!");
		set(PAUSED_WORD, "&cpaused");
		set(RUNNING_WORD, "&arunning");
	}
}
