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
    private Animation<TextureRegion> enemyShotAnimation;
    private Texture imagen;
    private TextureRegion frameActual;
    private TextureRegion[] regionsMovimiento;
    private AssetManager manager;
    private float time;

   private Random random = new Random();

    public Enemy1()
    {
        manager = new AssetManager();
        manager.load("enemyShot.png", Texture.class);
        manager.finishLoading();
        imagen = manager.get("enemyShot.png");

        TextureRegion[][] tmp = TextureRegion.split(imagen,imagen.getWidth()/3, imagen.getHeight());

        regionsMovimiento = new TextureRegion[3];
        for(int i = 0; i < 3; i++)
        {
            regionsMovimiento[i] = tmp[0][i];
        }
        enemyShotAnimation = new Animation<TextureRegion>(1,regionsMovimiento);
        time = 0f;

        posX = (int)maxW;
        posY = random.nextInt((int)maxH);

        bounds = new Rectangle(posX, posY, imagen.getWidth()/3, imagen.getHeight());

        //El primer parámetro indica el tiempo entre frame y frame
    }
    public void draw(Batch batch, float parentAlpha)
    {
        bounds.setPosition(posX,posY);
        batch.draw(frameActual, posX, posY);
    }
    @Override
    public void act(float delta)
    {
        time += delta;
        frameActual = enemyShotAnimation.getKeyFrame(time,true);
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