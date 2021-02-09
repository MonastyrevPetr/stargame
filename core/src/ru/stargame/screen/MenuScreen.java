package ru.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture background;
    private Vector2 touch;
    private  Vector2 v;
    private Vector2 n;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("background.jpg");
        touch = new Vector2();
        v=new Vector2();
        n=new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, touch.x, touch.y);
        batch.end();
        touch.add(v);
        if (Math.abs(touch.x-n.x)<=1 && Math.abs(touch.y-n.y)<=1){
            v.set(0,0);
        }
    }

    @Override
    public void dispose() {
        img.dispose();
        background.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        v.set(screenX, Gdx.graphics.getHeight() - screenY).sub(touch);
        v.nor();
        n.set(screenX,Gdx.graphics.getHeight() - screenY);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                touch.y += 10;
                v.set(0,0);
                break;
            case Input.Keys.DOWN:
                touch.y -= 10;
                v.set(0,0);
                break;
            case Input.Keys.LEFT:
                touch.x -= 10;
                v.set(0,0);
                break;
            case Input.Keys.RIGHT:
                touch.x += 10;
                v.set(0,0);
                break;
        }
        return false;
    }
}
