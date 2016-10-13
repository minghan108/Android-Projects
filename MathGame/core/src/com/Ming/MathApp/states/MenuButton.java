package com.Ming.MathApp.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by MingHan on 8/26/2015.
 */
public class MenuButton extends State{
    Stage stage;
    ImageButton button;
    //TextButton.TextButtonStyle textButtonStyle;
    ImageButton.ImageButtonStyle imageButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    Texture background;
    private int playBtnX;
    private int playBtnY;
    Table table;
    boolean pressed;
    private float scale = Gdx.graphics.getWidth() * 0.3f;



    public MenuButton(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        cam.setToOrtho(false, background.getWidth(), background.getHeight());

        table = new Table();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("playBtn.atlas"));
        skin.addRegions(buttonAtlas);
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("playbtnup");
        imageButtonStyle.down = skin.getDrawable("playbtndown");
        button = new ImageButton(imageButtonStyle);
//        playBtnX = background.getWidth()/2-playBtnUp.getWidth()/2;
//        playBtnY = background.getHeight()/2-playBtnUp.getHeight()/2;
//        button.setPosition(playBtnX, playBtnY);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pressed = true;
            }
        });

        //table.setBounds(0, 0, 480, 800);
        table.setFillParent(true);
        table.add(button).width(scale).height(scale);
        stage.addActor(table);
    }



    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
    if (pressed)
        gsm.set(new PlayState(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.end();
        stage.draw();

    }

    @Override
    public void dispose() {
        background.dispose();
        skin.dispose();
        stage.dispose();
    }
}
