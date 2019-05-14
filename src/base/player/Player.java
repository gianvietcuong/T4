package base.player;

import base.*;
import base.action.Action;
import base.enemy.EnemyExplosion;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import base.game.GameCanvas;
import base.game.Setting;
import base.renderer.BoxRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Physics {
    Action action;
    BoxCollider boxCollider;
    int hpPlayer;
    boolean isHited;


    public Player(){
        super();
        this.action = new ActionFire();
        this.position.set(200,300);
        this.hpPlayer = 500000;
        this.isHited = false;
        this.boxCollider = new BoxCollider(this.position,32,48);
        this.createRenderer();
    }


    @Override
    public void destroy() {
            super.destroy();
            PlayerExplosion playerExplosion = GameObject.recycle(PlayerExplosion.class);
            playerExplosion.position.set(this.position);


    }
    public int hited(){
        this.isHited =true;
        return this.hpPlayer--;
    }

    private void createRenderer() {
            ArrayList<BufferedImage> images = SpriteUtils.loadImages("assets/images/players/straight/0.png"
                    , "assets/images/players/straight/1.png"
                    , "assets/images/players/straight/2.png"
                    , "assets/images/players/straight/3.png"
                    , "assets/images/players/straight/4.png"
                    , "assets/images/players/straight/5.png"
                    , "assets/images/players/straight/6.png");
            this.renderer = new AnimationRenderer(images);
            //this.renderer = new BoxRenderer(this.boxCollider);
    }

    public void run() {
            this.move();
            this.action.run(this);
            super.run();
    }
    private void move(){
        int vx = 0;
        int vy = 0;
        if ( KeyEventPress.isUpPress) {
            if(this.position.y >0) {
                vy -= 3;
            }else{
                vy = 0;
            }
        }
        if ( KeyEventPress.isDownPress) {
            if(this.position.y< Setting.SCREEN_HEIGHT-Setting.PLAYER_HEIGHT) {
                vy += 3;
            }else{
                vy = 0;
            }
        }

        if ( KeyEventPress.isLeftPress) {
            if(this.position.x>20) {
                vx -= 3;
            }else{
                vx = 0;
            }
        }
        if ( KeyEventPress.isRightPress) {
            if(this.position.x<Setting.BACKGROUND_WIDTH-Setting.PLAYER_WIDTH-16) {
                vx += 2;
            }else{
                vx = 0;
            }
        }
        if(!KeyEventPress.isUpPress && !KeyEventPress.isDownPress && !KeyEventPress.isLeftPress && !KeyEventPress.isRightPress){
            this.velocity.set(0,0);
        }
        this.velocity.set(vx,vy);
    }



    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
