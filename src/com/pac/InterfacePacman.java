package com.pac;
import static javax.swing.SwingConstants.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InterfacePacman extends JFrame{
	
	
	private int keyTapped;
		
	private interface GraphicEntity{
		public void paint(Graphics2D g, Point p, int cell, int border);
	}
	
	private class Fantome implements GraphicEntity{
		Color c; int dir;
		public Fantome(Color c, int dir){
			this.c=c; this.dir=dir;
		}
		public void paint(Graphics2D g, Point p, int cell, int border){
			g.setColor(this.c);
			GeneralPath head = new GeneralPath();
			head.append(new Arc2D.Double(border+p.x*cell-cell/6,border+p.y*cell-cell/6,cell+cell/3,cell,0,180,Arc2D.PIE),false);
			head.closePath();
			g.fill(head);
			GeneralPath body = new GeneralPath();
			body.moveTo(border+(p.x+1)*cell+cell/6,border+p.y*cell+cell/3);
			body.lineTo(border+(p.x+1)*cell+cell/6,border+(p.y+1)*cell+cell/6);
			body.lineTo(border+(p.x+1)*cell,border+(p.y+1)*cell);
			body.lineTo(border+p.x*cell+13*cell/18,border+(p.y+1)*cell+cell/6);
			body.lineTo(border+p.x*cell+cell/2,border+(p.y+1)*cell);
			body.lineTo(border+p.x*cell+2*cell/9,border+(p.y+1)*cell+cell/6);
			body.lineTo(border+p.x*cell,border+(p.y+1)*cell);
			body.lineTo(border+p.x*cell-cell/6,border+(p.y+1)*cell+cell/6);
			body.lineTo(border+p.x*cell-cell/6,border+p.y*cell+cell/3);
			body.closePath();
			g.fill(body);
			g.setColor(Color.WHITE);
			g.fillOval(border+p.x*cell,border+p.y*cell+cell/6,cell/3,cell/3);
			g.fillOval(border+p.x*cell+2*cell/3,border+p.y*cell+cell/6,cell/3,cell/3);
			g.setColor(Color.BLACK);
			switch(this.dir){
				case NORTH : g.fillOval(border+p.x*cell+cell/6-cell/10,border+p.y*cell+cell/3-cell/10-cell/12,cell/5,cell/5);
					g.fillOval(border+p.x*cell+5*cell/6-cell/10,border+p.y*cell+cell/3-cell/10-cell/12,cell/5,cell/5); break;
				case SOUTH : g.fillOval(border+p.x*cell+cell/6-cell/10,border+p.y*cell+cell/3-cell/10+cell/12,cell/5,cell/5);
					g.fillOval(border+p.x*cell+5*cell/6-cell/10,border+p.y*cell+cell/3-cell/10+cell/12,cell/5,cell/5); break;
				case WEST : g.fillOval(border+p.x*cell+cell/6-cell/10-cell/12,border+p.y*cell+cell/3-cell/10,cell/5,cell/5);
					g.fillOval(border+p.x*cell+5*cell/6-cell/10-cell/12,border+p.y*cell+cell/3-cell/10,cell/5,cell/5); break;
				case EAST : g.fillOval(border+p.x*cell+cell/6-cell/10+cell/12,border+p.y*cell+cell/3-cell/10,cell/5,cell/5);
					g.fillOval(border+p.x*cell+5*cell/6-cell/10+cell/12,border+p.y*cell+cell/3-cell/10,cell/5,cell/5); break;
				default : g.fillOval(border+p.x*cell+cell/6-cell/10,border+p.y*cell+cell/3-cell/10,cell/5,cell/5);
					g.fillOval(border+p.x*cell+5*cell/6-cell/10,border+p.y*cell+cell/3-cell/10,cell/5,cell/5); break;
			}
		}
	}
	
	private class FantomeMangeable extends Fantome{
		public FantomeMangeable(){
			super(Color.BLUE, CENTER);
		}
		public void paint(Graphics2D g, Point p, int cell, int border){
			super.paint(g,p,cell,border);
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(1.0f));
			g.drawLine(border+p.x*cell,border+p.y*cell+5*cell/6,border+p.x*cell+cell/6,border+p.y*cell+2*cell/3);
			g.drawLine(border+p.x*cell+cell/6,border+p.y*cell+2*cell/3,border+p.x*cell+cell/3,border+p.y*cell+5*cell/6);
			g.drawLine(border+p.x*cell+cell/3,border+p.y*cell+5*cell/6,border+p.x*cell+cell/2,border+p.y*cell+2*cell/3);
			g.drawLine(border+p.x*cell+cell/2,border+p.y*cell+2*cell/3,border+p.x*cell+2*cell/3,border+p.y*cell+5*cell/6);
			g.drawLine(border+p.x*cell+2*cell/3,border+p.y*cell+5*cell/6,border+p.x*cell+5*cell/6,border+p.y*cell+2*cell/3);
			g.drawLine(border+p.x*cell+5*cell/6,border+p.y*cell+2*cell/3,border+(p.x+1)*cell,border+p.y*cell+5*cell/6);
			g.setStroke(new BasicStroke(2.0f));
		}
	}
	
	private class Fruit implements GraphicEntity{
		public void paint(Graphics2D g, Point p, int cell, int border){
			g.setColor(Color.WHITE); g.fillOval(border+p.x*cell+(cell*2)/5, border+p.y*cell+(cell*2)/5,cell/5,cell/5);
		}
	}
	
	private class Bonus implements GraphicEntity{
		public void paint(Graphics2D g, Point p, int cell, int border){
			g.setColor(Color.PINK); g.fillOval(border+p.x*cell+cell/5, border+p.y*cell+cell/5,3*cell/5,3*cell/5);
		}
	}
	
	private class Obstacle implements GraphicEntity{
		GraphicEntity[][] grid;
		int offset = 2;
		public Obstacle(GraphicEntity[][] grid){
			this.grid = grid;
		}
		public void paint(Graphics2D g, Point p, int cell, int border){
			g.setColor(Color.BLUE); 
			if(p.x!=0 && (grid[p.x-1][p.y] instanceof Obstacle)){
				if(p.y==0 || (p.y!=0 && (!(grid[p.x][p.y-1] instanceof Obstacle) || !(grid[p.x-1][p.y-1] instanceof Obstacle)))) g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+cell/offset, border+p.x*cell, border+p.y*cell+cell/offset);
				if(p.y==grid[p.x].length-1 || (p.y!=grid[p.x].length-1 && (!(grid[p.x][p.y+1] instanceof Obstacle) || !(grid[p.x-1][p.y+1] instanceof Obstacle)))) g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+(offset-1)*cell/offset, border+p.x*cell, border+p.y*cell+(offset-1)*cell/offset);
			}
			else g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+cell/offset, border+p.x*cell+cell/offset, border+p.y*cell+(offset-1)*cell/offset);
			if(p.y!=0 && (grid[p.x][p.y-1] instanceof Obstacle)){
				if(p.x==0 || (p.x!=0 && (!(grid[p.x-1][p.y-1] instanceof Obstacle) || !(grid[p.x-1][p.y] instanceof Obstacle)))) g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+cell/offset, border+p.x*cell+cell/offset, border+p.y*cell);
				if(p.x==grid.length-1 || (p.x!=grid.length-1 && (!(grid[p.x+1][p.y-1] instanceof Obstacle) || !(grid[p.x+1][p.y] instanceof Obstacle)))) g.drawLine(border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+cell/offset, border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell);
			}
			else g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+cell/offset, border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+cell/offset);
			if(p.y<grid[p.x].length-1 && (grid[p.x][p.y+1] instanceof Obstacle)){
				if(p.x==0 || (p.x!=0 && (!(grid[p.x-1][p.y] instanceof Obstacle) || !(grid[p.x-1][p.y+1] instanceof Obstacle)))) g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+(offset-1)*cell/offset, border+p.x*cell+cell/offset, border+(p.y+1)*cell);
				if(p.x==grid.length-1 || (p.x!=grid.length-1 && (!(grid[p.x+1][p.y] instanceof Obstacle) || !(grid[p.x+1][p.y+1] instanceof Obstacle)))) g.drawLine(border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+(offset-1)*cell/offset, border+p.x*cell+(offset-1)*cell/offset, border+(p.y+1)*cell);
			}
			else g.drawLine(border+p.x*cell+cell/offset, border+p.y*cell+(offset-1)*cell/offset, border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+(offset-1)*cell/offset);
			if(p.x<grid.length-1 && (grid[p.x+1][p.y] instanceof Obstacle)){
				if(p.y==0 || (p.y!=0 && (!(grid[p.x][p.y-1] instanceof Obstacle) || !(grid[p.x+1][p.y-1] instanceof Obstacle)))) g.drawLine(border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+cell/offset, border+(p.x+1)*cell, border+p.y*cell+cell/offset);
				if(p.y==grid[p.x].length-1 || (p.y!=grid[p.x].length-1 && (!(grid[p.x][p.y+1] instanceof Obstacle) || !(grid[p.x+1][p.y+1] instanceof Obstacle)))) g.drawLine(border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+(offset-1)*cell/offset, border+(p.x+1)*cell, border+p.y*cell+(offset-1)*cell/offset);
			}
			else g.drawLine(border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+cell/offset, border+p.x*cell+(offset-1)*cell/offset, border+p.y*cell+(offset-1)*cell/offset);
		}
	}
	
	private class Pacman implements GraphicEntity{
		int dir; boolean ouvert;
		public Pacman(int dir, boolean ouvert){
			this.dir = dir; this.ouvert = ouvert;
		}
		public void paint(Graphics2D g, Point p, int cell, int border){
			g.setColor(Color.YELLOW); 
			if(!this.ouvert) g.fillOval(border+p.x*cell-cell/6, border+p.y*cell-cell/6,cell+cell/3,cell+cell/3);
			else{
				switch(this.dir){						
					case NORTH : g.fillArc(border+p.x*cell-cell/6, border+p.y*cell-cell/6,cell+cell/3,cell+cell/3,135,270); break;
					case SOUTH : g.fillArc(border+p.x*cell-cell/6, border+p.y*cell-cell/6,cell+cell/3,cell+cell/3,-45,270); break;
					case WEST : g.fillArc(border+p.x*cell-cell/6, border+p.y*cell-cell/6,cell+cell/3,cell+cell/3,225,270); break;
					case EAST : g.fillArc(border+p.x*cell-cell/6, border+p.y*cell-cell/6,cell+cell/3,cell+cell/3,45,270); break;
				}
			}
		}
	}
	
	private GraphicEntity[][] grid; 
	private Graphic p;
	private int border = 5,cell = 18;
	private JTextField jtf;
	private KeyHandler key;
	
	
	public InterfacePacman(int largeur, int hauteur){
		super("PACMAN");
		this.grid = new GraphicEntity[largeur][hauteur];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				this.grid[i][j] = null;
			}		
		}
		key = new KeyHandler();
		this.p = new Graphic();
		this.p.setPreferredSize(new Dimension(2*this.border+largeur*this.cell, 2*this.border+hauteur*this.cell));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(this.p,BorderLayout.CENTER);
		this.getContentPane().add(this.p);
		this.jtf = new JTextField(20);
		this.getContentPane().add(this.jtf,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	this.addKeyListener(key);
		this.requestFocus();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	/* Renvoie un entier correspondant à la touche appuyee. CENTER:pause, EAST:fleche droite, WEST:fleche gauche, SOUTH:fleche bas, NORTH:fleche haut
	Renvoie -1 si la touche appuyee n'est ni une fleche ni pause. */
	
	/* Efface la case d'abscisse x et d'ordonnee y */
	public void effaceCase(int x, int y){
		this.grid[x][y] = null;
		this.miseAJour();
	}
	
	/* Dessine un obstacle dans la case d'abscisse x et d'ordonnee y */
	public void dessinerObstacle(int x, int y){
		this.grid[x][y] = new Obstacle(this.grid);
		this.miseAJour();
	}
	
	/* Dessine un fruit dans la case d'abscisse x et d'ordonnee y */
	public void dessinerFruit(int x, int y){
		this.grid[x][y] = new Fruit();
		this.miseAJour();
	}
	
	/* Dessine un bonus dans la case d'abscisse x et d'ordonnee y */
	public void dessinerBonus(int x, int y){
		this.grid[x][y] = new Bonus();
		this.miseAJour();
	}
	
	/* Dessine un pacman ferme dans la case d'abscisse x et d'ordonnee y */
	public void dessinerPacmanFerme(int x, int y){
		this.grid[x][y] = new Pacman(CENTER,false);
		this.miseAJour();
	}
	
	/* Dessine un pacman ouvert dans la case d'abscisse x et d'ordonnee y et avec la direction dir.
	 EAST: droite, WEST: gauche, SOUTH: bas, NORTH: haut */
	public void dessinerPacmanOuvert(int x, int y, int dir){
		this.grid[x][y] = new Pacman(dir,true);
		this.miseAJour();
	}
	
	/* Dessine un fantome dans la case d'abscisse x et d'ordonnee y avec la couleur c et la direction dir.
	EAST: droite, WEST: gauche, SOUTH: bas, NORTH: haut */
	public void dessinerFantome(int x, int y, Color c, int dir){
		this.grid[x][y] = new Fantome(c,dir);
		this.miseAJour();
	}
	
	/* Dessine un fantome mangeable dans la case d'abscisse x et d'ordonnee y */
	public void dessinerFantomeMangeable(int x, int y){
		this.grid[x][y] = new FantomeMangeable();
		this.miseAJour();
	}
	
	public void miseAJour(){
		this.p.repaint();
	}
		
	private class Graphic extends JPanel{
		
		public Graphic() { 
			this.addKeyListener(key);
		}
		
		public void paint(Graphics gr){
			Graphics2D gr2D = (Graphics2D) gr;
			gr2D.setColor(Color.BLACK);
			gr2D.fillRect(0,0,this.getWidth(),this.getHeight());
			gr2D.setStroke(new BasicStroke(2.0f));
			int offset = 2;
			for(int i=0;i<grid.length;i++){
				for(int j=0;j<grid[i].length;j++){
					if(grid[i][j] != null) grid[i][j].paint(gr2D, new Point(i,j), cell, border);
				}
			}
			requestFocus();
		}
	}
	
	public void clearScreen(int w,int h)
	{ 
		for(int y=0;y<h;y++) {
			 for(int x=0;x<w;x++) {
				  grid[x][y] = null;
			 }
		}
	}
	
	public void afficheMessage(String m){
		JOptionPane.showMessageDialog(this,m);
	}
	
	public void afficheTexte(String t){
		this.jtf.setText(t);
	}
	
	
	public int toucheAppuyee() {
		return keyTapped;
	}
	
	private class KeyHandler implements KeyListener{
		

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
		   switch(e.getKeyCode()) {
		   case KeyEvent.VK_UP:
			   keyTapped = SwingConstants.NORTH;
			   break;
			   
		   case KeyEvent.VK_RIGHT:
			   keyTapped = SwingConstants.EAST;
			   break;
			   
			   
		   case KeyEvent.VK_DOWN:
			   keyTapped = SwingConstants.SOUTH;
			   break;
	   
		   case KeyEvent.VK_LEFT:
			   keyTapped = SwingConstants.WEST;
			   break;
		   }
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		 
	}

	
}