package org.kotobaminers.kmconjugation;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStorage {
	public static HashMap<UUID, ConjPlayer> players = new HashMap<UUID, ConjPlayer>();

	public static ConjPlayer loadPlayer(UUID uuid) {
		if (!players.containsKey(uuid)) {
			ConjPlayer player = new ConjPlayer();
			player.setUuid(uuid);
			players.put(uuid, player);

			return player;
		}

		return players.get(uuid);

	}

}
