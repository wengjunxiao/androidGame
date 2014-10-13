package com.mygdx.game;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;



public class StartStage extends Stage {
	
	Texture texture;
	TextureRegion newGameRegion;
	Image newGameBtn;
	
	public StartStage() {
		super(new FitViewport(640,480));
		init();
	}
	
	public void init() {
		texture = new Texture(Gdx.files.internal("start_stage.png"));
		
		newGameRegion = new TextureRegion(texture,924,0,100,50);
		newGameBtn = new Image(newGameRegion);
		newGameBtn.setPosition(320, 240);
		
		this.addActor(newGameBtn);
		
		newGameBtn.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				Constants.Stageflag = Constants.GameStageOn;
				return true;
			}
		});
		
		
		
		
	}
}
