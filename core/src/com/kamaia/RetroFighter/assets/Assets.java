package com.kamaia.RetroFighter.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.kamaia.RetroFighter.util.Constants;

/**
 * Created by Krystal on 4/15/2015.
 */
public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG      = Assets.class.getName();
	public static final Assets instance = new Assets();
	public AssetBaddie  baddie;
	public AssetBox     box;
	public AssetJet     jet;
	public AssetBullet  bullet;
	public AssetPowerUp powerUp;
	public AssetLevelDecoration levelDecoration;

	private AssetManager assetManager;

	private Assets() {

	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		//load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_PATH, TextureAtlas.class);
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of Assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "Loaded Asset: " + a);
		}

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_PATH);
		for (Texture t : atlas.getTextures()) {
			t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
		baddie = new AssetBaddie(atlas);
		box = new AssetBox(atlas);
		jet = new AssetJet(atlas);
		powerUp = new AssetPowerUp(atlas);
		bullet = new AssetBullet(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);
	}


	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "Couldn't Load Asset: " + asset.fileName + "'", throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	public class AssetJet {
		public final TextureAtlas.AtlasRegion jet;

		public AssetJet(TextureAtlas atlas) {
			jet = atlas.findRegion("Jet");
		}

	}

	public class AssetBox {
		public final TextureAtlas.AtlasRegion box;

		public AssetBox(TextureAtlas atlas) {
			box = atlas.findRegion("Box");
		}

	}

	public class AssetBaddie {
		public final TextureAtlas.AtlasRegion baddie;

		public AssetBaddie(TextureAtlas atlas) {
			baddie = atlas.findRegion("baddie");
		}
	}

	public class AssetPowerUp {
		public final TextureAtlas.AtlasRegion powerUp;

		public AssetPowerUp(TextureAtlas atlas) {
			powerUp = atlas.findRegion("powerup");
		}

	}

	public class AssetLevelDecoration {
		public final TextureAtlas.AtlasRegion star01;
		public final TextureAtlas.AtlasRegion star02;
		public final TextureAtlas.AtlasRegion star03;

		public AssetLevelDecoration(TextureAtlas atlas) {
			star01 = atlas.findRegion("star01");
			star02 = atlas.findRegion("star02");
			star03 = atlas.findRegion("star03");
		}
	}

	public class AssetBullet {
		public final TextureAtlas.AtlasRegion bullet;

		public AssetBullet(TextureAtlas atlas) {
			bullet = atlas.findRegion("bullet");
		}
	}


}
