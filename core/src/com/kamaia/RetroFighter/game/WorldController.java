package com.kamaia.RetroFighter.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.kamaia.RetroFighter.assets.Assets;
import com.kamaia.RetroFighter.util.CameraHelper;

/**
 * Created by Krystal on 4/15/2015.
 */
public class WorldController extends InputAdapter {
	private static final String TAG = WorldController.class.getName();
	public  Sprite[]     testSprites;
	public  int          selectedSprite;
	private CameraHelper ch;

	public WorldController() {
		//initialize WorldController
		init();
	}

	private void init() {
		//do nothing again
		Gdx.input.setInputProcessor(this);
		ch = new CameraHelper();
		initTestObjects();
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.R:
				init();
				Gdx.app.debug(TAG, "Game World Reset!");
				break;
			case Input.Keys.SPACE:
				selectNextSprite();
				if (ch.hasTarget()) {
					ch.setTarget(testSprites[selectedSprite]);
				}
				Gdx.app.debug(TAG, "Selected sprite: " + selectedSprite);
				break;

			case Input.Keys.ENTER:
				selectPreviousSprite();
				if (ch.hasTarget()) {
					ch.setTarget(testSprites[selectedSprite]);
				}
				Gdx.app.debug(TAG, "Selected Sprite: " + selectedSprite);
				break;
			case Input.Keys.F:
				ch.setTarget(ch.hasTarget() ? null : testSprites[selectedSprite]);
				Gdx.app.debug(TAG, "Camera Follow Enabled on Target: " + ch.hasTarget());
			default:
				break;
		}
		return false;
	}

	private void initTestObjects() {
		testSprites = new Sprite[5];
		int width = 32;
		int height = 32;
		Array<TextureRegion> regions = new Array<TextureRegion>();
		regions.add(Assets.instance.baddie.baddie);
		regions.add(Assets.instance.box.box);
		regions.add(Assets.instance.jet.jet);
		regions.add(Assets.instance.bullet.bullet);
		regions.add(Assets.instance.powerUp.powerUp);

		Pixmap pm = createProceduralPixmap(width, height);
		Texture texture = new Texture(pm);
		for (int i = 0; i < testSprites.length; i++) {
			Sprite spr = new Sprite(regions.get(i));
			spr.setSize(1, 1);
			spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			spr.setPosition(randomX, randomY);
			testSprites[i] = spr;
		}
		selectedSprite = 0;
	}

	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pm = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		pm.setColor(1, 0, 0, 0.5f);
		pm.fill();
		pm.setColor(1, 1, 0, 1);
		pm.drawLine(0, 0, width, height);
		pm.drawLine(width, 0, 0, height);
		pm.setColor(0, 0, 1, 1);
		pm.drawRectangle(0, 0, width, height);
		return pm;
	}

	public void update(float deltaTime) {
		//do nothing.
		//handle debug input
		handleDebugInput(deltaTime);
		//update our test objects
		updateTestObjects(deltaTime);
		//update camera helper
		ch.update(deltaTime);
	}

	private void handleDebugInput(float deltaTime) {
		if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
			return;
		}

		float sprMoveSpeed = 5 * deltaTime;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			moveSelectedSprite(-sprMoveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			moveSelectedSprite(0, sprMoveSpeed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			moveSelectedSprite(sprMoveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			moveSelectedSprite(0, -sprMoveSpeed);
		}
		float camMoveSpeed = 5 * deltaTime;
		float camMoveAccelFactor = 5;
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			camMoveSpeed *= camMoveAccelFactor;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
			camMoveSpeed /= camMoveAccelFactor;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			moveCamera(-camMoveSpeed, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			moveCamera(camMoveSpeed, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			moveCamera(0, camMoveSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			moveCamera(0, -camMoveSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)){
			ch.setPosition(0,0);
		}
		float camZoomSpeed = 1*deltaTime;
		if(Gdx.input.isKeyPressed(Input.Keys.P)){
			camZoomSpeed *= camMoveAccelFactor;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.O)){
			camZoomSpeed /= camMoveAccelFactor;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)){
			ch.addZoom(camZoomSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)){
			ch.addZoom(-camZoomSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.T)){
			ch.setZoom(1);
		}
	}

	private void moveCamera(float camMoveSpeedX, float camMoveSpeedY) {
		 camMoveSpeedX += ch.getPosition().x;
		camMoveSpeedY += ch.getPosition().y;
		ch.setPosition(camMoveSpeedX, camMoveSpeedY);
	}

	private void selectPreviousSprite() {
		if (selectedSprite == 0) {
			selectedSprite = testSprites.length - 1;
		}
		else {
			selectedSprite -= 1;
		}
	}

	private void selectNextSprite() {
		if (selectedSprite == testSprites.length - 1) {
			selectedSprite = 0;
		}
		else {
			selectedSprite += 1;
		}
	}

	private void moveSelectedSprite(float spdX, float spdY) {
		testSprites[selectedSprite].translate(spdX, spdY);
	}


	private void updateTestObjects(float deltaTime) {
		float rotation = testSprites[selectedSprite].getRotation();
		rotation += 90 * deltaTime;
		rotation %= 360;
		testSprites[selectedSprite].setRotation(rotation);
	}


	public Sprite[] getTestSprites() {
		return testSprites;
	}

	public CameraHelper getCameraHelper() {
		return ch;
	}
}
