package com.mygdx.game;
import java.util.Timer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.utils.UIFactory;


public class FirstLevel extends AbstractScreen
{
    private SpriteBatch batch;
    private Texture character;
    private Texture characterBullet;
    private Texture exitButton;
    private Texture normalEnemy;
    private Texture finalBoss;
    private Texture skyBox;
    private Texture background;
    private ImageButton exitBtn;
    private Shape player;
    private Stage firstLevel;
    private Enemy1 enemyNormal;
    private CharacterBullet bullet;
    private boolean playerHasShot = false;
    private float timeSecondsElapsedFromShooting = 0f;
    private float periodTillShoot = 1f;
    private boolean goingUp;
    private boolean goingDown;

    private float timeSecondsEnemySpawning = 0f;
    private float periodEnemySpawn = 0.7f;



    public FirstLevel()
    {

        firstLevel = new Stage();
        character = new Texture(Gdx.files.internal("character01.png"));
        characterBullet = new Texture(Gdx.files.internal("characterbullet.png"));
        exitButton = new Texture(Gdx.files.internal("exitbutton.png"));
        finalBoss = new Texture(Gdx.files.internal("finalboss002.png"));
        normalEnemy =new Texture(Gdx.files.internal("bullet001scaled.png"));
        background = new Texture(Gdx.files.internal("skybox.jpg"));
    }

    @Override
    public void buildStage()
    {
        Gdx.input.setInputProcessor(firstLevel);
        Image bg = new Image(background);

        player = new Shape(50,50, character);

        exitBtn = UIFactory.createButton(exitButton);
        exitBtn.setWidth(100);
        exitBtn.setHeight(100);
        exitBtn.setPosition(getWidth() / 2, 60.f, Align.center);

        firstLevel.addActor(bg);
        firstLevel.addActor(player);

        exitBtn.addListener(
                new InputListener()
                {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                    {
                        Gdx.app.exit();
                        return false;
                    }
                });
        Procesador p = new Procesador(player);
        firstLevel.addActor(exitBtn);
        Gdx.input.setInputProcessor(p);
    }


   /* public void draw ()
    {
        //elapsed += Gdx.graphics.getDeltaTime();
       /* Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(skyBox,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(player.getTexture(), player.getPosX(), player.getPosY(), 100, 100);
        //batch.draw(animation.getKeyFrame(elapsed), 20.0f, 20.0f);

        batch.end();
    }*/

    @Override
    public void render(float delta)
    {
        timeSecondsEnemySpawning +=Gdx.graphics.getRawDeltaTime();
        if(timeSecondsEnemySpawning > periodEnemySpawn){
            timeSecondsEnemySpawning-=periodEnemySpawn;
            enemyNormal = new Enemy1(normalEnemy);
            firstLevel.addActor(enemyNormal);
        }
        if(playerHasShot)
        {
            timeSecondsElapsedFromShooting +=Gdx.graphics.getRawDeltaTime();
            if(timeSecondsElapsedFromShooting > periodTillShoot) {
                timeSecondsElapsedFromShooting-=periodTillShoot;
                playerHasShot = false;
            }
        }


        if(goingUp)
        {
            int y = player.getPosY();
            y += 7;

            player.setPosY(y);
        }
        else if(goingDown)
        {
            int y = player.getPosY();
            y -= 7;

            player.setPosY(y);
        }

        firstLevel.act();
        firstLevel.draw();

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        character.dispose();
        background.dispose();
        exitButton.dispose();
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
            firstLevel.addActor(bullet);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
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