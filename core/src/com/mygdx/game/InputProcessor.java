package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

    private Shape player;

    public InputProcessor(Shape shape)
    {
        this.player = shape;
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