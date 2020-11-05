package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Tank {
    private Texture tankTexture;
    private Texture weaponTexture;
    private Projectile projectile;
    private float angleTank;
    private float angleWeapon;
    private float x;
    private float y;
    private float speed;
    private int scale;

    public Tank() {
        tankTexture = new Texture("tank.png");
        weaponTexture = new Texture("weapon.png");
        projectile = new Projectile();
        x = 100.0f;
        y = 100.0f;
        scale = 3;
        speed = 200.0f;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getScale() {
        return scale;
    }

    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angleTank -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angleTank += 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            float preX = x + speed * MathUtils.cosDeg(angleTank) * dt;
            float preY = y + speed * MathUtils.sinDeg(angleTank) * dt;
            checkCoordinates(preX, preY);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            float preX = x - speed * MathUtils.cosDeg(angleTank) * dt;
            float preY = y - speed * MathUtils.sinDeg(angleTank) * dt;
            checkCoordinates(preX, preY);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            angleWeapon += 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            angleWeapon -= 90.0f * dt;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !projectile.isActive()) {
            projectile.shoot(x + 16 * scale * MathUtils.cosDeg(angleTank + angleWeapon), y + 16 * scale * MathUtils.sinDeg(angleTank + angleWeapon), angleTank + angleWeapon);
        }
        if (projectile.isActive())
            projectile.update(dt);
    }

    private void checkCoordinates(float x, float y) {
        int width = 1280;
        int height = 720;

        boolean moveApprove = true;

        if (x >= width - 20 * scale) {
            moveApprove = false;
        } else if (x - 20 * scale <= 0) {
            moveApprove = false;
        } else if (y >= height - 20 * scale) {
            moveApprove = false;
        } else if (y - 20 * scale <= 0) {
            moveApprove = false;
        }

        if (moveApprove) {
            this.x = x;
            this.y = y;
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(tankTexture, x - 20, y - 20, 20, 20, 40, 40, scale, scale, angleTank, 0, 0, 40, 40, false, false);
        batch.draw(weaponTexture, x - 20, y - 20, 20, 20, 40, 40, scale, scale, angleTank + angleWeapon, 0, 0, 40, 40, false, false);
        if (projectile.isActive())
            projectile.render(batch);
    }

    public void dispose() {
        tankTexture.dispose();
        weaponTexture.dispose();
    }

}
