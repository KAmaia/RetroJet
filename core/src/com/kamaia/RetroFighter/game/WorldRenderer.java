package com.kamaia.RetroFighter.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.kamaia.RetroFighter.util.Constants;

/**
 * Created by Krystal on 4/15/2015.
 */
public class WorldRenderer implements Disposable {
	private OrthographicCamera camera;
	private SpriteBatch        batch;
	private WorldController    wc;

	public WorldRenderer(WorldController wc) {
		this.wc = wc;
		init();
	}

	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.visibleWidth, Constants.visibleHeight);
		camera.position.set(0, 0, 0);
		camera.update();
	}

	public void render() {
		renderTestObjects();


	}

	private void renderTestObjects() {

		wc.getCameraHelper().applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Sprite sprite : wc.getTestSprites()) {
			sprite.draw(batch);
		}
		batch.end();
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.visibleHeight / height) * width;
		camera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
