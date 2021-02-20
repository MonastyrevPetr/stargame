package ru.stargame.base;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    protected final List<T> activeObject = new ArrayList<T>();

    protected final List<T> freeObject = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObject.isEmpty()) {
            object = newObject();
        } else {
            object = freeObject.remove(freeObject.size() - 1);
        }
        activeObject.add(object);
        System.out.println(getClass().getName()+" active/free "+activeObject.size()+"/"+freeObject.size());
        return object;
    }

    public void updateActiveSprite(float delta) {
        for (T item : activeObject) {
            if (!item.isDestroyed()) {
                item.update(delta);
            }
        }
    }

    public void drawActiveSprite(SpriteBatch batch) {
        for (T item : activeObject) {
            if (!item.isDestroyed()) {
                item.draw(batch);
            }
        }
    }

    public void freeAllDestroyActiveSprites(){
        for (int i = 0; i <activeObject.size() ; i++) {
            T sprite=activeObject.get(i);
            if (sprite.isDestroyed()){
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    private void free(T object){
        if (activeObject.remove(object)){
            freeObject.add(object);
            System.out.println(getClass().getName()+" active/free "+activeObject.size()+"/"+freeObject.size());
        }
    }

    public List<T> getActiveObject() {
        return activeObject;
    }

    public void dispose(){
        activeObject.clear();
        freeObject.clear();
    }
}
