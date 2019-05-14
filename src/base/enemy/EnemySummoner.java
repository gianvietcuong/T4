package base.enemy;

import base.FrameCounter;
import base.GameObject;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxRenderer;

import java.awt.*;
import java.util.EmptyStackException;

public class EnemySummoner extends GameObject implements Physics {
    BoxCollider boxCollider;
    FrameCounter summonCounter;
    public EnemySummoner(){
        //this.boxCollider = new BoxCollider(this.position,40,100);
        this.summonCounter= new FrameCounter(300);
        //this.renderer = new BoxRenderer(this.boxCollider, Color.white,true);
        this.position.set(20,100);
    }

    @Override
    public void run() {
        super.run();
        if(this.summonCounter.run()){
            this.summonEnemy();
            this.summonCounter.reset();
        }
    }

    private void summonEnemy() {
        Enemy enemy = GameObject.recycle(Enemy.class);
        enemy.position.set(this.position.add(20,0));

    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }
}
