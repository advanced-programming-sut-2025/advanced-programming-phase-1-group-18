package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.group18.Controller.ChoosingMapController;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Controller.StartNewGameController;
import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;

public class ChoosingMapView implements Screen{
    private ChoosingMapController controller;
    private GameMenuController gameMenuController;
    private StartNewGameController startNewGameController;
    private Stage stage;
    private TextButton selectMapButton1;
//    private TextButton selectMapButton2;
//    private TextButton selectMapButton3;
//    private TextButton selectMapButton4;

    private SelectBox<Integer> chooseMapBox1;
//    private SelectBox<Integer> chooseMapBox2;
//    private SelectBox<Integer> chooseMapBox3;
//    private SelectBox<Integer> chooseMapBox4;

    private Texture wholeMap;
    private Texture map1;
    private Texture map2;
    private Texture map3;
    private Texture map4;
    private TextButton playButton;
    private TextButton backButton;
    private Table table;
    private Skin skin;
    private Label notif;
    private Label menuTitle;
    private GameMenuController menuController;

    public ChoosingMapView(ChoosingMapController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage();
        this.table = new Table();
        this.playButton = new TextButton("Play Game", skin);
        this.backButton = new TextButton("Back", skin);
        this.notif = new Label("", skin);
        this.menuTitle = new Label("Choose Map", skin,"title");
        this.selectMapButton1 = new TextButton("Select Map", skin);

        this.chooseMapBox1=new SelectBox<>(skin);
//        this.chooseMapBox2=new SelectBox<>(skin);
//        this.chooseMapBox3=new SelectBox<>(skin);
//        this.chooseMapBox4=new SelectBox<>(skin);

        this.chooseMapBox1.setItems(1,2,3,4);
//        this.chooseMapBox2.setItems(1,2,3,4);
//        this.chooseMapBox3.setItems(1,2,3,4);
//        this.chooseMapBox4.setItems(1,2,3,4);

        this.map1 = GameAssetManager.getGameAssetManager().getMapDeafultTexture();
        this.map2 = GameAssetManager.getGameAssetManager().getMapDeafultTexture();
        this.map3 = GameAssetManager.getGameAssetManager().getMapDeafultTexture();
        this.map4 = GameAssetManager.getGameAssetManager().getMapDeafultTexture();
        this.wholeMap = GameAssetManager.getGameAssetManager().getBackgroundMapTexture();

    }

    @Override
    public void show() {
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(menuTitle);
        table.row();
        table.add(chooseMapBox1);
        table.add(selectMapButton1);
        table.row();
//        table.add(chooseMapBox2);
//        table.add(selectMapButton2);
//        table.row();
//        table.add(chooseMapBox3);
//        table.add(selectMapButton3);
//        table.row();
//        table.add(chooseMapBox4);
//        table.add(selectMapButton4);
//        table.row();
        table.add(playButton);
        table.row();
        table.add(notif);
        stage.addActor(table);
        controller.setView(this);
        controller.arrangeVisibily();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getMain().getBatch().begin();
        controller.arrangeVisibily();
        Main.getMain().getBatch().draw(GameAssetManager.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        int x=100;
        int y=200;
        Main.getMain().getBatch().draw(wholeMap, x, y);
        float bgWidth = wholeMap.getWidth();
        float bgHeight = wholeMap.getHeight();

        // Draw top-left image
        Main.getMain().getBatch().draw(map1,
            x, // x
            y+bgHeight-map1.getHeight() // y
        );

        // Draw top-right image
        Main.getMain().getBatch().draw(map2,
            bgWidth - map2.getWidth()+x, // x
            bgHeight - map2.getHeight()+y // y
        );

        // Draw bottom-left image
        Main.getMain().getBatch().draw(map3,
            x, // x
            y // y
        );

        // Draw bottom-right image
        Main.getMain().getBatch().draw(map4,
            bgWidth - map4.getWidth()+x, // x
            y // y
        );
        Main.getMain().getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleStartNewGame();
    }

    @Override
    public void resize(int width, int height) {

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

    }

    public void SetPlace(){
        int x=100;
        int y=200;
        Main.getMain().getBatch().draw(wholeMap, x, y);
        float bgWidth = wholeMap.getWidth();
        float bgHeight = wholeMap.getHeight();

        // Draw top-left image
         Main.getMain().getBatch().draw(map1,
            x, // x
            bgHeight - map1.getHeight() // y
        );

        // Draw top-right image
         Main.getMain().getBatch().draw(map2,
            bgWidth - map2.getWidth(), // x
            bgHeight - map2.getHeight() // y
        );

        // Draw bottom-left image
         Main.getMain().getBatch().draw(map3,
            x, // x
            y // y
        );

        // Draw bottom-right image
         Main.getMain().getBatch().draw(map4,
            bgWidth - map4.getWidth(), // x
            y // y
        );
    }
    public ChoosingMapController getController() {
        return controller;
    }

    public void setController(ChoosingMapController controller) {
        this.controller = controller;
    }

    public GameMenuController getGameMenuController() {
        return gameMenuController;
    }

    public void setGameMenuController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }

    public StartNewGameController getStartNewGameController() {
        return startNewGameController;
    }

    public void setStartNewGameController(StartNewGameController startNewGameController) {
        this.startNewGameController = startNewGameController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getSelectMapButton1() {
        return selectMapButton1;
    }

    public void setSelectMapButton1(TextButton selectMapButton1) {
        this.selectMapButton1 = selectMapButton1;
    }

//    public TextButton getSelectMapButton2() {
//        return selectMapButton2;
//    }
//
//    public void setSelectMapButton2(TextButton selectMapButton2) {
//        this.selectMapButton2 = selectMapButton2;
//    }
//
//    public TextButton getSelectMapButton3() {
//        return selectMapButton3;
//    }
//
//    public void setSelectMapButton3(TextButton selectMapButton3) {
//        this.selectMapButton3 = selectMapButton3;
//    }
//
//    public TextButton getSelectMapButton4() {
//        return selectMapButton4;
//    }
//
//    public void setSelectMapButton4(TextButton selectMapButton4) {
//        this.selectMapButton4 = selectMapButton4;
//    }

    public SelectBox<Integer> getChooseMapBox1() {
        return chooseMapBox1;
    }

    public void setChooseMapBox1(SelectBox<Integer> chooseMapBox1) {
        this.chooseMapBox1 = chooseMapBox1;
    }

//    public SelectBox<Integer> getChooseMapBox2() {
//        return chooseMapBox2;
//    }
//
//    public void setChooseMapBox2(SelectBox<Integer> chooseMapBox2) {
//        this.chooseMapBox2 = chooseMapBox2;
//    }
//
//    public SelectBox<Integer> getChooseMapBox3() {
//        return chooseMapBox3;
//    }
//
//    public void setChooseMapBox3(SelectBox<Integer> chooseMapBox3) {
//        this.chooseMapBox3 = chooseMapBox3;
//    }
//
//    public SelectBox<Integer> getChooseMapBox4() {
//        return chooseMapBox4;
//    }
//
//    public void setChooseMapBox4(SelectBox<Integer> chooseMapBox4) {
//        this.chooseMapBox4 = chooseMapBox4;
//    }

    public Texture getWholeMap() {
        return wholeMap;
    }

    public void setWholeMap(Texture wholeMap) {
        this.wholeMap = wholeMap;
    }

    public Texture getMap1() {
        return map1;
    }

    public void setMap1(Texture map1) {
        this.map1 = map1;
    }

    public Texture getMap2() {
        return map2;
    }

    public void setMap2(Texture map2) {
        this.map2 = map2;
    }

    public Texture getMap3() {
        return map3;
    }

    public void setMap3(Texture map3) {
        this.map3 = map3;
    }

    public Texture getMap4() {
        return map4;
    }

    public void setMap4(Texture map4) {
        this.map4 = map4;
    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(TextButton playButton) {
        this.playButton = playButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Label getNotif() {
        return notif;
    }

    public void setNotif(Label notif) {
        this.notif = notif;
    }

    public Label getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(Label menuTitle) {
        this.menuTitle = menuTitle;
    }

    public GameMenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(GameMenuController menuController) {
        this.menuController = menuController;
    }
}
