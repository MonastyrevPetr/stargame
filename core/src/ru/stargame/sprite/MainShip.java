package ru.stargame.sprite;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.Sprite;
import ru.stargame.math.Rect;

public class MainShip extends Sprite {

    private static final float HEIGHT = 0.1f;
    private static final float PADDING = 0.02f;
    private static final float V_LEN = 0.002f;

    private Vector2 v;
    private Vector2 tmp;
    private Vector2 touch;

    public MainShip(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, 195, 287));
        v = new Vector2();
        tmp = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        tmp.set(touch);
        if (tmp.sub(pos).len()>v.len()){
            pos.add(v);
        }else{
            pos.set(touch);
        }

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }
}
