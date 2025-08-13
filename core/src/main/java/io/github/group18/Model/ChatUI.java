package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Model.Items.FishingPole;
import io.github.group18.Model.Items.Price;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatUI {
    private Stage stage;
    private Skin skin;
    private Table chatTable;
    private Window currentDialog;
    private InputProcessor inputProcessor;

    public ChatUI(InputProcessor inputProcessor) {
        this.stage = new Stage(new ScreenViewport());
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.inputProcessor = inputProcessor;
        createChatUI();
    }

    private void createChatUI() {
        stage.clear();
        Window chatWindow = new Window("Chat Box", skin);
        chatWindow.setModal(true);
        chatWindow.setMovable(false);

        Label usernameLabel = new Label("Your Username: " + ClientModel.getPlayer().getOwner().getUsername(), skin);


        Table headerTable = new Table(skin);
        headerTable.add(usernameLabel).pad(20, 20, 20, 20).row();

        chatWindow.add(headerTable).row();

        chatTable = new Table(skin);

        refreshChatList();

        ScrollPane scrollPane = new ScrollPane(chatTable, skin);
        scrollPane.setFadeScrollBars(false);
        chatWindow.add(scrollPane).width(800).height(400).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(inputProcessor);
                chatWindow.remove();
                ClientModel.setWindowOpen(false);
            }
        });

        TextButton privateMessage = new TextButton("Send Private Message", skin);
        privateMessage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sendPrivateMessage();
            }
        });

        TextButton publicMessage = new TextButton("Send Public Message", skin);
        publicMessage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sendPublicMessage();
            }
        });

        TextButton refreshMessage = new TextButton("Refresh", skin);
        refreshMessage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                refreshChatList();
            }
        });

        chatWindow.add(privateMessage).width(700).height(120).row();
        chatWindow.add(publicMessage).width(700).height(120).row();
        chatWindow.add(refreshMessage).width(300).height(120).row();
        chatWindow.add(closeButton).width(200).height(120).row();

        chatWindow.pack();
        chatWindow.setPosition(
                Gdx.graphics.getWidth() / 2 - chatWindow.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - chatWindow.getHeight() / 2
        );

        stage.addActor(chatWindow);

    }

    private void sendPublicMessage() {
        if (currentDialog != null) {
            currentDialog.remove();
        }
        Table tooble = new Table(skin);
        currentDialog = new Window("Public Message", skin);
        currentDialog.setModal(true);

        Label messageTextLabel = new Label("Message: ", skin);
        TextField messageTextField = new TextField("", skin);
        tooble.add(messageTextLabel).width(400).height(120);
        tooble.add(messageTextField).width(400).height(120);
        tooble.row();

        TextButton sendButton = new TextButton("Send", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    //check
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("message", new ChatMessage(ClientModel.getPlayer().getOwner().getUsername(), messageTextField.getText(), false));
                    Message send = new Message(body, Message.Type.add_message, Message.Menu.game);
                    ClientModel.getServerConnectionThread().sendMessage(send);

                } catch (Exception e) {
                    Dialog errorDialog = new Dialog("Error", skin);
                    errorDialog.text("O_o Oops! Something went wrong.");
                    errorDialog.button("OK");
                    errorDialog.show(stage);
                }
            }
        });
        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentDialog.remove();
                currentDialog = null;
            }
        });
        tooble.add(sendButton).width(200).height(120);
        tooble.add(cancelButton).width(200).height(120).row();

        currentDialog.add(tooble);
        currentDialog.pack();
        currentDialog.setPosition(
                Gdx.graphics.getWidth() / 2 - currentDialog.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - currentDialog.getHeight() / 2
        );

        stage.addActor(currentDialog);
    }

    private void sendPrivateMessage() {
        if (currentDialog != null) {
            currentDialog.remove();
        }
        Table tooble = new Table(skin);
        currentDialog = new Window("Private Message", skin);
        currentDialog.setModal(true);

        Label messageTextLabel = new Label("Message: ", skin);
        TextField messageTextField = new TextField("", skin);
        tooble.add(messageTextLabel).width(400).height(120);
        tooble.add(messageTextField).width(400).height(120);
        tooble.row();

        Label senderUsernameLabel = new Label("Receiver Username: ", skin);
        TextField senderUsernameTextField = new TextField("", skin);
        tooble.add(senderUsernameLabel).width(400).height(120);
        tooble.add(senderUsernameTextField).width(400).height(120);
        tooble.row();

        TextButton sendButton = new TextButton("Send", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //check
                try {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("message", new ChatMessage(ClientModel.getPlayer().getOwner().getUsername(), messageTextField.getText(), true, senderUsernameTextField.getText()));
                    Message send = new Message(body, Message.Type.add_message, Message.Menu.game);
                    ClientModel.getServerConnectionThread().sendMessage(send);
                } catch (Exception e) {
                    Dialog errorDialog = new Dialog("Error", skin);
                    errorDialog.text("O_o Oops! Something went wrong.");
                    errorDialog.button("OK");
                    errorDialog.show(stage);
                }
            }
        });
        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentDialog.remove();
                currentDialog = null;
            }
        });
        tooble.add(sendButton).width(200).height(120);
        tooble.add(cancelButton).width(200).height(120).row();

        currentDialog.add(tooble);
        currentDialog.pack();
        currentDialog.setPosition(
                Gdx.graphics.getWidth() / 2 - currentDialog.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - currentDialog.getHeight() / 2
        );

        stage.addActor(currentDialog);
    }

    private void refreshChatList() {
        chatTable.clear();

        Message send = new Message(new HashMap<>(), Message.Type.get_messages, Message.Menu.game);
        Message response = null;
        while (response == null || response.getType() != Message.Type.get_messages) {
            response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        }

        Gson gson = new Gson();
        Object messagesObject = response.getBody().get("messages");
        String messagesJson = gson.toJson(messagesObject);
        Type chatMessageListType = new TypeToken<ArrayList<ChatMessage>>() {
        }.getType();

        ArrayList<ChatMessage> chatMessages = gson.fromJson(messagesJson, chatMessageListType);

        for (ChatMessage chatMessage : chatMessages) {
            Table row = new Table(skin);

            Label label = null;
            if (chatMessage.privateChat) {
                if (chatMessage.receiverUsername.equalsIgnoreCase(ClientModel.getPlayer().getOwner().getUsername())) {
                    label = new Label("Private msg from: " + chatMessage.senderUsername + "\t\t: " + chatMessage.message, skin);
                    label.setColor(Color.RED);
                } else {
                    if (chatMessage.senderUsername.equalsIgnoreCase(ClientModel.getPlayer().getOwner().getUsername())) {
                        label = new Label("Private msg for: " + chatMessage.receiverUsername + "\t\t: " + chatMessage.message, skin);
                        label.setColor(Color.RED);
                    }
                }
            } else {
                HorizontalGroup messageGroup = new HorizontalGroup();
                messageGroup.wrap(true);
                messageGroup.space(5);

                String[] words = chatMessage.message.split(" ");
                Label loobel = new Label("Sender: " + chatMessage.senderUsername + "\t\tMessage: ",skin);
                messageGroup.addActor(loobel);
                for (String word : words) {
                    Label wordLabel = new Label(word + " ", skin);
                    if (word.startsWith("@")) {
                        wordLabel.setColor(Color.GREEN);
                    }
                    messageGroup.addActor(wordLabel);
                }

                row.add(messageGroup).width(600);
                label = null;
            }
            if (label != null) {
                row.add(label).width(600);
            }

            chatTable.add(row).fillX();
            chatTable.row();
        }
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public Stage getStage() {
        return stage;
    }
}
