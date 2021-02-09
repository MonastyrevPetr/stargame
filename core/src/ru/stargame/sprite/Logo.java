package ru.stargame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.Sprite;
import ru.stargame.math.Rect;

public class Logo extends Sprite {
    private Vector2 v=new Vector2();
    private Vector2 touch=new Vector2();
    public static final float V_LEN=0.005f;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
        setHeightProportion(worldBounds.getHeight()/10);

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        v.set(touch.cpy().sub(this.pos)).setLength(V_LEN);
        this.touch.set(touch);

        return false;
    }

    public Vector2 getV() {
        return v;
    }

    public Vector2 getTouch() {
        return touch;
    }


}
