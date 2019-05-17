package com.mygdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ScreenEnum;

public class UIFactory
{
    public static ImageButton createButton(Texture texture) {
        return
                new ImageButton(
                        new TextureRegionDrawable(
                                new TextureRegion(texture) ) );
    }

    public static InputListener createListener(final ScreenEnum dstScreen, final Object... params)
    {
        System.out.println("test");
        return new InputListener()
                {
                    @Override
                    public boolean keyDown(InputEvent event, int keycode) {
                        if(keycode == Input.Keys.W)
                        {
                            System.out.println("Esto funciona");
                            return false;
                        }
                        return true;
                    }
                };
    }
}