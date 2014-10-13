package com.mygdx.game;


import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



public class BlackWhite extends Actor {
	
	public int [] data;
	public int cursor;
	public float offset;
	public static final int DEFAULT_WIDTH=3,DEFAULT_HEIGHT=50;
	public static final int INSCREEN_HEIGHT = 4;
	public static final int TILE_HEIGHT = 160, TILE_WIDTH = 160;
	public static final float SCROLL_SPEED = 1200f;
	public  boolean animating;
	ShapeRenderer sr;
	
	public BlackWhite() {
		cursor = 0;
		data = new int[DEFAULT_HEIGHT];
		animating = false;
		init();
		
	}
	
	public void init() {
		int i;
		for(i = 0;i < data.length;i ++){
			Random rn = new Random();
			data[i] = rn.nextInt(DEFAULT_WIDTH);
		}
		
	}
	
	public void update() {
		if(animating == true) {
			offset = offset + Gdx.graphics.getDeltaTime()*SCROLL_SPEED; 
			if(offset >= TILE_HEIGHT) {
				cursor += 1;
				animating = false;
			}
			
		}
		
	}
	
	
	
	public void check() {
			sr.setColor(1, 1, 1, 1);
			sr.begin(ShapeType.Filled);
			
			for(int i = cursor; i  < cursor + INSCREEN_HEIGHT ; i ++ ) {
				sr.rect(data[i]*TILE_WIDTH, (i - cursor)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
			}
			
			sr.begin(ShapeType.Line);
			for(int i = cursor;i < cursor + INSCREEN_HEIGHT;i ++) {
				for(int j = 0;j < DEFAULT_WIDTH;j ++) {
					sr.rect(j*TILE_WIDTH, (i - cursor)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);		
				}

			}
			sr.end();
	}
	

	
	@Override
	public void draw(Batch batch, float parentAlpha){		
		this.update();
		this.check();
		
	}
}
