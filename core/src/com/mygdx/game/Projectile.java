package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Projectile {
    private Texture texture;
    private float x;
    private float y;
    private float vx;
    private float vy;
    private float speed;
    private boolean active;

    public Projectile() {
        texture = new Texture("projectile.png");
        speed = 800.0f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void shoot(float x, float y, float angle) {
        active = true;
        this.x = x;
        this.y = y;
        this.vx = speed * MathUtils.cosDeg(angle);
        this.vy = speed * MathUtils.sinDeg(angle);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void update(float dt) {
        x += vx * dt;
        y += vy * dt;
        if (x < 0 || x > 1280 || y < 0 || y > 720)
            active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 8, y - 8, 8, 8, 16, 16, 2, 2, 0, 0, 0, 16, 16, false, false);
    }

}
