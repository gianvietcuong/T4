package base;

import base.renderer.SimpleImageRenderer;
import base.game.Setting;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class BackGround extends GameObject {

    public BackGround(){
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/background/0.png");
        this.renderer = new SimpleImageRenderer(image) ;
        this.position.set(0, Setting.SCREEN_HEIGHT- image.getHeight());
        this.velocity.set(0,10);
        this.anchor.set(0,0);
    }

    public void run(){
        super.run();
        if(this.position.y >=0){
            this.velocity.set(0,0);
        }
    }
}
