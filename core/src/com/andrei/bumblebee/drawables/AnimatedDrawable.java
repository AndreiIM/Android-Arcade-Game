package com.andrei.bumblebee.drawables;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public abstract class AnimatedDrawable extends GenericDrawable {

	protected TextureAtlas textureAtlas;
	protected Sprite sprite;
	protected int currentFrame = 1;
	protected String currentAtlasKey;

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public void setTextureAtlas(TextureAtlas textureAtlas) {
		this.textureAtlas = textureAtlas;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public String getCurrentAtlasKey() {
		return currentAtlasKey;
	}

	public void setCurrentAtlasKey(String currentAtlasKey) {
		this.currentAtlasKey = currentAtlasKey;
	}

}
