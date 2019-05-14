package base.enemy;

import base.GameObject;
import base.game.Setting;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.BoxRenderer;
import base.renderer.SimpleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject implements Physics {

    BoxCollider boxCollider;
    public EnemyBullet(){
        super();
//        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
//        this.renderer = new SimpleImageRenderer(image);
        this.velocity.set(0,5);
        this.boxCollider = new BoxCollider(this.position,8,8);
        this.renderer = new BoxRenderer(this.boxCollider, Color.red,true);
    }

    @Override
    public void run() {
        super.run();
        this.destroyIfNeeded();
        this.hitPlayer();
    }

    protected void hitPlayer() {
        Player player = GameObject.intersects(Player.class, this.boxCollider);
        if (player != null) {
            if(player.hited() <=0){
                player.destroy();
            }else{
                player.hited();
            }
            this.destroy();
        }
    }
    protected void destroyIfNeeded() {
        if(this.position.y> Setting.SCREEN_HEIGHT || this.position.x < 0|| this.position.x > Setting.BACKGROUND_WIDTH){
            this.destroy();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
