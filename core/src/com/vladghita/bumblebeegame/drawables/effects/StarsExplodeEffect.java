package com.vladghita.bumblebeegame.drawables.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vladghita.bumblebeegame.drawables.GenericDrawable;
import com.vladghita.bumblebeegame.pojos.Point;

/**
 * Created by Vlad on 23.08.2015.
 */
public class StarsExplodeEffect extends GenericDrawable{

    private ParticleEffect effect;

    public void create(){
        batch = new SpriteBatch();

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("data/effects/star_effect.p"), Gdx.files.internal("data/effects/"));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {
        batch.begin();

        effect.draw(batch, Gdx.graphics.getDeltaTime());

        batch.end();
    }

    private void setPosition(Point point){
        effect.setPosition((float)point.getX(), (float)point.getY());
    }

    public void respawn(Point point){
        effect.reset();
        setPosition(point);
    }
}
