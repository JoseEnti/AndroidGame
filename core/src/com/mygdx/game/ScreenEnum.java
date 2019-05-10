package com.mygdx.game;

public enum ScreenEnum
{
    MAIN_MENU
    {
        public AbstractScreen getScreen()
        {
            return new MainMenu();
        }
    },
    GAME
    {

    }
}
