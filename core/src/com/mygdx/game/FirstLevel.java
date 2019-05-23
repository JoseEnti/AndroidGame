package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.utils.UIFactory;

import java.util.ArrayList;

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

    private Rectangle playerBounds;
    private ArrayList<Rectangle> listOfEnemies;
    public ArrayList<Rectangle>listOfBullets;

    private float timeSecondsEnemySpawning = 0f;
    private float periodEnemySpawn = 1.3f;
    private float totalTimeInGame = 0f;
    private float secondsToWin = 50f;

    private Rectangle tempData;

    public FirstLevel()
    {
        listOfEnemies = new ArrayList<Rectangle>();
        listOfBullets = new ArrayList<Rectangle>();

        tempData = new Rectangle();

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
        player.setWidth(100);
        player.setHeight(100);
        playerBounds = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

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

    @Override
    public void render(float delta)
    {
        playerBounds.setPosition(player.getX(),player.getY());
        totalTimeInGame +=Gdx.graphics.getRawDeltaTime();
        if(totalTimeInGame < secondsToWin)
        {
            timeSecondsEnemySpawning += Gdx.graphics.getRawDeltaTime();
            if (timeSecondsEnemySpawning > periodEnemySpawn)
            {
                timeSecondsEnemySpawning -= periodEnemySpawn;
                enemyNormal = new Enemy1(normalEnemy);
                listOfEnemies.add(enemyNormal.getBounds());
                firstLevel.addActor(enemyNormal);
            }
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

            firstLevel.act();
            firstLevel.draw();
        }
        //Comprobar si el enemigo toca al jugador
        for(int i = 0; i < listOfEnemies.size(); i++)
        {
            if(listOfEnemies.get(i).overlaps(playerBounds))
            {
                //Hacer algo cuando el jugador toca al enemigo
            }
        }
        //Comprobar que el jugador ataca al enemigo
        for(int i = 0; i < listOfBullets.size(); i++)
        {
            for(int j = 0; j < listOfEnemies.size(); j++)
            if(listOfBullets.get(i).overlaps(listOfEnemies.get(j)))
            {
                //Eliminar al enemigo que ha tocado
            }
        }
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
            listOfBullets.add(bullet.getBounds());
            firstLevel.addActor(bullet);
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