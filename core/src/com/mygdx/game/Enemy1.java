package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Enemy1 extends Actor
{
    private int posX;
    private int posY;
    private final float maxH = Gdx.graphics.getHeight();
    private final float maxW = Gdx.graphics.getWidth();
    private Texture texture;
    private Rectangle bounds;
    private boolean hasCollided;
    private Animation enemyShotAnimation;
    private TextureAtlas shotEnemy;
    private TextureRegion currentRegion;
    private AssetManager manager;
    private float time = 0f;

   private Random random = new Random();

    public Enemy1()
    {
        manager = new AssetManager();
        manager.load("enemyShot.atlas", TextureAtlas.class);
        manager.finishLoading();

        posX = (int)maxW;
        posY = random.nextInt((int)maxH);

        //bounds = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());

        //El primer parámetro indica el tiempo entre frame y frame
        shotEnemy = manager.get("enemyShot.atlas");
        enemyShotAnimation = new Animation(1/15f, shotEnemy.findRegions("shotenemy"));
        enemyShotAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }
    public void draw(Batch batch, float parentAlpha)
    {
        //bounds.setPosition(posX,posY);
        batch.draw(texture,posX,posY);
        batch.draw(currentRegion, getX(), getY());
    }
    @Override
    public void act(float delta)
    {
        time += delta;
        currentRegion = (TextureRegion)enemyShotAnimation.getKeyFrame(time,true);
        posX -= 6;
        super.act(delta);
    }

    public void setPosX(int newPosX){posX = newPosX;}
    public int getPosX(){return posX;}

    public void setPosY(int newPosY){posY = newPosY;}
    public int getPosY(){return posY;}

    public void setTexture(Texture newTexture){texture = newTexture;}
    public Texture getTexture(){return texture;}

    public Rectangle getBounds() {return bounds;}
}