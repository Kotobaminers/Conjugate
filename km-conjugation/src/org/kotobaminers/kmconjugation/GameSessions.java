package org.kotobaminers.kmconjugation;

import java.util.UUID;

public class GameSessions {
	private Integer times = 10;
	
	public void initiate(ConjPlayer player) {
		times = player.getTimes();
	}
	

}
