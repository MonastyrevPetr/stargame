package ru.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


import ru.stargame.base.BaseScreen;
import ru.stargame.math.Rect;
import ru.stargame.sprite.Background;
import ru.stargame.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Texture lg;
    private Logo logo;
    private Vector2 tmp;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        lg=new Texture("textures/badlogic.jpg");
        logo=new Logo(lg);
        tmp=new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.6f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
        tmp.set(logo.getTouch());
        if (tmp.sub(logo.pos).len()>Logo.V_LEN){
            logo.pos.add(logo.getV());
        }else {
            logo.pos.set(logo.getTouch());
        }

    }

    @Override
    public void dispose() {
        bg.dispose();
        lg.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch, pointer,  button);
        return false;
    }
}
