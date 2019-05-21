package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class Procesador extends InputAdapter {

    private Shape player;



    public Procesador(Shape shape)
    {
        this.player = shape;
    }

    public Procesador(){}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("my app", ""+button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        System.out.print("entra");
        if(keycode == Input.Keys.W)
        {
            System.out.print("WWWWWWWWWW");
            int y = player.getPosY();
            y += 3;

            player.setPosY(y);
        }
        if(keycode == Input.Keys.S)
        {
            System.out.print("SSSSSSSSSSS");
            int y = player.getPosY();
            y -= 3;

            player.setPosY(y);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

}