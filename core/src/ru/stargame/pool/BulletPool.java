package ru.stargame.pool;

import ru.stargame.base.SpritesPool;
import ru.stargame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }


}
