package com.kamaia.RetroFighter.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Krystal on 4/15/2015.
 */
public abstract class AbstractObject {
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;

	public AbstractObject(){
		position = new Vector2();
		dimension = new Vector2();
		origin = new Vector2();
		scale = new Vector2();
		rotation = 0;
	}
	public void update(float deltaTime){

	}
	public abstract void render(SpriteBatch batch);

}