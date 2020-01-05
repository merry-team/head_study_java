package com.sharingcard.Paint;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MoonJFrame extends JFrame {
	private MoonCanvas canvas;
	
	File f;
	URI uri;
	URL url;
	public void Music(){
		try{
//			this.f=new File("Faded.wav");
//			uri=f.toURI();
//			url=uri.toURL();//解析地址
//			AudioClip aau;
//			aau=Applet.newAudioClip(url);
//			aau.loop();
			}catch(Exception e){e.printStackTrace();}
	}
	public MoonJFrame(){
		super("MOON");
		Dimension dim=getToolkit().getScreenSize();
		this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas=new MoonCanvas();
		canvas.setBackground(Color.black);
		this.getContentPane().add(this.canvas,"Center");
		this.setVisible(true);
		this.Music();
	}
	public static void main(String[] args) {
		new MoonJFrame();
	}
}
class MoonCanvas extends Canvas implements ActionListener{
	private Moon moon;
	private Timer timer;
	private static class Moon{
		int x;
		public boolean move;
		Moon(int x){
			this.x=x;
			boolean move=false;
		}
	}
		public MoonCanvas(){
			this.moon=new Moon(400);
			timer=new Timer(500,this);
			timer.start();
		}
	public void paint(Graphics g){
		int x=this.getWidth();
		int y=this.getHeight();
		g.setColor(Color.white);
		for(int i=0;i<100;i++){
		g.fillOval(new Random().nextInt(1000),new Random().nextInt(1000),5,5);
		}
		g.fillOval(300,80,100,100);
		g.setColor(Color.black);
		g.fillOval(moon.x,80,100,100);
		moon.x=moon.move?moon.x+10:moon.x-10;
		if(moon.x>=400||moon.x<=200){
			moon.move=!moon.move;
		}
	}
	public void actionPerformed(ActionEvent ev){
		repaint();
	}
}
