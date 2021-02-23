package ru.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.math.Rect;
import ru.stargame.pool.BulletPool;
import ru.stargame.pool.ExplosionPool;
import ru.stargame.sprite.Bullet;
import ru.stargame.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected Vector2 v0;
    protected Vector2 v;

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;

    protected Sound sound;

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v0 = new Vector2();
        v = new Vector2();
    }

    @Override
    public void update(float delta) {
        if (getTop()>worldBounds.getTop()){
            pos.mulAdd(v0,delta);
        }else{
            pos.mulAdd(v, delta);
        }

        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shot();
        }
        damageAnimateTimer+=delta;
        if(damageAnimateTimer>=DAMAGE_ANIMATE_INTERVAL){
            frame=0;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame=1;
        damageAnimateTimer=0f;
    }

    public int getDamage() {
        return damage;
    }

    private void shot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
        sound.play(0.05f);
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }
}
