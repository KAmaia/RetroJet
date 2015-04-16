package com.kamaia.RetroFighter.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kamaia.RetroFighter.assets.Assets;

/**
 * Created by Krystal on 4/15/2015.
 */
public class Box extends AbstractObject {
	private int           length;
	private TextureRegion region;

	public Box() {
		init();
	}

	private void init() {
		dimension.set(1, 1);
		setLength(1);
	}

	@Override
	public void render(SpriteBatch batch) {
		region = Assets.instance.box.box;

		float relX = 0;
		float relY = 0;
		relX -= dimension.x / 4;
		for (int i = 0; i < length; i++) {
			batch.draw(region.getTexture(), position.x + relX, position.y + relY, origin.x, origin.y,
			           dimension.x / 4, dimension.y, scale.x, scale.y, rotation, region.getRegionX(),
			           region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
			relX += dimension.x;
		}
	}

	public void increaseLength(int amount) {
		length += amount;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}
}
