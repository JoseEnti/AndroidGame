package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class FirstLevel extends AbstractScreen
{
    private SpriteBatch batch;
    private Texture ship;
    private Texture skyBox;
    private int level;
    private Shape player;

    FirstLevel(Integer level)
    {
        super();
        this.level = level;
    }

    @Override
    public void buildStage()
    {
        batch = new SpriteBatch();

        ship = new Texture("ship.png");
        skyBox = new Texture("skybox.jpg");
        //animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("pepe.gif").read());

        player = new Shape(0,0, ship);
        addActor(player);

        InputProcessor p = new InputProcessor(player);
        Gdx.input.setInputProcessor(p);
    }

    @Override
    public void draw ()
    {
        //elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(skyBox,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(player.getTexture(), player.getPosX(), player.getPosY(), 100, 100);
        //batch.draw(animation.getKeyFrame(elapsed), 20.0f, 20.0f);

        batch.end();
    }

    @Override
    public void dispose ()
    {
        batch.dispose();
        ship.dispose();
        skyBox.dispose();
    }
}