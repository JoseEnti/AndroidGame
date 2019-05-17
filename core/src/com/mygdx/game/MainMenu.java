package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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

	public MainMenu()
	{
		//Initialise level and textures
		mainMenu = new Stage();
		background = new Texture(Gdx.files.internal("background.jpg"));
		playButton = new Texture(Gdx.files.internal("playbutton.png"));
		exitButton = new Texture(Gdx.files.internal("exitbutton.png"));
	}

	@Override
	public void buildStage()
	{
		//Assign actors to the stage
		Image bg = new Image(background);

		playBtn = UIFactory.createButton(playButton);
		playBtn.setPosition(getWidth() / 2, 120.f, Align.center);

		exitBtn = UIFactory.createButton(exitButton);
		exitBtn.setPosition(getWidth() / 2, 60.f, Align.center);

		playBtn.addListener(UIFactory.createListener(ScreenEnum.GAME));

		exitBtn.addListener(
				new InputListener()
				{
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Gdx.app.exit();
						return false;
					}
				});

		mainMenu.addActor(bg);
		mainMenu.addActor(playBtn);
		mainMenu.addActor(exitBtn);
		}

	@Override
	public void render(float delta)
	{
		mainMenu.act();
		mainMenu.draw();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		background.dispose();
		playButton.dispose();
		exitButton.dispose();
	}
}