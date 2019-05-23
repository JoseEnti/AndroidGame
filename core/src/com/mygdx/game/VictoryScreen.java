package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.utils.ScreenManager;

public class VictoryScreen extends AbstractScreen
{
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont gameOverRestart;
    private BitmapFont gameOverExit;
    private Texture background;
    private Stage victoryScreen;
    private Animation <TextureRegion> animation;
    private float elapsed;
    private AssetManager manager;
    private Music music;

    public VictoryScreen()
    {
        victoryScreen = new Stage();
        batch = new SpriteBatch();

        manager = new AssetManager();
        manager.load("background.jpg", Texture.class);
        manager.load("avengers.mp3", Music.class);
        manager.finishLoading();

        background = manager.get("background.jpg");
        music = manager.get("avengers.mp3");

        gameOverExit = new BitmapFont();
        gameOverRestart = new BitmapFont();
        font = new BitmapFont();

        gameOverExit.getData().setScale(3,3);
        gameOverRestart.getData().setScale(3,3);
        font.getData().setScale(4,4);
    }

    @Override
    public void buildStage()
    {
        batch = new SpriteBatch();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("ricardo.gif").read());
        Image bg = new Image(background);
        victoryScreen.addActor(bg);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta)
    {
        elapsed += Gdx.graphics.getDeltaTime();
        victoryScreen.draw();

        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), getWidth() / 2 - 200, getHeight() / 2 - 200);
        font.draw(batch, "You've defeated Thanos! Racism is no more", 460, 990);
        gameOverRestart.draw(batch, "Press ENTER to play again", getWidth() / 2 - 200, 200);
        gameOverExit.draw(batch, "Press ESC to exit", getWidth() / 2 - 200, 150);
        batch.end();
    }

    @Override
    public void dispose()
    {
        victoryScreen.dispose();
        background.dispose();
        batch.dispose();
        music.dispose();
    }

    @Override
    public boolean keyDown(int keyCode)
    {
        if(keyCode == Input.Keys.ESCAPE)
        {
            Gdx.app.exit();
        }
        else if(keyCode == Input.Keys.ENTER)
        {
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
        }
        return true;
    }
}
