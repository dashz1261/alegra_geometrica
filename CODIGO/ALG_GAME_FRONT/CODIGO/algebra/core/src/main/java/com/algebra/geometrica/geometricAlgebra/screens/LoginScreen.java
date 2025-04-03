package com.algebra.geometrica.geometricAlgebra.screens;

import com.algebra.geometrica.geometricAlgebra.network.AuthService;
import com.algebra.geometrica.geometricAlgebra.network.SessionManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LoginScreen implements Screen {
    private final Stage stage;
    private final TextField usernameField;
    private final TextField passwordField;
    private final AuthService authService;
    private final Skin skin;

    public LoginScreen() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.authService = new AuthService();

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        // Labels y TextFields
        Label usernameLabel = new Label("Usuario:", skin);
        usernameField = new TextField("", skin);

        Label passwordLabel = new Label("Contraseña:", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Botón de login
        TextButton loginButton = new TextButton("Iniciar Sesión", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                login();
            }
        });

        table.add(usernameLabel).pad(10);
        table.add(usernameField).width(200).pad(10);
        table.row();

        table.add(passwordLabel).pad(10);
        table.add(passwordField).width(200).pad(10);
        table.row();

        table.add(loginButton).colspan(2).center().padTop(20);
    }

    private void login() {
        String userName = usernameField.getText();
        String passName = passwordField.getText();

        authService.login(userName, passName, new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                assert response.body() != null;
                final String jsonResponse = response.body().string();
                Gdx.app.postRunnable(() -> {
                if (response.isSuccessful()) {
                    showMessage("Inicio de Sesión existoso:" , jsonResponse );
                    SessionManager.setToken(jsonResponse);
                }else {
                    showMessage("Error", "Usuario no existe o contraseña incorrecta");
                }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Gdx.app.postRunnable(() -> {
                    showMessage("Error", "No se pudo conectar al servidor.");
                });
            }
        });
    }

    private void showMessage(String tittle, String message) {
        Dialog dialog = new Dialog(tittle, skin);
        dialog.text(message);
        dialog.button("OK"); // Botón para cerrar
        dialog.show(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

