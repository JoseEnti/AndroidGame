package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Shape extends Actor
{
    private int posX;
    private int posY;
    private Texture texture;
    private Rectangle playerBounds;

    public Shape(int x, int y, Texture newTexture){
        posX = x;
        posY = y;
        texture = newTexture;

        playerBounds = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
    }

    public void setPosX(int newPosX){posX = newPosX;}
    public int getPosX(){return posX;}

    public void setPosY(int newPosY){posY = newPosY;}
    public int getPosY(){return posY;}

    public void setTexture(Texture newTexture){texture = newTexture;}
    public Texture getTexture(){return texture;}

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture,posX,posY);
        playerBounds.setPosition(posX,posY);
    }

    @Override
    public void act(float delta)
    {
        if(getPosY() > Gdx.graphics.getHeight() - 250)
        {
            setPosY(Gdx.graphics.getHeight() - 250);
        }
        if(getPosY() < 0)
        {
            setPosY(0);
        }
        super.act(delta);
    }

    public Rectangle getBounds(){return playerBounds;}
}
