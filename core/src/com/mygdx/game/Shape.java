package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Shape extends Actor
{
    private int posX;
    private int posY;
    private Texture texture;

    public Shape(int x, int y, Texture newTexture){
        posX = x;
        posY = y;
        texture = newTexture;
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
    }

    @Override
    public void act(float delta) {
        if(getPosY() > Gdx.graphics.getHeight() - 300)
        {
            setPosY(Gdx.graphics.getHeight() - 300);
        }
        if(getPosY() < 0)
        {
            setPosY(0);
        }
        super.act(delta);
    }
}
