package ru.stargame.pool;

import com.badlogic.gdx.audio.Sound;

import ru.stargame.base.SpritesPool;
import ru.stargame.math.Rect;
import ru.stargame.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
    private Rect worldBounds;
    private Sound sound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.sound = sound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool,worldBounds,sound);
    }
}
