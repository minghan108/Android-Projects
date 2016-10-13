package com.Ming.MathApp.states;

import com.Ming.MathApp.RandomNum;
import com.Ming.MathApp.setting.SettingsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class PlayState extends State {

    Stage stage;
    ImageButton correctBtn, wrongBtn;
    ImageButton.ImageButtonStyle correctBtnStyle, wrongBtnStyle;
    Skin skin;
    TextureAtlas correctBtnAtlas, wrongBtnAtlas;
    Texture background, plus, equal, numbers[];
    boolean correctPressed, wrongPressed;
    Table table;
    private float scaleX = Gdx.graphics.getWidth() * 0.5f;
    private float scaleY = Gdx.graphics.getHeight() * 0.1f;
    RandomNum randomNum;
    SettingsManager settingsManager;
    int num1, num2, realAns, tempAns, guess;
    BitmapFont font;
    float totalTime = 2;

    public PlayState(GameStateManager gsm){
        super(gsm);
        background = new Texture("bg.png");
        plus = new Texture("plus.png");
        equal = new Texture("equal.png");
        numbers = new Texture[34];
        numbers[1] = new Texture("1.png");
        numbers[2] = new Texture("2.png");
        numbers[3] = new Texture("3.png");
        numbers[4] = new Texture("4.png");
        numbers[5] = new Texture("5.png");
        numbers[6] = new Texture("6.png");
        numbers[7] = new Texture("7.png");
        numbers[8] = new Texture("8.png");
        numbers[9] = new Texture("9.png");
        numbers[10] = new Texture("10.png");
        numbers[11] = new Texture("11.png");
        numbers[12] = new Texture("12.png");
        numbers[13] = new Texture("13.png");
        numbers[14] = new Texture("14.png");
        numbers[15] = new Texture("15.png");
        numbers[16] = new Texture("16.png");
        numbers[17] = new Texture("17.png");
        numbers[18] = new Texture("18.png");
        numbers[19] = new Texture("19.png");
        numbers[20] = new Texture("20.png");
        numbers[21] = new Texture("21.png");
        numbers[22] = new Texture("22.png");
        numbers[23] = new Texture("23.png");
        numbers[24] = new Texture("24.png");
        numbers[25] = new Texture("25.png");
        numbers[26] = new Texture("26.png");
        numbers[27] = new Texture("27.png");
        numbers[28] = new Texture("28.png");
        numbers[29] = new Texture("29.png");
        numbers[30] = new Texture("30.png");
        numbers[31] = new Texture("31.png");
        numbers[32] = new Texture("32.png");
        numbers[33] = new Texture("33.png");




        font = new BitmapFont();
        settingsManager = SettingsManager.getInstance();
        randomNum = new RandomNum();
        num1 = randomNum.random(1, 15);
        num2 = randomNum.random(1, 15);
        guess = randomNum.random(1, 2);
        realAns = num1 + num2;
        if (guess == 1)
            tempAns = num1 + num2;
        if (guess == 2)
            tempAns = (num1 + num2) + randomNum.random(-3, 3);
        if (realAns == tempAns)
                guess = 1;



        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        cam.setToOrtho(false, background.getWidth(), background.getHeight());
        table = new Table();

        skin = new Skin();
        correctBtnAtlas = new TextureAtlas(Gdx.files.internal("correctBtn.atlas"));
        skin.addRegions(correctBtnAtlas);
        correctBtnStyle = new ImageButton.ImageButtonStyle();
        correctBtnStyle.up = skin.getDrawable("correctup");
        correctBtnStyle.down = skin.getDrawable("correctdown");
        correctBtn = new ImageButton(correctBtnStyle);
        correctBtn.setPosition(0, background.getHeight() * 0.1f);
        correctBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                correctPressed = true;
            }
        });

        wrongBtnAtlas = new TextureAtlas(Gdx.files.internal("wrongBtn.atlas"));
        skin.addRegions(wrongBtnAtlas);
        wrongBtnStyle = new ImageButton.ImageButtonStyle();
        wrongBtnStyle.up = skin.getDrawable("wrongup");
        wrongBtnStyle.down = skin.getDrawable("wrongdown");
        wrongBtn = new ImageButton(wrongBtnStyle);
        wrongBtn.setPosition(background.getWidth() * 0.5f, background.getHeight() * 0.1f);
        wrongBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                wrongPressed = true;
            }
        });

        table.setFillParent(true);
        table.add(correctBtn).width(scaleX).height(scaleX).padBottom(scaleY);
        table.add(wrongBtn).width(scaleX).height(scaleX).padBottom(scaleY);
        table.center().bottom();
        stage.addActor(table);
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        if (guess == 1) {
            if (correctPressed == true) {
                settingsManager.increaseScore();
                gsm.set(new PlayState(gsm));
            }
        }
        if (guess == 1) {
            if (wrongPressed == true) {
                settingsManager.resetScore();
                gsm.set(new MenuButton(gsm));
            }
        }
        if (guess == 2) {
            if (correctPressed == true) {
                settingsManager.resetScore();
                gsm.set(new MenuButton(gsm));
            }
        }
        if (guess == 2) {
            if (wrongPressed == true) {
                settingsManager.increaseScore();
                gsm.set(new PlayState(gsm));
            }
        }

        if (totalTime < 0){
            settingsManager.resetScore();
            gsm.set(new MenuButton(gsm));
        }

    }



    @Override
    public void render(SpriteBatch sb) {
        if (settingsManager.getScore() > 0) {
            float deltaTime = Gdx.graphics.getDeltaTime();
            totalTime -= deltaTime;
        }

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(numbers[num1], 40, background.getHeight() * 0.62f);
        sb.draw(plus, background.getWidth()/2 - (numbers[num1].getWidth()/2), background.getHeight()*0.62f);
        sb.draw(numbers[num2], numbers[num1].getWidth() + plus.getWidth() - 35, background.getHeight() * 0.62f);
        sb.draw(equal, 75, background.getHeight() * 0.62f - plus.getHeight());
        sb.draw(numbers[tempAns],55 + plus.getWidth(), background.getHeight() * 0.62f - plus.getHeight());
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(sb, settingsManager.getStringScore(), background.getWidth() * 0.9f, background.getHeight() * 0.9f);
        //use freetype font, tutorial in bookmarks
        font.getData().setScale(3, 3);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
        numbers[num1].dispose();
        plus.dispose();
        numbers[num2].dispose();
        equal.dispose();
        numbers[tempAns].dispose();
    }
}
