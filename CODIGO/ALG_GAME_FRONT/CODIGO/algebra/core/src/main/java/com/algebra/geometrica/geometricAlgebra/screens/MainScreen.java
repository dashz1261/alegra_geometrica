package com.algebra.geometrica.geometricAlgebra.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainScreen implements Screen {

    private final Game game;
    private final Stage stage;
    private final Skin skin;
    private final Texture background;

    public MainScreen(Game game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("textures/imagen_main.png"));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.bottom();
        stage.addActor(mainTable);

        Table buttonTable = new Table();
        mainTable.add(buttonTable).expandX().fillX();


        // Botón con imagen y texto combinado
        Texture buttonTexture = new Texture(Gdx.files.internal("textures/boton_inicio.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        // Estilo para ImageTextButton
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
        style.up = buttonDrawable;
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;

        ImageTextButton loginButton = new ImageTextButton("INICIAR SESIÓN", style);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoginScreen());
            }
        });

        TextButton registerButton = new TextButton("Registrarse", skin);
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterScreen());
            }
        });

        // Tamaño de los botones
        float buttonWidth = 200;
        float buttonHeight = 50;

        buttonTable.add(loginButton).pad(20).width(buttonWidth).height(buttonHeight);
        buttonTable.add(registerButton).pad(20).width(buttonWidth).height(buttonHeight);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
    }
}
