package com.sharingcard.test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class drawlineforspline extends JFrame{
    
    private static final long serialVersionUID = 1L;
    static List <mypoint>plist;
    
    public static class mypoint{
    	double x;
    	double y;
        int type;
        public mypoint(double x,double y,int type){
            this.x=x;
            this.y=y;
            this.type=type;
        }
    }
    public drawlineforspline(){
        init();
    }
    public drawlineforspline(ArrayList plist){
        init();
        this.plist=plist;         
    }
    
    private void init(){
     
        this.setTitle("drawline");
        this.setBounds(200, 200, 500, 400);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true); 
        
        plist =new ArrayList();
        plist.add(new mypoint(8.959999084472656,8.860000610351562,0)); 
        plist.add(new mypoint(20.940000534057617,20.709999084472656,1));        
        plist.add(new mypoint(8.959999084472656,15.860000610351562,0));
        plist.add(new mypoint(20.959999084472656,27.610000610351562,1));        
        plist.add(new mypoint(8.170000076293945,55.979999542236328,0));
        plist.add(new mypoint(20.620000839233398,33.139999389648438,1));        
        plist.add(new mypoint(20.600000381469727,12.159999847412109,0));
        plist.add(new mypoint(50.600000381469727,12.159999847412109,1));        
        plist.add(new mypoint(20.600000381469727,22.159999847412109,0));
        plist.add(new mypoint(50.600000381469727,22.159999847412109,1));        
        plist.add(new mypoint(20.600000381469727,32.159999847412109,0));
        plist.add(new mypoint(50.600000381469727,32.159999847412109,1));        
        plist.add(new mypoint(40.600000381469727,8.159999847412109,0));
        plist.add(new mypoint(20.600000381469727,55.159999847412109,1));        
        plist.add(new mypoint(22.600000381469727,40.159999847412109,0));
        plist.add(new mypoint(50.600000381469727,40.159999847412109,1)); 
        plist.add(new mypoint(45.600000381469727,35.159999847412109,0));
        plist.add(new mypoint(45.600000381469727,55.159999847412109,0));
        plist.add(new mypoint(40.600000381469727,50.159999847412109,1));        
        plist.add(new mypoint(30.600000381469727,44.159999847412109,0));
        plist.add(new mypoint(35.600000381469727,48.159999847412109,1));
    }
 
    
    public class Mypanel extends JPanel{     
        public void paint(Graphics g){
            mypoint fromP=new mypoint(50,80,0);
            mypoint toP=new mypoint(370,240,0);
            for(int i=0;i<plist.size()-1;i++){
            	if(plist.get(i).type==0)
            		g.drawLine((int)(plist.get(i).x*6), (int)(plist.get(i).y*6), (int)(plist.get(i+1).x*6), (int)(plist.get(i+1).y*6));
            }
        }
    }
    
    public static void main(String[] args) {
        drawlineforspline d=new drawlineforspline();
        Mypanel myp=d.new Mypanel();
        d.add(myp);
    }

}