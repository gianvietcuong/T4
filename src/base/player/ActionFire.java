package base.player;

import base.FrameCounter;
import base.GameObject;
import base.KeyEventPress;
import base.action.Action;

public class ActionFire implements Action {
    FrameCounter fireCounter;
    boolean b1,b2,b3;

    public ActionFire(){
        this.fireCounter = new FrameCounter(10);
    }
    @Override
    public void run(GameObject master) {
        if( this.fireCounter.run() && KeyEventPress.isFirePress  ){
            this.fire(master);
        }
    }

    public void fire(GameObject master){
        PlayerBulletType1 bullet = GameObject.recycle(PlayerBulletType1.class);
        PlayerBulletType2 bullet1 = GameObject.recycle(PlayerBulletType2.class);
        PlayerBulletType2 bullet2 = GameObject.recycle(PlayerBulletType2.class);
        bullet.position.set(master.position);
        bullet1.position.set(master.position.add(-20,0));
        bullet1.velocity.set(-5,-5);
        bullet2.position.set(master.position.add(30,0));
        bullet2.velocity.set(5,-5);
        this.fireCounter.reset();
    }
}
