package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ThanosHolyButthole extends Actor
{
    private float posX, posY;
    public boolean alive = true;
    private Rectangle bounds;
    private Texture texture;
    public float bulletsSpeed;

    public ThanosHolyButthole(float cPosX, float cPosY, Texture bulletTexture)
    {
        texture = bulletTexture;
        posX = cPosX + 250;
        posY = cPosY + 80;

        bounds = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(float delta)
    {
        if(alive)
        {
            posX += bulletsSpeed;
        }
        else
        {
            posX+= 12;
            posY+= 12;
        }
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
