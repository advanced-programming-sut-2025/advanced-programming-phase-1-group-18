package io.github.group18.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.group18.Model.App;
import io.github.group18.Model.DateTime;
import io.github.group18.View.GameView;

public class GameController {
    GameView view;
    private SpriteBatch batch = new SpriteBatch();
    private ClockController clockController;


    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
        clockController = new ClockController(App.getCurrentGame().getCurrentDateTime());
    }

    public void updateGame(float delta) {
        if (view != null) {
            DateTime time = App.getCurrentGame().getCurrentDateTime();
            clockController.update();
        }
    }
}
