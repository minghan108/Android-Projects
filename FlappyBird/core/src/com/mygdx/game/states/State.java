package com.mygdx.game.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    // GameStageManager manages which state the game is in eg paused/play
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    //dt is the time it takes from on frame rendered to the next
    public abstract void update(float dt);
    //render is a container to allow all textures to render in one big blob
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


}
