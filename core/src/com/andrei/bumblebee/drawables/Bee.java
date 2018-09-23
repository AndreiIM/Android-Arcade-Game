package com.andrei.bumblebee.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.pojos.Point;
import com.andrei.bumblebee.utils.AppConstants;
import com.andrei.bumblebee.utils.AppUtilities;

public class Bee extends AnimatedDrawable {

    private static final int FRAMES_COUNT = 8;
    private static final String ATLAS_LABEL = "BEE";
    private static final float SCALE_RATIO = 1.3f;  //sprite size (height) relative to hexagon diameter

    public Bee(BumblebeeGame game){
        this.game = game;
    }

	@Override
	public void create() {
		batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("data/bee.atlas"));
        AtlasRegion region = textureAtlas.findRegion(ATLAS_LABEL + "1");
        sprite = new Sprite(region);
        //sprite.setPosition(100, 250);
        //sprite.scale(-0.5f);

        //resize the sprite according to the world dimensions
        AppUtilities.safeResize(sprite, SCALE_RATIO);

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

    public void moveTo(Point p){
        sprite.setPosition(p.x - sprite.getWidth()/2,
                Gdx.graphics.getHeight() - p.y - sprite.getHeight()/2);
    }

}
