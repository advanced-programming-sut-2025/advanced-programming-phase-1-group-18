package io.github.group18.View;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import io.github.group18.Controller.RadioMenuController;

import java.io.File;

public class RadioWindow extends Window {

    public RadioWindow(Skin skin, Stage stage) {
        super("Radio System", skin);

        setSize(1000, 800);
        setMovable(true);
        pad(20);
        setPosition(
            (stage.getWidth() - getWidth()) / 2f,
            (stage.getHeight() - getHeight()) / 2f
        );

        Label infoLabel = new Label("Upload a music file (.wav):", skin);
        Label resultLabel = new Label("", skin);
        Label nowPlayingLabel = new Label("", skin); // نمایش عنوان موسیقی در حال پخش

        // ===== Upload Button =====
        TextButton uploadButton = new TextButton("Choose File", skin);
        uploadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("eropjk");
                java.awt.FileDialog dialog = new java.awt.FileDialog((java.awt.Frame) null, "Select WAV");
                dialog.setMode(java.awt.FileDialog.LOAD);
                dialog.setVisible(true);
                System.out.println("gooz");
                if (dialog.getFile() != null) {
                    File selectedFile = new File(dialog.getDirectory(), dialog.getFile());
                    String fileName = selectedFile.getName().toLowerCase();

                    if (fileName.endsWith(".wav")) {
                        RadioMenuController.uploadMusicFileChunked(selectedFile);
                        resultLabel.setText("Music uploaded: " + selectedFile.getName());
                    } else {
                        resultLabel.setText("Invalid file type. Please select a WAV file.");
                    }
                }
            }
        });

        // ===== Play Button =====
        TextButton playButton = new TextButton("Play", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (RadioMenuController.latestReceivedFile != null) {
                    RadioMenuController.playMusic(RadioMenuController.latestReceivedFile);
                    nowPlayingLabel.setText("Now Playing: " + RadioMenuController.latestReceivedFile.getName());
                } else {
                    nowPlayingLabel.setText("No music received.");
                }
            }
        });

        // ===== Stop Button =====
        TextButton stopButton = new TextButton("Stop", skin);
        stopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                RadioMenuController.stopCurrentMusic();
                nowPlayingLabel.setText("Playback stopped.");
            }
        });

        // ===== Close Button =====
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        // ===== Layout =====
        add(infoLabel).left().padBottom(10).row();
        add(uploadButton).width(200).padBottom(10).row();
        add(resultLabel).left().padBottom(15).row();
        add(playButton).width(100).padBottom(10).left().row();
        add(stopButton).width(100).padBottom(10).left().row();
        add(nowPlayingLabel).left().padBottom(20).row();
        add(closeButton).padTop(10);
    }
}
