package base.player;

import base.GameObject;
import base.Vector2D;
import base.enemy.Enemy;
import base.game.Setting;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBulletType2 extends PlayerBullet {
    public PlayerBulletType2(){
        super();
        createRenderer();
    }
    private void createRenderer() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages("assets/images/enemies/bullets/cyan.png"
                ,"assets/images/enemies/bullets/red.png"
                ,"assets/images/enemies/bullets/white.png"
                ,"assets/images/enemies/bullets/yellow.png");
        this.renderer = new AnimationRenderer(images);
    }

    @Override
    public void run() {
        super.run();
        this.moveToEnemy();

    }

    @Override
    public void reset() {
        super.reset();
    }

    private void moveToEnemy() {
        Enemy enemy = null;
        for(GameObject gameObject: GameObject.gameObjects){
            if(gameObject.isActive && gameObject instanceof Enemy){
                enemy = (Enemy) gameObject;
                break;
            }
        }
        if(enemy != null){
            Vector2D enemyPosition = enemy.position;
            Vector2D result = enemyPosition.substract(this.position).addThis(this.velocity);
            result.setLength(5);
            this.velocity.set(result);
        }
    }
}
