package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;

import java.util.ArrayList;
import java.util.List;

public class MarriageResponseWindow extends Window {

    public MarriageResponseWindow(Skin skin, Stage stage, Player currentPlayer) {
        super("💍 Respond to Marriage Requests", skin);
        setSize(1000, 800);
        setMovable(true);
        pad(20);
        setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);

        Label selectLabel = new Label("Select a Proposal:", skin);
        SelectBox<String> proposalBox = new SelectBox<>(skin);
        Label messageLabel = new Label("", skin);

        List<MarriageProposal> proposals = App.getCurrentGame().getMarriageProposals();
        List<String> validProposers = new ArrayList<>();

        for (MarriageProposal proposal : proposals) {
            if (proposal.getReceiver().equals(currentPlayer)) {
                String entry = proposal.getProposer().getOwner().getUsername() + " 💍 (" + proposal.getRing().getCorrectName() + ")";
                validProposers.add(entry);
            }
        }

        if (validProposers.isEmpty()) {
            messageLabel.setText("You have no marriage proposals.");
        } else {
            proposalBox.setItems(validProposers.toArray(new String[0]));
        }

        TextButton acceptButton = new TextButton("Accept", skin);
        TextButton rejectButton = new TextButton("Reject", skin);
        TextButton closeButton = new TextButton("Close", skin);

        acceptButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (validProposers.isEmpty()) return;
                String selected = proposalBox.getSelected().split(" ")[0]; // Extract username
                Result result = GameMenuController.response("-accept", selected);
                messageLabel.setText(result.getMessage());
            }
        });

        rejectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (validProposers.isEmpty()) return;
                String selected = proposalBox.getSelected().split(" ")[0]; // Extract username
                Result result = GameMenuController.response("-reject", selected);
                messageLabel.setText(result.getMessage());
            }
        });

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        // Layout
        add(selectLabel).left().row();
        if (!validProposers.isEmpty()) add(proposalBox).width(300).padBottom(15).row();
        add(acceptButton).pad(5);
        add(rejectButton).pad(5).row();
        add(messageLabel).colspan(2).padTop(10).row();
        add(closeButton).colspan(2).padTop(20);

        stage.addActor(this);
    }
}
