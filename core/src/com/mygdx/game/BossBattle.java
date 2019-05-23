package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.utils.ScreenManager;

import java.util.ArrayList;

public class BossBattle extends AbstractScreen {

    private SpriteBatch batch;
    private Texture character;
    private Texture finalBoss;
    private Texture finalfinalBoss;
    private Texture ass;
    private Texture characterBullet;
    private Texture normalEnemy;
    private Texture background;
    private Shape player;
    private BonetteThanos boss;
    private CharacterBullet bullet;
    private boolean playerHasShot = false;
    private float timeSecondsElapsedFromShooting = 0f;
    private float periodTillShoot = 1f;
    private boolean goingUp;
    private boolean goingDown;
    private AssetManager manager;
    private Stage BossBattle;
    private Music music;
    private Sound playerShotSound;
    private ArrayList<CharacterBullet>listOfBullets;
    private ArrayList<ThanosHolyButthole>listOfThanosBullets;
    private BitmapFont score;
    private Sound nigga;
    private float timeSecondsEnemySpawning = 0f;
    private float thanosShotTimer = 5f;
    private Texture thanosBulletsTexture;
    private boolean firstTime = true;
    public BossBattle()
    {
        batch = new SpriteBatch();


        listOfBullets = new ArrayList<CharacterBullet>();
        listOfThanosBullets = new ArrayList<ThanosHolyButthole>();
        BossBattle = new Stage();

        manager = new AssetManager();

        manager.load("character01.png",Texture.class);
        manager.load("characterbullet.png", Texture.class);
        manager.load("exitbutton.png", Texture.class);
        manager.load("finalboss001.png", Texture.class);
        manager.load("finalboss002.png", Texture.class);
        manager.load("thanosAss.jpg", Texture.class);
        manager.load("backgroundBoss.jpg", Texture.class);
        manager.load("enemyShot.atlas",TextureAtlas.class);
        manager.load("bossTheme.mp3", Music.class);
        manager.load("oof.mp3", Sound.class);
        manager.load("nibba_lamar.mp3", Sound.class);
        manager.load("thanosBullet.png", Texture.class);
        manager.finishLoading();

        character = manager.get("character01.png");
        characterBullet = manager.get("characterbullet.png");
        finalBoss = manager.get("finalboss001.png");
        background = manager.get("backgroundBoss.jpg");
        finalfinalBoss = manager.get("finalboss002.png");
        ass = manager.get("thanosAss.jpg");
        music = manager.get("bossTheme.mp3");
        playerShotSound = manager.get("oof.mp3");
        nigga = manager.get("nibba_lamar.mp3");
        thanosBulletsTexture = manager.get("thanosBullet.png");

        score = new BitmapFont();
    }
    @Override
    public void buildStage()
    {
        BossBattle = new Stage();
        Gdx.input.setInputProcessor(BossBattle);
        Image bg = new Image(background);

        player = new Shape(50,50, character);
        player.setWidth(100);
        player.setHeight(100);

        boss = new BonetteThanos(finalBoss, ass, finalfinalBoss);

        BossBattle.addActor(bg);
        BossBattle.addActor(player);
        BossBattle.addActor(boss);

        Gdx.input.setInputProcessor(BossBattle);

        music.setLooping(true);
        music.setVolume(.5f);
        music.play();

    }
    @Override
    public void render(float delta)
    {
        if (playerHasShot)
        {
            timeSecondsElapsedFromShooting += Gdx.graphics.getRawDeltaTime();
            if (timeSecondsElapsedFromShooting > periodTillShoot)
            {
                timeSecondsElapsedFromShooting -= periodTillShoot;
                playerHasShot = false;
            }
        }
        if (goingUp)
        {
            int y = player.getPosY();
            y += 7;

            player.setPosY(y);
        } else if (goingDown)
        {
            int y = player.getPosY();
            y -= 7;

            player.setPosY(y);
        }
        BossBattle.act();
        BossBattle.draw();
        if(boss.bossPhase == 1 || boss.bossPhase == 4) {
            if(firstTime && boss.bossPhase == 4)
            {
                thanosShotTimer = thanosShotTimer / 2;
                firstTime = false;
            }
            timeSecondsEnemySpawning += Gdx.graphics.getRawDeltaTime();
            if (timeSecondsEnemySpawning > thanosShotTimer) {
                timeSecondsEnemySpawning -= thanosShotTimer;
                ThanosHolyButthole bullet = new ThanosHolyButthole(boss.getPosX(), boss.getPosY(), thanosBulletsTexture);
                listOfThanosBullets.add(bullet);
                BossBattle.addActor(bullet);
            }
        }
        for(int i = 0; i < listOfThanosBullets.size(); i++)
        {
            if(listOfThanosBullets.get(i).getBounds().overlaps(player.getBounds()))
            {
                player.remove();
                nigga.play();
                ScreenManager.getInstance().showScreen(ScreenEnum.DEFEAT);

            }
        }
        //Comprobar que el jugador ataca al enemigo
        for(int i = 0; i < listOfBullets.size(); i++)
        {
            if(listOfBullets.get(i).getBounds().overlaps(boss.getBounds()))
            {
                boss.takeDmg();
                listOfBullets.get(i).alive = false;
            }
        }
    }
    @Override
    public void dispose()
    {
        batch.dispose();
        character.dispose();
        background.dispose();
        music.dispose();
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return true;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if(keyCode == Input.Keys.W)
        {
            goingDown = false;
            goingUp = true;
        }
        else if(keyCode == Input.Keys.S)
        {
            goingUp = false;
            goingDown = true;
        }
        if(keyCode == Input.Keys.D && !playerHasShot)
        {
            playerHasShot = true;
            bullet = new CharacterBullet(player.getPosX(),player.getPosY(),characterBullet);
            playerShotSound.play(0.3f);
            listOfBullets.add(bullet);
            BossBattle.addActor(bullet);
        }
        if(keyCode == Input.Keys.ESCAPE)
        {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keyCode)
    {
        if(keyCode == Input.Keys.W)
        {
            goingUp = false;
        }
        if(keyCode == Input.Keys.S)
        {
            goingDown = false;
        }
        return false;
    }


}
