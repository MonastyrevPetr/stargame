package ru.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import ru.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture background;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("background.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,0.5f, 1, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        background.dispose();
        super.dispose();
    }
}
