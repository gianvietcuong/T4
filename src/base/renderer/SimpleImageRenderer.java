package base.renderer;

import base.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleImageRenderer  extends Renderer {
    BufferedImage image;

    public SimpleImageRenderer(BufferedImage image){
        this.image = image;
    }
    @Override
    public void render(Graphics g, GameObject master) {
        int x= (int) (master.position.x - master.anchor.x*image.getWidth());
        int y = (int)(master.position.y - master.anchor.y*image.getHeight());
        g.drawImage(this.image, x,y, null);
    }

}
