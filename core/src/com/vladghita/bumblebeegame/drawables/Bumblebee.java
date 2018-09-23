package com.vladghita.bumblebeegame.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.vladghita.bumblebeegame.BumblebeeGame;
import com.vladghita.bumblebeegame.pojos.Point;
import com.vladghita.bumblebeegame.utils.AppConstants;
import com.vladghita.bumblebeegame.utils.AppUtilities;

public class Bumblebee extends AnimatedDrawable {

    private static final int FRAMES_COUNT = 6;
    private static final String ATLAS_LABEL = "BUMBEE";
    private static final float SCALE_RATIO = 2f;  //sprite size (height) relative to hexagon diameter

	@Override
	public void create() {
		batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("data/bumblebee.atlas"));
        AtlasRegion region = textureAtlas.findRegion(ATLAS_LABEL + "1");
        sprite = new Sprite(region);
        //sprite.setPosition(70, 250);
        //sprite.scale(-0.7f);

        //resize the sprite according to the world dimensions
        AppUtilities.safeResize(sprite, SCALE_RATIO);

        //move to the start position
        if(BumblebeeGame.HONEYCOMB_CENTER != null)
            this.moveTo(AppUtilities.worldToScreen(BumblebeeGame.HONEYCOMB_CENTER.getCenter()));

        Timer.schedule(new Task(){
                @Override
                public void run() {
                    currentFrame++;
                    if(currentFrame > FRAMES_COUNT)
                        currentFrame = 1;
                    
                    currentAtlasKey = String.format(ATLAS_LABEL + "%d", currentFrame);
                    sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
                }
            }
            ,0,1/10.0f);
	}

	@Override
	public void dispose() {
		batch.dispose();
        textureAtlas.dispose();
	}

	@Override
	public void render() {
        batch.begin();
        sprite.draw(batch);
        batch.end();
	}

    public void moveTo(int x, int y){
        sprite.setPosition(x - sprite.getWidth() / 2,
                Gdx.graphics.getHeight() - y - sprite.getHeight() / 2);
    }

    public void moveTo(Point p){
        sprite.setPosition(p.x - sprite.getWidth()/2,
                Gdx.graphics.getHeight() - p.y - sprite.getHeight()/2);
    }

}
