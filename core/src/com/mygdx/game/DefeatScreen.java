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

public class DefeatScreen extends AbstractScreen
{
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont gameOverRestart;
    private BitmapFont gameOverExit;
    private Texture endgame;
    private Texture background;
    private Stage defeatScreen;
    private Animation<TextureRegion> animation;
    private float elapsed;
    private Music music;
    private AssetManager manager;

    public DefeatScreen()
    {
        manager = new AssetManager();
        manager.load("background.jpg", Texture.class);
        manager.load("crab.mp3", Music.class);
        manager.finishLoading();

        defeatScreen = new Stage();

        batch = new SpriteBatch();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("thanos.gif").read());

        background = manager.get("background.jpg");
        //endgame = new Texture(Gdx.files.internal("homanos.jpg"));

        font = new BitmapFont();
        gameOverExit = new BitmapFont();
        gameOverRestart = new BitmapFont();

        font.getData().setScale(3,3);
        gameOverExit.getData().setScale(3,3);
        gameOverRestart.getData().setScale(3,3);

        music = manager.get("crab.mp3");
    }

    @Override
    public void buildStage()
    {
        //Image end = new Image(endgame);
        Image bg = new Image(background);
        //end.setPosition(getWidth() / 2 - endgame.getWidth() / 2, getHeight() / 2 - endgame.getHeight() / 2);
        defeatScreen.addActor(bg);
        //defeatScreen.addActor(end);
        music.setVolume(.2f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta)
    {
        elapsed += Gdx.graphics.getDeltaTime();
        defeatScreen.draw();

        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), getWidth() / 2 - 200, getHeight() / 2 - 100);
        font.draw(batch,"Oh no! Thanos has gotten the N-word pass!",460,990);
        gameOverRestart.draw(batch, "Press ENTER to play again", getWidth() / 2 - 200, 200);
        gameOverExit.draw(batch, "Press ESC to exit", getWidth() / 2 - 200, 150);
        batch.end();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        font.dispose();
        gameOverExit.dispose();
        gameOverRestart.dispose();
        background.dispose();
        defeatScreen.dispose();
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
