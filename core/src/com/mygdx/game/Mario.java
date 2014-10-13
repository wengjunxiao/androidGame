package com.mygdx.game;


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



public class Mario extends Actor {
	public float x;
	public float y;
	public float statetime;
	
	Texture texture;
	TextureRegion currentFrame;
	
	ImageButton buttonL;
	ImageButton buttonR;
	
	Animation aniRight;
	Animation aniLeft;
	Animation aniIdle;
	
	STATE state;
	
	enum STATE{Left,Right,Idle};
	
	public Mario(float x, float y) {
		this.x = x;
		this.y = y;
		statetime = 0;
		this.show();
		state = STATE.Idle;
		
	}
	
	public void show() {
		this.texture = new Texture(Gdx.files.internal("mario.png"));
		
		TextureRegion[][] split = TextureRegion.split(texture, 64, 64);
		TextureRegion[][] mirror = TextureRegion.split(texture, 64, 64);
		
		for(TextureRegion[] region1 : mirror) {
			for(TextureRegion region2 : region1) {
				region2.flip(true, false);
			}
		}
		
		TextureRegion [] regionR = new TextureRegion [3];
		regionR[0] = split[0][1];
		regionR[1] = split[0][2];
		regionR[2] = split[0][0];
		this.aniRight = new Animation(0.1f, regionR);
		
		TextureRegion [] regionL = new TextureRegion [3];
		regionL[0] = mirror[0][1];
		regionL[1] = mirror[0][2];
		regionL[2] = mirror[0][0];
		this.aniLeft = new Animation(0.1f, regionL);
		
		TextureRegion[] regionI = new TextureRegion[1];
		regionI[0] = split[0][0];
		this.aniIdle = new Animation(0.1f, regionI);
		
		this.buttonL = new ImageButton(new TextureRegionDrawable(mirror[1][0]),new TextureRegionDrawable(mirror[1][1]));
		this.buttonR = new ImageButton(new TextureRegionDrawable(split[1][0]),new TextureRegionDrawable(split[1][1]));
		
		buttonL.setPosition(100, 150);
		buttonR.setPosition(20, 150);
		
		buttonL.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.Idle;
				super.touchUp(event, x, y, pointer, button);
			}
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.Right;
				return true;
			}
		});
		
		buttonR.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.Idle;
				super.touchUp(event, x, y, pointer, button);
			}
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.Left;
				return true;
			}
		});
		
		
	}
	
	public void check() {
		if(state == STATE.Left) {
			currentFrame = aniLeft.getKeyFrame(statetime,true);
		} else if(state == STATE.Right) {
			currentFrame = aniRight.getKeyFrame(statetime,true);
		} else {
			currentFrame = aniIdle.getKeyFrame(statetime,true);
		}
	}
	
	public void update() {
		if(state == STATE.Left) {
			x -= 1.5f;
			if(this.x < 20) x = 20;
		} else if(state == STATE.Right) {
			x += 1.5f;
			if(this.x > 200) x = 200;
		}
		//this.x = x;

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		statetime += Gdx.graphics.getDeltaTime();
		
		this.update();
		this.check();
		
		batch.draw(currentFrame, x, y);
	}
}
