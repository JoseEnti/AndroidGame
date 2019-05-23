package com.mygdx.game;

public enum ScreenEnum
{
    MAIN_MENU
    {
        public AbstractScreen getScreen(Object... params)
        {return new MainMenu();}
    },
    DEFEAT
    {
        @Override
        public AbstractScreen getScreen(Object... params)
        {return new DefeatScreen();}
    },
    GAME
    {
        @Override
        public AbstractScreen getScreen(Object... params)
        {return new FirstLevel();}
    },
    BOSS
    {
        @Override
        public AbstractScreen getScreen(Object... params)
        {return new BossBattle();}
    },
    VICTORY
    {
        @Override
        public AbstractScreen getScreen(Object... params)
            {return new VictoryScreen();}
    };

    public abstract AbstractScreen getScreen(Object... params);
}
