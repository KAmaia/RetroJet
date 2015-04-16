package com.kamaia.RetroFighter.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.kamaia.RetroFighter.assets.Assets;

/**
 * Created by Krystal on 4/15/2015.
 */
public class Stars extends AbstractObject {
	private float                length;
	private Array<TextureRegion> regStars;
	private Array<Star>          stars;

	public Stars(float length) {
		this.length = length;
		init();
	}

	private void init() {
		dimension.set(0.5f, 0.5f);
		regStars = new Array<TextureRegion>();
		regStars.add(Assets.instance.levelDecoration.star01);
		regStars.add(Assets.instance.levelDecoration.star02);
		regStars.add(Assets.instance.levelDecoration.star03);

		int distFac = 5;
		int numStars = (int) (length  / distFac);
		stars = new Array<Star>(2 * numStars);
		for(int i = 0; i < numStars; i++){
			Star star = spawnStar();
			star.position.x = i * distFac;
			stars.add(star);
		}
	}

	private Star spawnStar() {
		Star star = new Star();
		star.dimension.set(dimension);
		star.setRegStar(regStars.random());
		Vector2 pos = new Vector2();
		pos.x = length + 10;
		pos.y += 1.75;
		pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1);
		star.position.set(pos);
		return star;
	}

	public void render(SpriteBatch batch){
		for(Star star : stars){
			star.render(batch);
		}
	}

	private class Star extends AbstractObject {
		private TextureRegion regStar;

		public Star() {

		}

		public void setRegStar(TextureRegion region) {
			regStar = region;
		}

		@Override
		public void render(SpriteBatch batch) {
			TextureRegion reg = regStar;
			batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y,
			           dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
			           reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		}
	}
}