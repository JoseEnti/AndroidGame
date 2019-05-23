package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.utils.ScreenManager;
import com.mygdx.utils.UIFactory;

public class MainMenu extends AbstractScreen {
	private SpriteBatch batch;
	private Texture background;
	private Texture cover;
	private Stage mainMenu;
	private BitmapFont font;
	private BitmapFont startGameText;
	private Music music;
	private AssetManager manager;

	public MainMenu()
	{
		//Initialise level and textures
		mainMenu = new Stage();
		manager = new AssetManager();
		manager.load("background.jpg", Texture.class);
		manager.load("gameCover.jpeg", Texture.class);
		manager.load("cumbia.mp3", Music.class);
		manager.finishLoading();

		background = manager.get("background.jpg");
		cover = manager.get("gameCover.jpeg");
		music = manager.get("cumbia.mp3");

		font = new BitmapFont();
		font.getData().setScale(4, 4);

		startGameText = new BitmapFont();
		startGameText.getData().setScale(2, 2);

		batch = new SpriteBatch();
	}

	@Override
	public void buildStage()
	{
		Gdx.input.setInputProcessor(mainMenu);

		music.setLooping(true);
		music.setVolume(.1f);
		music.play();

		//Assign actors to the stage
		Image bg = new Image(background);
		Image coverToImage = new Image(cover);

		coverToImage.setPosition(getWidth() / 2 - cover.getWidth() / 2, getHeight() / 2 - cover.getHeight() / 2);

		mainMenu.addActor(bg);
		mainMenu.addActor(coverToImage);
	}

	@Override
	public void render(float delta) {
		mainMenu.draw();
		batch.begin();
		font.draw(batch, "Revengers: The Endless Tussle of the Incredible Fella", 260, 990);
		startGameText.draw(batch, "Press ENTER to start",getWidth() / 2 - 180, 220);
		batch.end();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		background.dispose();
		cover.dispose();
		batch.dispose();
		music.dispose();
	}

	@Override
	public boolean keyDown(int keyCode)
	{
		if(keyCode == Input.Keys.ENTER)
		{
			ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
		}
		else if(keyCode == Input.Keys.ESCAPE)
		{
			Gdx.app.exit();
		}
		return true;
	}
}