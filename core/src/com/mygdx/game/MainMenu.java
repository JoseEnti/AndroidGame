package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
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
import com.mygdx.utils.UIFactory;

public class MainMenu extends AbstractScreen
{
	private SpriteBatch batch;
	private Texture background;
	private Texture playButton;
	private Texture exitButton;
	private Stage mainMenu;
	private ImageButton playBtn;
	private ImageButton exitBtn;
	private BitmapFont font;
	private float x;
	private float y;

	public MainMenu()
	{
		//Initialise level and textures
		mainMenu = new Stage();
		background = new Texture(Gdx.files.internal("background.jpg"));
		playButton = new Texture(Gdx.files.internal("playbutton.png"));
		exitButton = new Texture(Gdx.files.internal("exitbutton.png"));
		font = new BitmapFont();
		font.getData().setScale(6, 6);
		batch = new SpriteBatch();
	}

	@Override
	public void buildStage()
	{
		Gdx.input.setInputProcessor(mainMenu);

		//Assign actors to the stage
		Image bg = new Image(background);

		playBtn = UIFactory.createButton(playButton);
		playBtn.setSize(250,250);
		playBtn.setPosition(130, 720.f, Align.center);

		exitBtn = UIFactory.createButton(exitButton);
		exitBtn.setSize(250,250);
		exitBtn.setPosition(130, 620.f, Align.center);

		mainMenu.addActor(bg);

		playBtn.addListener(UIFactory.createListener(ScreenEnum.GAME));
		exitBtn.addListener(
				new InputListener()
				{
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
					{
						Gdx.app.exit();
						return false;
					}
				}
		);

		mainMenu.addActor(playBtn);
		mainMenu.addActor(exitBtn);
	}

	@Override
	public void render(float delta)
	{
		mainMenu.draw();
		batch.begin();
		font.draw(batch, "Avengers: Endgame", 620, 1020);
		batch.end();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		background.dispose();
		playButton.dispose();
		exitButton.dispose();
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{

		return true;
	}
}