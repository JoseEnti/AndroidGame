package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Enemy1 extends Actor{
    private int posX;
    private int posY;
    private final float maxH = Gdx.graphics.getHeight();
    private final float maxW = Gdx.graphics.getWidth();
    private Texture texture;
    private Rectangle bounds;

    private boolean hasCollided;


   private Random random = new Random();

    public Enemy1(Texture newTexture)
    {
        texture = newTexture;
        posX = (int)maxW;
        posY = random.nextInt((int)maxH);

        bounds = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());


    }
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture,posX,posY);
    }
    @Override
    public void act(float delta) {
        posX -= 6;
        bounds.setPosition(posX,posY);
        super.act(delta);
    }



    public void setPosX(int newPosX){posX = newPosX;}
    public int getPosX(){return posX;}

    public void setPosY(int newPosY){posY = newPosY;}
    public int getPosY(){return posY;}

    public void setTexture(Texture newTexture){texture = newTexture;}
    public Texture getTexture(){return texture;}
}
