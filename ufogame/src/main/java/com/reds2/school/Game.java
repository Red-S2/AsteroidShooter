package com.reds2.school;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Game implements State{
    private BufferedImage[] coin,asteriod,ship;
    private BufferedImage bg;
    private int anim=0;
    private double SceneY=0;
    double x=250,y=800;
    private ArrayList<Integer> keys = new ArrayList<Integer>();
    private	double xV = 0,yV=0,rot=0;

    Game(){
        ship = new BufferedImage[4];
        try {
            for(int i=0;i<4;i++){
                System.out.println("Ship"+i+"_"+ Main.INSTANCE.skin+".png");
                ship[i]=ImageIO.read(Game.class.getClassLoader().getResourceAsStream("Ship"+i+"_"+ Main.INSTANCE.skin+".png"));
                System.out.println(i);
            }
            bg  = ImageIO.read(Game.class.getClassLoader().getResourceAsStream("GameBG.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage draw() {
        BufferedImage result = new BufferedImage(540, 1080, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      
        g.drawImage(bg,0, ((int)SceneY%2160)-2160, null);
        g.drawImage(bg,0, (int)SceneY%2160, null);
        SceneY+=0.1;

        AffineTransform t = g.getTransform();
        t.rotate(rot,x+40,y+60);
        g.setTransform(t);
        g.drawImage(ship[anim],(int)x,(int)y,80,120,null);
        g.setTransform(new AffineTransform());

        if (new Random().nextInt(50)==1){anim++;anim =anim%4;}
        if (keys.contains(38)){
            xV += 2*Math.cos(rot);
            yV += 2*Math.sin(rot);
        }
        if (keys.contains(40)){
            xV -= 2*Math.cos(rot);
            yV -= 2*Math.sin(rot);
        }
        if (keys.contains(37)){
            rot -= 0.2;
        }
        if (keys.contains(39)){
            rot += 0.1;
        }
        x+=xV;
        y+=yV;
        xV=Math.sqrt(xV);
        yV=Math.sqrt(yV);
        return result;
    }

    @Override
    public void press(KeyEvent e) {
        if (!keys.contains(e.getKeyCode())){
            keys.add(e.getKeyCode());
        }   
    }

    @Override
    public void release(KeyEvent e) {
        keys.remove((Object)e.getKeyCode());
    }

    @Override
    public void click(MouseEvent e, Dimension d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drag(MouseEvent e, Dimension d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void m_release(MouseEvent e, Dimension d) {
        // TODO Auto-generated method stub
        
    }
    
}
