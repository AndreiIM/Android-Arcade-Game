package com.andrei.bumblebee.drawables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.utils.AppUtilities;

public abstract class GenericDrawable {
	
	protected SpriteBatch batch;
	protected Sprite sprite;
	protected Texture texture;

    protected BumblebeeGame game;

    public abstract void create();
    public abstract void dispose();
    public abstract void render();
	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
