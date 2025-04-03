package com.algebra.geometrica.geometricAlgebra;

import com.algebra.geometrica.geometricAlgebra.screens.LoginScreen;
import com.algebra.geometrica.geometricAlgebra.screens.MainScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class Main extends Game {

    @Override
    public void create() {
        this.setScreen(new MainScreen(this));
    }

}
