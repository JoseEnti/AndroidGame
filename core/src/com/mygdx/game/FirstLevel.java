package com.mygdx.game;
import java.util.Timer;

import com.badlogic.gdx.Gdx;
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
    private Texture exitButton;
    private Texture finalBoss;
    private Texture skyBox;
    private Texture background;
    private ImageButton exitBtn;
    private Shape player;
    private Stage firstLevel;

    Timer timer = new Timer();
    public FirstLevel()
    {

        firstLevel = new Stage();
        character = new Texture(Gdx.files.internal("character01.png"));
        exitButton = new Texture(Gdx.files.internal("exitbutton.png"));
        finalBoss = new Texture(Gdx.files.internal("finalboss002.png"));
        background = new Texture(Gdx.files.internal("skybox.jpg"));
    }

    @Override
    public void buildStage()
    {
        //Gdx.input.setInputProcessor(firstLevel);
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
}