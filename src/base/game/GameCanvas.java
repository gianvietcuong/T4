package base.game;

import base.BackGround;
import base.GameObject;
import base.Sound;
import base.enemy.EnemySummoner;
import base.player.Player;
import javax.swing.*;
import java.awt.*;


public class GameCanvas extends JPanel {
    public GameObject background;
    public GameObject  player;
    public GameObject enemy;
    public EnemySummoner enemySummoner;
        public GameCanvas(){
            this.background = GameObject.recycle(BackGround.class);
            this.player= GameObject.recycle(Player.class);
            this.enemySummoner = GameObject.recycle(EnemySummoner.class);
            Sound sound = new Sound();
        }

    @Override
    protected void paintComponent(Graphics g) {

        for(int i=0;i< GameObject.gameObjects.size();i++){
            GameObject gameObject = GameObject.gameObjects.get(i);
            if(gameObject.isActive){
                gameObject.render(g);
            }
        }
    }
    public void gameLoop(){
            int delay = 1000/60;
            long lastRun =0;
            while (true){
                long currentTime = System.currentTimeMillis();
                if(currentTime-lastRun>delay) {
                    this.runAll();
                    this.renderAll();
                    lastRun = currentTime;
                }
            }
    }

    private void renderAll() {
            this.repaint();
    }

    private void runAll() {

        for(int i=0;i< GameObject.gameObjects.size();i++){
            GameObject gameObject = GameObject.gameObjects.get(i);
           if(gameObject.isActive){
               gameObject.run();
           }
        }
    }
}
