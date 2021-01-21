package com.pac;

import static javax.swing.SwingConstants.*;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game {
	
	
			public static final String SPAWN_PATH = "./res/levels/spawn.png";
	
			public static final int DELAY  = 70;
			public static final int OBSTACLE = 1;
			public static final int FRUIT = 2;
			public static final int RATE_ANIM = 2;
			
			public static final int OBSTACLE_MAPPED_COLOR = 0xff0000ff;
			public static final int FRUIT_MAPPED_COLOR = 0xffffffff;
			 
			public static final int WIDTH  = 40;
			public static final int HEIGHT = 40;
			public static int TIMER;
			
			
			public static class Pacman{
				 int x,y;
				 int speed;
				 int score;
				 int dir;
			}
			
			
			/***
			 * MAP UTILS
			 * TEST UTILS 
			 * @return
			 */
			
			public static int[] customizedMap() { 
				// 1 to draw Obstacle
				// 2 to draw Fruit
				
				int map[] = new int[] {
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,	
				};
				return map;
			} 
	
			
			public static int[] setDefaultMap() { 
				
				int xPortal = 20 , yPortal = 20; 
				
				
				int map[] = new int[WIDTH * HEIGHT];
				for(int y=0;y<HEIGHT;y++) { 
					 for(int x=0;x<WIDTH;x++) { 
						 if(y == 0 || y == HEIGHT - 1 || x == 0 || x == WIDTH - 1 || x == 10 || x == 30 || y == 15) { 
							 map[x + y * WIDTH] = 1;
						 }
						 /*
						  * Portals
						  */
						 else if(x == xPortal || y == yPortal) { 
							  map[xPortal + y * WIDTH] = 0;
							  map[(WIDTH - 1) + y * WIDTH] = 0;
							  
							  map[x + yPortal * WIDTH] = 0;
							  map[x + (HEIGHT - 1) * WIDTH] = 0;
						 }
						 
						 else {
							 map[x + y * WIDTH] = FRUIT;
						 }
						 
						 
					 }
				}
				return map;
			} 
			
			/*
			 * DYNAMCAlly Create LEVELS
			 * 
			 * 
			 */
			public static int[] loadLevel(String path) { 
			int w,h;
			int pixels[] = null;
			
				try {
					BufferedImage image = ImageIO.read(new FileInputStream(path));
					w = image.getWidth();
					h = image.getHeight();
					pixels = new int[w  * h];
					image.getRGB(0, 0, w, h, pixels, 0, w);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return pixels;
			}

			public static void drawWorldINTMAPPING(int map[],int w,int h,InterfacePacman ifp) { 
				  for(int y=0;y<h;y++) {
					  for(int x=0;x<w;x++) { 
						  switch(map[x + y * w]) {
						  
						   case OBSTACLE:
							   ifp.dessinerObstacle(x, y);
							   break;
							   
						   case FRUIT:
							   ifp.dessinerFruit(x, y);
							   break;
						   }
					  }
				  }
			}
			

			public static void drawWorldColorMapping(int map[],int w,int h,InterfacePacman ifp) { 
				  for(int y=0;y<h;y++) {
					  for(int x=0;x<w;x++) { 
						  switch(map[x + y * w]) {
						  
						   case OBSTACLE_MAPPED_COLOR:
							   ifp.dessinerObstacle(x, y);
							   break;
							   
						   case FRUIT_MAPPED_COLOR:
							   ifp.dessinerFruit(x, y);
							   break;
						   }
					  }
				  }
			}
			
			
			/**
			 * Pacman UTILS
			 * @param x
			 * @param y
			 * @param speed
			 * @param dir
			 * @return
			 */
			
			public static Pacman createPacman(int x,int y,int speed,int dir) { 
					Pacman p = new Pacman();
					p.x = x;
					p.y = y;
					p.dir = dir;
					return p;
			}
			
			public static void movePacInDirection(Pacman p) { 
				if(p.dir == NORTH) { 
					p.y-=1;
				}
				else 
					if(p.dir == EAST) {
						p.x+=1;
					}
					else 
						if(p.dir == SOUTH) {
						  p.y+=1;
						}
						else 
							if(p.dir == WEST) { 
								p.x-=1;
							}
			}
			
			public static boolean collisionInt(Pacman p,int w,int h,int grid[]) { 
				int pos;
				if(p.dir == NORTH) {
			    pos	= p.y - 1;
			    
				if(grid[p.x + pos * w] == OBSTACLE) {
					return true;
					}
				}
				else if(p.dir == EAST) {
				    pos	= p.x + 1;
					if(grid[pos + p.y * w] == OBSTACLE) {
						  return true;
					}
				}
				else if(p.dir == SOUTH) {
					    pos	= p.y + 1;
						if(grid[p.x + pos * w] == OBSTACLE) {
							  return true;
						}
					}
				else if(p.dir == WEST) {
						    pos	= p.x - 1;
							if(grid[pos + p.y * w] == OBSTACLE) {
								  return true;
							}
						}
				return false;
			}

			public static boolean collisionColoredMapping(Pacman p,int w,int h,int grid[]) { 
				int pos;
				if(p.dir == NORTH) {
			    pos	= p.y - 1;
			    
				if(grid[p.x + pos * w] == OBSTACLE_MAPPED_COLOR) {
					return true;
					}
				}
				else if(p.dir == EAST) {
				    pos	= p.x + 1;
					if(grid[pos + p.y * w] == OBSTACLE_MAPPED_COLOR) {
						  return true;
					}
				}
				else if(p.dir == SOUTH) {
					    pos	= p.y + 1;
						if(grid[p.x + pos * w] == OBSTACLE_MAPPED_COLOR) {
							  return true;
						}
					}
				else if(p.dir == WEST) {
						    pos	= p.x - 1;
							if(grid[pos + p.y * w] == OBSTACLE_MAPPED_COLOR) {
								  return true;
							}
						}
				return false;
			}


			
			public static void changeDirection(Pacman p,InterfacePacman ifp) { 
				 if(ifp.toucheAppuyee() == NORTH) { 
					   p.dir = NORTH;
				 }
				 else if(ifp.toucheAppuyee() == EAST) { 
					   p.dir = EAST;
				 }
				 else if(ifp.toucheAppuyee() == SOUTH) {
					   p.dir = SOUTH;
				 }
				 else if(ifp.toucheAppuyee() == WEST) { 
					   p.dir = WEST;
				 }
			}
			
			
			public static void showPac(Pacman p , InterfacePacman ifp ,int grid[]) { 
				if(TIMER % RATE_ANIM == 0 && !collisionColoredMapping(p,WIDTH,HEIGHT,grid)) {
					ifp.dessinerPacmanFerme(p.x, p.y);
				 }
				else {
					ifp.dessinerPacmanOuvert(p.x, p.y, p.dir);
				}
			}
			
			public static void teleportate(Pacman p ,int grid[], int width, int height) {
				if(p.x <= 0) {
					p.x = width - 1;
					grid[0 + p.y * WIDTH] = 0;
					p.dir = WEST;
				}
				else if(p.x >= width - 1) {
					p.x = 0;
					grid[(width - 1) + p.y * WIDTH] = 0;
					p.dir = EAST;
				}
				else 
					if(p.y <= 0) {
						p.y = height - 1;
						grid[p.x + 0 * WIDTH] = 0;
						p.dir  = NORTH;
					}
					else if(p.y >= height - 1) {
						 p.y = 0;
						 grid[p.x + (HEIGHT - 1) * WIDTH] = 0;
						 p.dir = SOUTH;
					}
			}
			
			
			public static void eatFruit(Pacman p, int grid[]) { 
				   if(grid[p.x + p.y * WIDTH] == FRUIT_MAPPED_COLOR) { 
					   		p.score+=1;
					   		grid[p.x + p.y * WIDTH] = 0;
				   }
			}
			
		/**
		 * 
		 * GAME LOOP
		 */
		public static void jeu() { 
		
			 boolean over = false;
			 int map[] = new int[WIDTH * HEIGHT];
			 
			 Pacman pac = createPacman(WIDTH / 2 , HEIGHT / 2 ,1, EAST);
			 InterfacePacman ifp = new InterfacePacman(WIDTH,HEIGHT);
			
			 map = loadLevel(SPAWN_PATH);
			 
			 while(!over) {
				 if(TIMER < 10000) TIMER++; else TIMER = 0;
				 ifp.clearScreen(WIDTH,HEIGHT);
				 drawWorldColorMapping(map,WIDTH,HEIGHT,ifp);
				 showPac(pac,ifp,map);
				 changeDirection(pac,ifp);
				 if(!collisionColoredMapping(pac,WIDTH,HEIGHT,map)) {
					 movePacInDirection(pac);
				 }
				 teleportate(pac,map,WIDTH,HEIGHT);
				 eatFruit(pac,map);
				 try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
		}
			/*
			 * ENTRY POINT 
			 * 
			 */
			public static void main(String[] args) { 
				 jeu();
			}
}
