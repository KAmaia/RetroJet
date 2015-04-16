package com.kamaia.RetroFighter.levels;

/**
 * Created by Krystal on 4/15/2015.
 */
public class Level {
	public static final String TAG = Level.class.getName();

	public enum BLOCK_TYPE{
		EMPTY(),
		BOX(),
		STAR(),
		PLAYER_SPAWNPOINT(),
		POWERUP_HEALTH();
	}
}
