package org.kotobaminers.kmconjugation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Conjugate extends JavaPlugin {

	// Temporary for reading the past tense config file
	private File configYMLFile = new File(getDataFolder(), "pastConfig.yml");
	Set<String> conjKeys;
	String[] keyArray;

	// Let's make a configuration file for the past tense verbs
	public void createPastConfig() {

		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		} else {
			getLogger().info("pastTense.yml found.");
		}
		if (!configYMLFile.exists()) {
			getLogger().info("pastTense.yml not found, creating.");
			copy(getResource("pastConfig.yml"), configYMLFile);
		}

		YamlConfiguration special = new YamlConfiguration();
		try {
			special.load(configYMLFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Copying the resource file into the YML file if it doesn't exist
	public void copy(InputStream in, File file) {

		try {

			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}

			out.close();
			in.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void onEnable() {
		super.onEnable();
		System.out.println("Enabling Conjugate");
		createPastConfig();

		VerbStorage.loadYML(getDataFolder());

	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			ConjPlayer playerInstance = PlayerStorage.loadPlayer(player.getUniqueId());
			if (cmd.getName().equalsIgnoreCase("hello")) {
				player.sendMessage("The plugin is running " + player.getName() + "!");
				return true;
			}

			if (cmd.getName().equalsIgnoreCase("conjugate")) {
				if (args.length == 0) {
					Util.usage(player);
					return true;
				} else {

					// Choose type of conjugation
					switch (args[0]) {
					case "past":

						/*
						 * Util.send(player, keyArray[(int) (Math.random() *
						 * (conjKeys.size() - 0))]);
						 * 
						 * Util.send(player, "You have selected past tense.");
						 */
						playerInstance.setMode("past");
						Util.send(player, "You have selected past tense.");
						return true;
					case "potential":
						playerInstance.setMode("potential");
						Util.send(player, "You have selected potential form.");
						return true;
					/*
					 * case "会う": player.sendMessage(ChatColor.RED +
					 * pastConfig.getString("会う")); return true;
					 */
					case "times":
						if (!(args.length == 2)) {
							Util.send(player, "You need to type an number between 5 and 100");
							return true;
						}
						try {
							PlayerStorage.loadPlayer(player.getUniqueId()).setTimes(Integer.parseInt(args[1]));
							Util.send(player, "You will be quizzed " + Integer.parseInt(args[1]) + " times.");
							return true;

						} catch (NumberFormatException e) {
							Util.send(player, ChatColor.RED + "You need to type an number between 5 and 100");
							Util.send(player, ChatColor.RED + "/conjugate times 5");
							return true;
						}
					case "quiz":
						GameSessions session = new GameSessions();
						session.initiate(playerInstance);
						return true;

					default:
						Util.send(player, ChatColor.RED + "That is not a valid command.");
						Util.send(player, ChatColor.RED + "Use /conjugate to see options.");
						return true;
					}
				}
			}

		}

		return false;

	}

}
