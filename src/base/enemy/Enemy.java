package base.enemy;
import base.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import base.game.GameCanvas;
import base.GameObject;
import base.game.Setting;
import base.renderer.BoxRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject implements Physics {
    FrameCounter fireCounter;
    BoxCollider boxCollider;
    int hp;
    boolean immune;
    FrameCounter immuneCounter;
    int countTime=0;
    public Enemy() {
        super();
        this.velocity.set(0,3);
        this.position.set(50, 50);
        this.fireCounter = new FrameCounter(15);
        this.boxCollider = new BoxCollider(this.position,28,28);
        this.hp =3;
        this.immune= false;
        this.immuneCounter = new FrameCounter(60);
        this.createRenderer();
    }

    protected void createRenderer() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages("assets/images/enemies/level0/blue/0.png"
                ,"assets/images/enemies/level0/blue/1.png"
                ,"assets/images/enemies/level0/blue/2.png"
                ,"assets/images/enemies/level0/blue/3.png");
        this.renderer = new AnimationRenderer(images);
        //this.renderer = new BoxRenderer(this.boxCollider);


    }

    boolean check = true;
    @Override

    public void run() {
        super.run();
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png");
            if (this.position.x <= 0) {
                check = true;
            }
            if (this.position.x >= Setting.BACKGROUND_WIDTH - image.getWidth()) {
                check = false;
            }
            if (check) {
                this.velocity.set(3, 0);
            }
            if (!check) {
                this.velocity.set(-3, 0);
            }
            fire();
    }


    public void fire(){
        if(this.fireCounter.run()) {
            EnemyBullet enemybullet = GameObject.recycle(EnemyBullet.class);
            enemybullet.position.set(this.position);
            this.fireCounter.reset();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EnemyExplosion enemyExplosion = GameObject.recycle(EnemyExplosion.class);
        enemyExplosion.position.set(this.position);
    }

    @Override
    public void reset() {
        super.reset();
        this.immune =false;
        this.immuneCounter.reset();
        this.hp=3;
    }

    public void takeDamge(int damge){
        if(this.immune){
            return;
        }
        this.hp -= damge;
        if(this.hp <=0){
            this.hp=0;
            this.destroy();
        }else{
            this.immune = true;
            this.immuneCounter.reset();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void render(Graphics g) {
        if(this.immune){
            if(this.immuneCounter.run()){
                this.immune = false;
            }
            if(this.immuneCounter.count %4 == 0){
                super.render(g);
            }
        }else{
            super.render(g);
        }
    }
}