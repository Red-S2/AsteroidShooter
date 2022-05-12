package com.reds2.school;

import java.util.Random;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

class Asteriod{
    private Random rng = new Random();
    double x,y,xV,yV,rot,rV;
    int s=new Random().nextInt(30)+30+(int) Main.INSTANCE.game.time, hp=s/20;
    private Dimension aim;
    Ellipse2D col;
    Asteriod(){
        x = rng.nextDouble(740)-200;
        y = -75;
        if (rng.nextInt(5)==1){
            aim = new Dimension((int) Main.INSTANCE.game.x,(int) Main.INSTANCE.game.y);
            s++;
        } else {
            aim = new Dimension(rng.nextInt(400)+50,rng.nextInt(60)+500);
        }
        rot = Math.atan((y-aim.height)/(x-aim.width));  
		if(aim.width<x){rot+=Math.PI;}
        xV = (rng.nextDouble(1.5)+Math.sqrt(Main.INSTANCE.game.time)+1)*Math.cos(rot);
        yV = (rng.nextDouble(1.5)+Math.sqrt(Main.INSTANCE.game.time)+1)*Math.sin(rot); 
        rV = rng.nextDouble(.5)/(double)Math.sqrt(s);
        col = new Ellipse2D.Double(x,y,(double)s,(double)s);
    }
}