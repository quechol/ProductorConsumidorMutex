package tankes;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;

public class Productor extends Thread{
    private Y rc;
    private DibujaTanke panel; 
    private Lock mutex;
    public Productor(DibujaTanke panel, Y rc){
        this.panel=panel;
        this.rc=rc;
        mutex = new ReentrantLock();
    }
    public void run(){
        while(true){
            if(rc.getY()>50){
                if(mutex.tryLock()){
                mutex.lock();
                    panel.agua.getAgua().add(new Rectangle2D.Double(50,rc.getY(), 100, 5));
                    rc.setY(rc.getY()-5);
                    System.out.println("Productor");
                mutex.unlock();
            }
                panel.repaint();
                try{
                    sleep(500+(int)Math.random()*200);
                }catch(Exception e){}
            }
        }
    }
}
