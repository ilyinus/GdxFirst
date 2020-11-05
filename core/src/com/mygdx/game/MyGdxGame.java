package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Tank tank;
	Target target;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		tank = new Tank();
		target = new Target();
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		tank.update(dt);
		target.update(tank.getProjectile());
		target.update(tank);

		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		target.render(batch);
		tank.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		tank.dispose();
	}
}
