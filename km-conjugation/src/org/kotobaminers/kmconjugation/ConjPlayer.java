package org.kotobaminers.kmconjugation;

import java.util.UUID;

public class ConjPlayer {
	private UUID uuid = null;
	private String mode = "past";
	private int times = 10;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
