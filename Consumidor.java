package tankes;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;
public class Consumidor extends Thread{
    private Y rc;
    private DibujaTanke panel;
    Lock mutex;
    public Consumidor(DibujaTanke panel, Y rc){
        this.panel=panel;
        this.rc=rc;
        mutex = new ReentrantLock();
    }
    public void run(){
        while(true){
            if (rc.getY()<250){
                if(mutex.tryLock()){
                    mutex.lock();
                        panel.agua.getAgua().remove(panel.agua.getAgua().size()-1);
                        rc.setY(rc.getY()+5);
                        System.out.println("Consumidor");
                    mutex.unlock();
                }
                panel.repaint();
                try{    
                    sleep(1000);
                }catch(InterruptedException e){}
            }
        }
    }
}
