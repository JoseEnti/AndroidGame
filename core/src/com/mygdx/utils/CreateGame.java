package com.mygdx.utils;

import com.badlogic.gdx.Game;
import com.mygdx.game.ScreenEnum;

public class CreateGame extends Game
{
    @Override
    public void create()
    {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.BOSS);
    }
}
