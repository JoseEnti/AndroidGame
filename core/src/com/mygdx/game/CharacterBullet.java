package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CharacterBullet extends Actor {
    private float posX;
    public float bulletsSpeed = 5f;
    private float posY;
    private final float maxH = Gdx.graphics.getHeight();
    private final float maxW = Gdx.graphics.getWidth();
    private Texture texture;
    private Rectangle bounds;

    private boolean hasCollided;

    public CharacterBullet(float cPosX, float cPosY, Texture bulletTexture)
    {
        texture = bulletTexture;
        posX = cPosX + 250;
        posY = cPosY + 80;

        bounds = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(float delta) {
        posX += bulletsSpeed;
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        bounds.setPosition(posX,posY);
        batch.draw(texture,posX,posY);
    }

    public Rectangle getBounds() {return bounds;}
}
