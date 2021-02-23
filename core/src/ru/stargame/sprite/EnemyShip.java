package ru.stargame.sprite;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.stargame.base.Ship;
import ru.stargame.math.Rect;
import ru.stargame.pool.BulletPool;

public class EnemyShip extends Ship {

    public EnemyShip(BulletPool bulletPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.worldBounds=worldBounds;
        this.sound=sound;
        v = new Vector2();
        v0 = new Vector2(0,-0.2f);
        bulletPos = new Vector2();
        bulletV= new Vector2();
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x,pos.y-getHalfHeight());
        super.update(delta);
        if (getBottom()<worldBounds.getBottom()){
            destroy();
        }
    }

    public void set(TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            float reloadInterval,
            float height,
            int hp){
        this.regions=regions;
        this.v.set(v0);
        this.bulletRegion=bulletRegion;
        this.bulletHeight=bulletHeight;
        this.bulletV.set(bulletV);
        this.damage=damage;
        this.reloadInterval=reloadInterval;
        this.reloadTimer=reloadInterval;
        setHeightProportion(height);
        this.hp=hp;
    }
}
