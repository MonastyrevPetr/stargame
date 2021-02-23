package ru.stargame.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.stargame.base.SpritesPool;
import ru.stargame.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private final TextureAtlas atlas;
    private final Sound sound;

    public ExplosionPool(TextureAtlas atlas,Sound sound) {
        this.atlas = atlas;
        this.sound=sound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(atlas,sound);
    }
}
