package ru.stargame.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.stargame.base.Ship;
import ru.stargame.math.Rect;
import ru.stargame.pool.BulletPool;
import ru.stargame.pool.ExplosionPool;

public class MainShip extends Ship {

    private static final float HEIGHT = 0.15f;
    private static final float PADDING = 0.05f;
    private static final int INVALID_POINTER = -1;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.explosionPool=explosionPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        v = new Vector2();
        v0 = new Vector2(0.5f, 0);
        bulletV = new Vector2(0, 0.5f);
        bulletPos = new Vector2();
        bulletHeight = 0.01f;
        damage = 1;
        reloadInterval = 0.15f;
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        hp=100;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        bulletPos.set(pos.x,pos.y+getHalfHeight());
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
        this.worldBounds = worldBounds;

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                moveLeft();
                pressedLeft = true;
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                moveRight();
                pressedRight = true;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft)
                    moveLeft();
                else {
                    stop();
                }
                break;
        }
        return false;
    }

    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight()<getLeft() || bullet.getLeft()>getRight() || bullet.getBottom()>pos.y || bullet.getTop()<getBottom());
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }


    public void dispose() {
        sound.dispose();
    }

}
