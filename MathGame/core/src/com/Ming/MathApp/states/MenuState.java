package com.Ming.MathApp.states;

import com.Ming.MathApp.MathLink;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by MingHan on 8/23/2015.
 */
public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    public InputListener input;
    private boolean touched;
    private int playBtnX;
    private int playBtnY;


    public MenuState(GameStateManager gsm) {
        super(gsm);
//        for android

        background = new Texture("bg.png");
        playBtn = new Texture("playbtnup.png");
        cam.setToOrtho(false, background.getWidth()/2, background.getHeight()/2);
        playBtnX = background.getWidth()/2-playBtn.getWidth()/2;
        playBtnY = background.getHeight()/2-playBtn.getHeight()/2;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button){
        if ((screenX > playBtnX) && (screenX < (playBtnX + playBtn.getWidth()))) {
            if ((screenY > playBtnY) && (screenY < (playBtnY + playBtn.getHeight()))) {

                touched = true;
            }
        }
        return true;
    }

    @Override
    protected void handleInput() {
        if(touched = true){
        //if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }



    @Override
    public void update(float dt) {
        //touchDown();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
//        for android
        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, playBtnX, playBtnY);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
