package com.mygdx.game;


import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyGdxGame extends ApplicationAdapter {
	
	GameStage gameStage;
	StoreStage storeStage;
	StartStage startStage;
	OrthographicCamera cam;
	ShapeRenderer sr;
	
	
	@Override
	public void create () {
		gameStage = new GameStage();
		storeStage = new StoreStage();
		startStage = new StartStage();
	}
	
	 @Override
	   public void dispose() {
	   }

	 
	public void selectRender() {
		if(Constants.Stageflag == Constants.StartStageOn) {
			Gdx.input.setInputProcessor(startStage);
			startStage.act();
			startStage.draw();
		} else if(Constants.Stageflag == Constants.GameStageOn) {
			Gdx.input.setInputProcessor(gameStage);
			sr = new ShapeRenderer();
			cam = new OrthographicCamera();
			initiateCamera();
			sr.setProjectionMatrix(cam.combined);
			gameStage.setBlackWhite(sr);
			handleInput();
			gameStage.act();
			gameStage.draw();
		} else if(Constants.Stageflag == Constants.OverStageOn) {
			Gdx.input.setInputProcessor(storeStage);
			storeStage.act();
			storeStage.draw();
		}
	}
	
	public void initiateCamera() {
		cam.setToOrtho(false, 480, 800);
		cam.position.add(240, 400, 0f);
		cam.update();
		
	}
	
	public void handleInput() {
		if(Gdx.input.isTouched() == true) {
			gameStage.blackWhite.animating = true;
		}
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		selectRender();
		
	
	}
}
