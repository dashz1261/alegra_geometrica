package com.algebra.geometrica.geometricAlgebra.screens;

import com.algebra.geometrica.geometricAlgebra.network.AuthService;
import com.algebra.geometrica.geometricAlgebra.network.SessionManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class RegisterScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final AuthService authService;
    private final TextField userNameField;
    private final TextField passwordField;

    public RegisterScreen(){
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        authService = new AuthService();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label userNameLabel = new Label("Usuario: ", skin);
        userNameField = new TextField("", skin);

        Label passwordLabel = new Label("Contraseña:", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        TextButton registerButton = new TextButton("Registrar", skin);
        registerButton.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event, float x, float y){
               register();
           }
        });

        table.add(userNameLabel).pad(10);
        table.row();
        table.add(userNameField).width(300).pad(10);
        table.row();
        table.add(passwordLabel).pad(10);
        table.row();
        table.add(passwordField).width(300).pad(10);
        table.row();
        table.add(registerButton).pad(20);
    }

    public void register(){
        String userName = userNameField.getText();
        String passName = passwordField.getText();

        authService.register(userName, passName, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Gdx.app.postRunnable(() -> {
                    showMessage("Error", "No se pudo conectar al servidor.");
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                assert response.body() != null;
                final String jsonResponse = response.body().string();
                Gdx.app.postRunnable(() -> {
                    if (response.isSuccessful()) {
                        showMessage("Registro existoso: " , jsonResponse );
                        SessionManager.setToken(jsonResponse);
                    }else {
                        showMessage("Error", "El usuario ingresado ya se encuentra registrado: ");
                    }
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
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
    }
}
