package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Shape
{
    private int posX;
    private int posY;
    private Texture texture;

    public Shape(int x, int y, Texture newTexture){posX = x; posY = y; texture = newTexture;}

    public void setPosX(int newPosX){posX = newPosX;}
    public int getPosX(){return posX;}

    public void setPosY(int newPosY){posY = newPosY;}
    public int getPosY(){return posY;}

    public void setTexture(Texture newTexture){texture = newTexture;}
    public Texture getTexture(){return texture;}
}
