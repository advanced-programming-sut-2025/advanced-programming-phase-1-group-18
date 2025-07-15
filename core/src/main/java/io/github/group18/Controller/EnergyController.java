package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;

public class EnergyController {
    private Sprite energyTexture;
    private Sprite greenBarTexture;
    private float maxEnergyLen;
    private int energy;
    Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
    int scale;
    public EnergyController(){
        scale = 2;
        energyTexture = new Sprite(GameAssetManager.getGameAssetManager().getBarTexture());
        greenBarTexture = new Sprite(GameAssetManager.getGameAssetManager().getGreenBarTexture());
        energyTexture.setSize(energyTexture.getWidth()*scale, energyTexture.getHeight()*scale);
        greenBarTexture.setSize(greenBarTexture.getWidth()*scale, greenBarTexture.getHeight()*scale);
        energyTexture.setPosition(Gdx.graphics.getWidth()-energyTexture.getWidth()-10,
            10);
        greenBarTexture.setPosition(energyTexture.getX()+9*scale, 10+6*scale);
        energy = currentPlayer.getEnergy();
        maxEnergyLen = greenBarTexture.getHeight();
    }
    public void render(SpriteBatch batch){
        float nesbat = (float) currentPlayer.getEnergy() /currentPlayer.getMaxEnergy();
        greenBarTexture.setSize(greenBarTexture.getWidth(), nesbat*maxEnergyLen);
        energyTexture.draw(batch);
        greenBarTexture.draw(batch);
    }

}
