package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

import javax.xml.soap.Text;

public class BonetteThanos extends Actor
{
    private float posX;
    private float posY;
    private final float maxH = Gdx.graphics.getHeight();
    private final float maxW = Gdx.graphics.getWidth();
    private Texture texture;
    private Texture ass;
    private Texture finalTexture;
    private boolean goingUp = true;
    int bossPhase = 0;
    private Random random = new Random();
    int thanosLife = 1000;
    boolean firsttime = true;
    private Rectangle bounds;

    public BonetteThanos(Texture newTexture, Texture assTexture, Texture finalGamba)
    {
        texture = newTexture;
        ass = assTexture;
        finalTexture = finalGamba;
        posX = (int)maxW;
        posY = maxH / 3;

        bounds = new Rectangle(posX, posY, newTexture.getWidth(), newTexture.getHeight());
    }

    public void act(float delta)
    {

        if(bossPhase == 0)
        {
            posX -= 0.264f;
            if(posX <= maxW - 430f)
            {
                bossPhase = 4;
            }
        }
        else if(bossPhase == 1)
        {
            if(firsttime)
            {
                thanosLife = 100;
                firsttime = false;
            }
            if(goingUp) {
                if(posY >= maxH - 316f)
                {
                    goingUp = false;
                }
                else {
                    posY += 1;
                }
            }
            else
            {
                if(posY <= 0)
                {
                    goingUp = true;
                }
                else {
                    posY--;
                }
                if(thanosLife < 50)
                {
                    firsttime = true;
                    bossPhase = 2;
                }
            }
        }
        else if(bossPhase == 2)
        {

            if(firsttime == true) {
                texture = ass;
                firsttime = false;
            }
            posX += 1.2f;
            if(posX >= maxW)
            {
                bossPhase++;
                firsttime = true;
            }
        }
        else if(bossPhase == 3)
        {

        }
        else
        {
            if(firsttime == true) {
                texture = ass;
                firsttime = false;
            }
            posX += 0.2f;
            if(posX >= maxW)
            {
                bossPhase++;
            }
        }
        super.act(delta);
    }
    public void draw(Batch batch, float parentAlpha)
    {
        bounds.setPosition(posX,posY);
        batch.draw(texture,posX,posY);

    }
    public void takeDmg()
    {
        thanosLife -= 5;

    }

    public Rectangle getBounds(){return bounds;}
}