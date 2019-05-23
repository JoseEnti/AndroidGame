package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class BonetteThanos extends Actor {
    private int posX;
    private int posY;
    private final float maxH = Gdx.graphics.getHeight();
    private final float maxW = Gdx.graphics.getWidth();
    private Texture texture;

    int bossPhase = 0;
    private Random random = new Random();
    
    public BonetteThanos(Texture newTexture)
    {
        texture = newTexture;
        posX = (int)maxW;
        posY = random.nextInt((int)maxH);
    }



}
