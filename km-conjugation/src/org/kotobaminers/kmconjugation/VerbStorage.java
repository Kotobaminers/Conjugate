package org.kotobaminers.kmconjugation;

import java.io.File;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class VerbStorage {
	public static HashMap<String, Verb> verbs = new HashMap<String, Verb>();
	/*
	 * Random random = new Random(); List<String> keys = new
	 * ArrayList<String>(x.keySet()); String randomKey = keys.get(
	 * random.nextInt(keys.size()) ); String value = x.get(randomKey);
	 */

	public static void loadYML(File dataFolder) {

		YamlConfiguration past = YamlConfiguration.loadConfiguration(new File(dataFolder, "pastConfig.yml"));

		for (String key : past.getKeys(false)) {
			Verb verb = null;
			if (!verbs.containsKey(key)) {
				verb = new Verb();
			} else {
				verb = verbs.get(key);
			}
			verb.dicForm = key;
			verb.pastForm = past.getString(key);
			verbs.put(verb.dicForm, verb);
		}

	}

}
