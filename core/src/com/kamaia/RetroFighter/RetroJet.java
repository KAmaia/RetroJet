package com.kamaia.RetroFighter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kamaia.RetroFighter.assets.Assets;
import com.kamaia.RetroFighter.game.WorldController;
import com.kamaia.RetroFighter.game.WorldRenderer;

public class RetroJet implements ApplicationListener {
	private static final String TAG = RetroJet.class.getName();
	private WorldController worldController;
	private WorldRenderer   worldRenderer;
	private boolean         paused;
	SpriteBatch batch;
	Texture     img;

	@Override
	public void create() {
		paused = false;
		Assets.instance.init(new AssetManager());
		//set LibGDX to debug log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		batch = new SpriteBatch();
		img = new Texture("jet.png");
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void render() {
		if (!paused) {
			worldController.update(Gdx.graphics.getDeltaTime());
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			worldRenderer.render();
		}
	}

	@Override
	public void pause() {
		paused = true;

	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void dispose() {
		Assets.instance.dispose();
		worldRenderer.dispose();
	}
}
