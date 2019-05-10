package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenu extends ApplicationAdapter
{
	private SpriteBatch batch;
	private Texture ship;
	private Texture skyBox;
	private Animation<TextureRegion> animation;
	private Shape player;
	private float elapsed;

	public MainMenu()
	{

	}

	@Override
	public void create ()
	{
		InputProcessor p = new InputProcessor(player);
		Gdx.input.setInputProcessor(p);
	}

	@Override
	public void render ()
	{

	}

	@Override
	public void dispose ()
	{

	}
}