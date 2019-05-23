package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.utils.ScreenManager;
import com.mygdx.utils.UIFactory;

import java.util.ArrayList;

import javax.xml.soap.Text;

public class FirstLevel extends AbstractScreen
{
    private SpriteBatch batch;
    private Texture character;
    private Texture characterBullet;
    private Texture finalBoss;
    private Texture skyBox;
    private Texture background;
    private Shape player;
    private Stage firstLevel;
    private Enemy1 enemyNormal;
    private CharacterBullet bullet;
    private boolean playerHasShot = false;
    private float timeSecondsElapsedFromShooting = 0f;
    private float periodTillShoot = 1f;
    private boolean goingUp;
    private boolean goingDown;

    private ArrayList<Rectangle> listOfEnemies;
    private ArrayList<Rectangle>listOfBullets;
    private AssetManager manager;

    private float timeSecondsEnemySpawning = 0f;
    private float periodEnemySpawn = 1.3f;
    private float totalTimeInGame = 0f;
    private float secondsToWin = 50f;

    private Rectangle tempData;


    private Music music;
    private Sound playerShotSound;

    private BitmapFont score;

    //IMPORTANTE
    //Para ejecutar las animaciones hay que pegar esto:
    //private TextureRegion currentRegion; Como variable global
    //Y dentro del act() hay que poner:
    //currentRegion = (TextureRegion)currentAnimation.getKeyFrame(time,true);
    //Siendo time: time += delta;
    //Y por último en el draw:
    //batch.draw(currentregion, getX(), getY());
    //Para hacer más animaciones el proceso es el mismo, copiar el código y seguir los mismos pasos.
    //Para hacer las animaciones es necesario hacer un archivo .atlas y un .png
    //Para hacerlo tienes que descargar este programa: https://www.codeandweb.com/texturepacker
    //Para guardarlo tienes que seleccionar que estás trabajando con libgdx, donde dice data file
    //buscar la ruta hasta assets, ponerle el nombre y guardarlo en formato atlas y luego darle a publish sprite sheet.

    //Para llamar a la pantalla de derrota: ScreenManager.getInstance().showScreen(ScreenEnum.DEFEAT);
    //Para llamar a la pantalla de victoria: ScreenManager.getInstance().showScreen(ScreenEnum.VICTORY);
    public FirstLevel()
    {
        batch = new SpriteBatch();
        listOfEnemies = new ArrayList<Rectangle>();
        listOfBullets = new ArrayList<Rectangle>();

        tempData = new Rectangle();

        firstLevel = new Stage();

        manager = new AssetManager();

        manager.load("character01.png",Texture.class);
        manager.load("characterbullet.png", Texture.class);
        manager.load("exitbutton.png", Texture.class);
        manager.load("finalboss002.png", Texture.class);
        manager.load("skybox.jpg", Texture.class);
        manager.load("enemyShot.atlas",TextureAtlas.class);
        manager.load("nibba.mp3", Music.class);
        manager.load("oof.mp3", Sound.class);
        manager.finishLoading();

        character = manager.get("character01.png");
        characterBullet = manager.get("characterbullet.png");
        finalBoss = manager.get("finalboss002.png");
        background = manager.get("skybox.jpg");

        music = manager.get("nibba.mp3");
        playerShotSound = manager.get("oof.mp3");

        score = new BitmapFont();
    }

    @Override
    public void buildStage()
    {
        Gdx.input.setInputProcessor(firstLevel);
        Image bg = new Image(background);

        player = new Shape(50,50, character);
        player.setWidth(100);
        player.setHeight(100);

        firstLevel.addActor(bg);
        firstLevel.addActor(player);

        Gdx.input.setInputProcessor(firstLevel);

        music.setLooping(true);
        music.setVolume(.5f);
        music.play();
    }

    @Override
    public void render(float delta)
    {
        totalTimeInGame +=Gdx.graphics.getRawDeltaTime();
        if(totalTimeInGame < secondsToWin)
        {
            timeSecondsEnemySpawning += Gdx.graphics.getRawDeltaTime();
            if (timeSecondsEnemySpawning > periodEnemySpawn)
            {
                timeSecondsEnemySpawning -= periodEnemySpawn;
                enemyNormal = new Enemy1();
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
            if(listOfEnemies.get(i).overlaps(player.getBounds()))
            {
                System.out.println("ok bro");
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
            firstLevel.addActor(bullet);
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