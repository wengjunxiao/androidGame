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



public class StoreStage extends Stage {
	
	Texture texture;
	TextureRegion goodsSelfRegion;
	TextureRegion heartRegion;
	TextureRegion goldRegion;

	Image heart;
	Image gold;
	Image goodsShelf;
	
	
	Image img;
	Mario mario;
	
	public StoreStage() {
		super(new FitViewport(320,240));
		init();
	}
	
	public void init() {
		
		
		texture = new Texture(Gdx.files.internal("shopping_stage.png"));
		goodsSelfRegion = new TextureRegion(texture,0,85,510,350);
		goodsShelf = new Image(goodsSelfRegion);
		goodsShelf.setSize(480, 320);
		
		heartRegion = new TextureRegion(texture,0,0,102,85);
		heart = new Image(heartRegion);
		
		goldRegion = new TextureRegion(texture,102,0,102,85);
		gold = new Image(goldRegion);
		
		gold.setPosition(50, 50);
		heart.setPosition(190, 50);
		
		this.addActor(goodsShelf);
		this.addActor(gold);
		this.addActor(heart);
		
		heart.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				Constants.Stageflag = Constants.StartStageOn;
				return true;
			}
		});
		
		gold.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				Constants.Stageflag = Constants.StartStageOn;
				return true;
			}
		});
		
		
		
		
	}
}
