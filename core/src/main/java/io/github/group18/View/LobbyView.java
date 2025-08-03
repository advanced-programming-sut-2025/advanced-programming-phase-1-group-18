package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.LobbyController;
import io.github.group18.Model.App;
import io.github.group18.Model.Lobby;
import io.github.group18.enums.ppEnum;

import java.util.ArrayList;

public class LobbyView implements Screen {
    private final LobbyController controller;
    private final Skin skin;
    private Stage stage;
    private Table mainTable;
    private boolean visibleLobbies = true;

    private TextButton refreshButton;
    private TextButton toggleVisibilityButton;
    private java.util.List<Lobby> lobbyList;
    private List<String> visibleLobbiesUI;
    private TextButton joinLobbyButton;
    private TextButton createLobbyButton;
    private TextButton backButton;
    private TextButton chooseMapButton;

    private Lobby selectedLobby;

    public LobbyView(LobbyController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        createUI();
    }

    private void createUI() {
        refreshButton = new TextButton("Refresh", skin);
        toggleVisibilityButton = new TextButton("Visible", skin);
        joinLobbyButton = new TextButton("Join Lobby", skin);
        createLobbyButton = new TextButton("Create Lobby", skin);
        backButton = new TextButton("Back", skin);
        chooseMapButton = new TextButton("Choose Map", skin);

        lobbyList = controller.getAllLobbies();

        Array<String> visibleLobbyNames = new Array<>();
        for (Lobby lobby : lobbyList) {
            if (lobby.isVisible()) {
                String display = lobby.getName() + " | Admin: " + lobby.getAdmin().getUsername() + " | Players: " + lobby.getUsers().size();
                visibleLobbyNames.add(display);
            }
        }

        visibleLobbiesUI = new List<>(skin);
        visibleLobbiesUI.setItems(visibleLobbyNames);

        ScrollPane scrollPane = new ScrollPane(visibleLobbiesUI, skin);

        scrollPane.setFadeScrollBars(false);

        Table topButtons = new Table();
        topButtons.add(refreshButton).pad(10);
        topButtons.add(toggleVisibilityButton).pad(10);
        mainTable.add(topButtons).left().top().expandX().row();

        mainTable.add(scrollPane).expand().fill().pad(10).row();

        Table bottomButtons = new Table();
        bottomButtons.add(joinLobbyButton).left().pad(10);
        bottomButtons.add().expandX();
        bottomButtons.add(createLobbyButton).right().pad(10);
        mainTable.add(bottomButtons).expandX().fillX().row();

        mainTable.add(backButton).left().pad(10);
        mainTable.add(chooseMapButton).right().pad(10);

        setupListeners();
        updateLobbyList();
    }

    private void setupListeners() {
        refreshButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //inja list lobby ha refresh mishe
                updateLobbyList();
            }
        });

        toggleVisibilityButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                visibleLobbies = !visibleLobbies;
                toggleVisibilityButton.setText(visibleLobbies ? "Visible" : "Invisible");
                if (!visibleLobbies) {
                    //inja ye menu miad ke taraf id lobby invisible ro bezane
                    showInvisibleLobbyDialog();
                }
            }
        });

        joinLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                updateLobbyList();
                System.out.println("someone is trying to join lobby");
                if (selectedLobby != null) {
                    System.out.println("selected lobby aint null");
                    if (selectedLobby.getAccessLevel() == ppEnum.PRIVATE) {
                        TextField passwordField = new TextField("", skin);

                        Dialog passwordDialog = new Dialog("Private Lobby", skin) {
                            protected void result(Object object) {
                                if ((boolean) object) {
                                    String password = passwordField.getText();
                                    controller.joinLobby(selectedLobby, App.getCurrentUser(), password);
                                }
                            }
                        };


                        passwordField.setPasswordMode(true);
                        passwordField.setPasswordCharacter('*');

                        passwordDialog.text("Enter password:");
                        passwordDialog.getContentTable().row();
                        passwordDialog.getContentTable().add(passwordField).width(200);
                        passwordDialog.button("Join", true);
                        passwordDialog.button("Cancel", false);
                        passwordDialog.show(stage);
                    } else {
                        controller.joinLobby(selectedLobby, App.getCurrentUser());
                    }
                } else {
                    System.out.println("selected lobby is null");
                }
            }
        });


        createLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateLobbyList();
                //sakht lobby jadid
                showCreateLobbyDialog();
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.goBack();
            }
        });
        chooseMapButton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
//               controller.chooseMap(App.getCurrentUser(),stage);
           }
        });

        visibleLobbiesUI.addListener(new ClickListener() {
            private long lastClickTime = 0;

            @Override
            public void clicked(InputEvent event, float x, float y) {
                int selectedIndex = visibleLobbiesUI.getSelectedIndex();
                int lobbyIndex = -1;

                int visibleCount = 0;
                for (int i = 0; i < lobbyList.size(); i++) {
                    Lobby lobby = lobbyList.get(i);
                    if (lobby.isVisible()) {
                        if (visibleCount == selectedIndex) {
                            selectedLobby = lobby;
                            break;
                        }
                        visibleCount++;
                    }
                }

                long now = System.currentTimeMillis();
                if (now - lastClickTime < 400 && selectedLobby != null) {
                    showLobbyInfo(selectedLobby);
                }
                lastClickTime = now;
            }
        });

    }

    private void showInvisibleLobbyDialog() {
        TextField field = new TextField("", skin);

        Dialog dialog = new Dialog("Enter Lobby ID", skin) {
            protected void result(Object object) {
                if ((boolean) object) {
                    String id = field.getText();

                    String info = controller.showInvisibleLobbyInfo(Integer.parseInt(id));
                    Dialog dialog = new Dialog("Lobby Info", skin);
                    dialog.text(info);
                    if (controller.isAdmin(App.getCurrentUser(), Integer.parseInt(id))) {
                        dialog.button("Create Game", "createGame");
                    }
                    dialog.button("Back", "back");
                    dialog.show(stage);
                }
            }
        };
        dialog.text("Enter lobby ID:");
        dialog.getContentTable().row();
        dialog.getContentTable().add(field).width(200);
        dialog.button("OK", true);
        dialog.button("Cancel", false);
        dialog.key(com.badlogic.gdx.Input.Keys.ENTER, true);
        dialog.show(stage);
        dialog.setMovable(false);
        dialog.setResizable(false);
    }

    private void showCreateLobbyDialog() {

        TextField nameField = new TextField("", skin);
        CheckBox privateCheckBox = new CheckBox("Private", skin);
        privateCheckBox.setChecked(false);
        CheckBox visibleCheckBox = new CheckBox("Visible", skin);
        visibleCheckBox.setChecked(true);
        TextField passwordField = new TextField("", skin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setVisible(false);
        privateCheckBox.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                passwordField.setVisible(privateCheckBox.isChecked());
            }
        });
        Dialog dialog = new Dialog("Create Lobby", skin) {
            protected void result(Object object) {
                if ((boolean) object) {
                    String name = nameField.getText();
                    boolean isPrivate = privateCheckBox.isChecked();
                    boolean isVisible = visibleCheckBox.isChecked();
                    String password = isPrivate ? passwordField.getText() : null;
                    controller.createLobby(name, isPrivate, password, isVisible, App.getCurrentUser());
                }
            }
        };
        Table content = dialog.getContentTable();


        content.add("Name:").left();
        content.add(nameField).width(200).row();
        content.add(privateCheckBox).colspan(2).left().row();
        content.add("Password:").left();
        content.add(passwordField).width(200).row();
        content.add(visibleCheckBox).colspan(2).left().row();

        dialog.button("Create", true);
        dialog.button("Cancel", false);
        dialog.show(stage);
    }

    private void showLobbyInfo(Lobby lobby) {
        String info = controller.getLobbyInfo(lobby);

        Dialog dialog = new Dialog("Lobby Info", skin) {
            @Override
            protected void result(Object object) {
                if ("createGame".equals(object)) {
                    controller.chooseMap(App.getCurrentUser(),stage,lobby);
                } else if ("leave".equals(object)) {
                    controller.leaveLobby(lobby, App.getCurrentUser());
                    updateLobbyList();
                }
            }
        };

        dialog.text(info);

        if (controller.isAdmin(App.getCurrentUser(), lobby) && lobby.getUsers().size() > 1) {
            dialog.button("Create Game", "createGame");
        }

        dialog.button("Leave Lobby", "leave");
        dialog.button("Back", "back");

        dialog.show(stage);
    }

    private void updateLobbyList() {
        lobbyList = controller.getAllLobbies();
        Array<String> visibleLobbyNames = new Array<>();
        for (Lobby lobby : lobbyList) {
            if (visibleLobbies && lobby.isVisible()) {
                String display = lobby.getName() + " | Admin: " + lobby.getAdmin().getUsername() + " | Players: " + lobby.getUsers().size();
                visibleLobbyNames.add(display);
            }
        }
        visibleLobbiesUI.setItems(visibleLobbyNames);
        selectedLobby = null;
    }

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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
