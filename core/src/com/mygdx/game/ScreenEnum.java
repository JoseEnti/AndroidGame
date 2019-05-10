package com.mygdx.game;

public enum ScreenEnum
{
    MAIN_MENU
    {
        public AbstractScreen getScreen(Object... params)
        {return new MainMenu();}
    },
    /*LEVEL_SELECT
    {
        @Override
        public AbstractScreen getScreen(Object... params)
        {return new LevelSelectionScreen();}
    },*/
    GAME
    {
        @Override
        public AbstractScreen getScreen(Object... params)
        {
            return new FirstLevel((Integer) params[0]);
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}
