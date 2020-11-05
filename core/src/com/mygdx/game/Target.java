package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Target {
    private Texture texture;
    private float x;
    private float y;
    private int scale;

    public Target() {
        texture = new Texture("block.png");
        scale = 3;
        setRandomPlace();
    }

    public void update(Projectile projectile) {
        if (projectile.isActive()) {
            float projectileX = projectile.getX();
            float projectileY = projectile.getY();

            if (projectileX > x - 10 * scale && projectileX < x + 10 * scale && projectileY > y - 10 * scale && projectileY < y + 10 * scale) {
                projectile.setActive(false);
                setRandomPlace();
            }

        }

    }

    public void update(Tank tank) {
        float tankX = tank.getX();
        float tankY = tank.getY();
        int tankScale = tank.getScale();

        if (tankX + 20 * tankScale > x - 10 * scale && tankX - 20 * tankScale < x + 10 * scale && tankY + 20 * tankScale > y - 10 * scale && tankY - 20 * tankScale < y + 10 * scale)
            setRandomPlace();

        if (tankX - 20 * tankScale < x + 10 * scale && tankX + 20 * tankScale > x - 10 * scale && tankY - 20 * tankScale < y + 10 * scale && tankY + 20 * tankScale > y - 10 * scale)
            setRandomPlace();

    }

    private void setRandomPlace() {
        x = (float) (Math.random() * 1240);
        y = (float) (Math.random() * 680);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 10, y - 10, 10, 10, 20, 20, scale, scale, 0, 0, 0, 20, 20, false, false);
    }

}
