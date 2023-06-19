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
	
	public ConfigPlugin(File folder) {
		super(new File(folder, FILE_NAME));
	}

	@Override
	protected void createDefault() {
		set(COMMAND_CREATE_HELP, "Creates a new Conway's game of life instance");
		set(COMMAND_CREATE_EXECUTED, "&aLife created!");
	}
}
