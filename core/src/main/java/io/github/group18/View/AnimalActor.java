package io.github.group18.View;


import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimalActor extends Image {
    private Animation<TextureRegion> walkAnimation;
    private float stateTime = 0f;
    private Vector2 position;
    private Vector2 target;
    private float speed = 60f;

    public AnimalActor(Animation<TextureRegion> animation, Vector2 start, Vector2 target) {
        super(animation.getKeyFrame(0));
        this.walkAnimation = animation;
        this.position = new Vector2(start);
        this.target = new Vector2(target);
        setPosition(position.x, position.y);
        setSize(128, 160);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        setDrawable(new TextureRegionDrawable(walkAnimation.getKeyFrame(stateTime, true)));

        Vector2 direction = new Vector2(target).sub(position);
        if (direction.len() > 2f) {
            direction.nor().scl(speed * delta);
            position.add(direction);
            setPosition(position.x, position.y);
        } else {

            remove();
        }
    }
}
