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
    private ArrayList<Rectangle>listOfBullets;
    private BitmapFont score;
    public BossBattle()
    {
        batch = new SpriteBatch();


        listOfBullets = new ArrayList<Rectangle>();
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
        manager.finishLoading();

        character = manager.get("character01.png");
        characterBullet = manager.get("characterbullet.png");
        finalBoss = manager.get("finalboss001.png");
        background = manager.get("backgroundBoss.jpg");
        finalfinalBoss = manager.get("finalboss002.png");
        ass = manager.get("thanosAss.jpg");
        music = manager.get("bossTheme.mp3");
        playerShotSound = manager.get("oof.mp3");

        score = new BitmapFont();
    }
    @Override
    public void buildStage() {
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
    public void render(float delta) {
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
            listOfBullets.add(bullet.getBounds());
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
