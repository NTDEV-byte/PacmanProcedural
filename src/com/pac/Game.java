package com.pac;

import static javax.swing.SwingConstants.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Game {
	
		/*
		 * Game X 
		 * Pacman fruit X 
		 * collision detection X
		 * LevelLoader X
		 * ghosts X 
		 * collision Pacman and ghosts
		 * ghost path finder A*
		 * Animation
		 * innerMenu
		 * EntryMenu
		 * Sound
		 */
	 
			public static int WIDTH  = 40;
			public static int HEIGHT = 40;
			public static final String SPAWN_PATH = "./res/levels/spawn.png";
	
			public static final int DELAY = 60;
			public static final int OBSTACLE = 1;
			public static final int FRUIT = 2;
			public static final int RATE_ANIM = 2;
			public static final int RATE_GHOST_DIR = 5;

			
			public static final int CYAN_GHOST = 0xff14ffff;
			public static final int RED_GHOST = 0xfff60303;
			public static final int YELLOW_GHOST = 0xffd7ff3c;
			public static final int PINK_GHOST = 0xfff400ff;
		
			
			public static final int OBSTACLE_MAPPED_COLOR = 0xff0000ff;
			public static final int FRUIT_MAPPED_COLOR = 0xffffffff;

			public static int TIMER;
			
			
			public static class Pacman{
				 int x,y;
				 int speed;
				 int score;
				 int dir;
			}
			
			public static class Fantome{
				int x,y;
				int speed;
				int dir;
				Color c;
				boolean mangeable;
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
					w = WIDTH = image.getWidth();
					h = HEIGHT = image.getHeight();
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
					p.speed = speed;
					p.dir = dir;
					return p;
			}
			
			public static void movePacInDirection(Pacman p) { 
				if(p.dir == NORTH) { 
					p.y-=p.speed;
				}
				else 
					if(p.dir == EAST) {
						p.x+=p.speed;
					}
					else 
						if(p.dir == SOUTH) {
						  p.y+=p.speed;
						}
						else 
							if(p.dir == WEST) { 
								p.x-=p.speed;
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
			
			/*
			 * Handles Logic of the pacman and it display it
			 */
			
			public static void pacmanLogicAndVisual(Pacman pac , InterfacePacman ifp , int map[]) {
				 showPac(pac,ifp,map);
				 changeDirection(pac,ifp);
				 if(!collisionColoredMapping(pac,WIDTH,HEIGHT,map)) {
					 movePacInDirection(pac);
				 }
				 teleportate(pac,map,WIDTH,HEIGHT);
				 eatFruit(pac,map);
			}
			
			/**
			 * Fantome
			 * 
			 */
			
			public static Fantome createFantome(int x,int y,int speed,int dir,Color c){
					Fantome fantome = new Fantome();
					fantome.x = x;
					fantome.y = y;
					fantome.speed = speed;
					fantome.dir = dir;
					fantome.c = c;
					return fantome;
			}
			
			public static Fantome[] createFantomes(int nb,int x,int y,int xs,int ys,int grid[]) {
				int directions[] = {NORTH,EAST,WEST,SOUTH};
				Color colors[] = {Color.red,Color.pink,Color.yellow,Color.cyan};
				Random random = new Random();
				Fantome fantomes[] = new Fantome[nb];
				Point locations[] = getMappedLocationInMap(grid);
				
				for(int i=0;i<fantomes.length;i++) {
					  fantomes[i] = createFantome(locations[i].x , locations[i].y, 1 , directions[random.nextInt(directions.length)] ,colors[i]);
				}
				
				return fantomes;
			}
			
			public static Point[] getMappedLocationInMap(int grid[]) {
				
				Point locations[] = new Point[4];
				int total = 0;
				int pixel;
				
				 	for(int y=0;y<HEIGHT;y++) {
				 		 for(int x=0;x<WIDTH;x++) { 
				 			 
				 			 pixel = grid[x + y * WIDTH];
				 			 
				 			 if(pixel ==  RED_GHOST) { 
				 				 locations[total++] = new Point(x,y);
				 			 }
				 			 else if(pixel == PINK_GHOST) {
				 				 locations[total++] = new Point(x,y);
				 			 }
				 			 else if(pixel == YELLOW_GHOST) { 
				 				 locations[total++] = new Point(x,y);
				 			 }
				 			 else if(pixel == CYAN_GHOST) {
				 				 locations[total++] = new Point(x,y);
				 			 }
				 		 }
				 	}
				 	
				 	return locations;
			}
			
			
			
			public static void moveInDir(Fantome fantome) {
				  if(fantome.dir == NORTH) {
					    fantome.y-=fantome.speed;
				  }
				  else if(fantome.dir == EAST) { 
					  	fantome.x+=fantome.speed;
				  }
				  else if(fantome.dir == SOUTH) {
					   fantome.y+=fantome.speed;
				  }
				  else if(fantome.dir == WEST) { 
					  fantome.x-=fantome.speed;
				  }
				  
			}
			
			public static void pickDirection(Fantome f) { 
				if(TIMER % RATE_GHOST_DIR == 0) {
				     f.dir = (int)((Math.random() * 5) + 1);
					 
					  switch(f.dir) { 
					  case 1:
						  
						  f.dir = NORTH;
						  
						  break;
						
					  case 2:
						  
						  f.dir = EAST;
						  break;
					  
					  case 3:
						  
						  f.dir = SOUTH;
						  
						  break;
						  
					  case 4:
						  f.dir = WEST;
						  
						  break;
					  }
				}
				
			}
			
			
			public static boolean collisionFantomeWorld(Fantome f,int grid[]) { 
				int pos;
				if(f.dir == NORTH) {
			    pos	= f.y - 1;
			    
				if(grid[f.x + pos * WIDTH] == OBSTACLE_MAPPED_COLOR) {
					return true;
					}
				}
				else if(f.dir == EAST) {
				    pos	= f.x + 1;
					if(grid[pos + f.y * WIDTH] == OBSTACLE_MAPPED_COLOR) {
						  return true;
					}
				}
				else if(f.dir == SOUTH) {
					    pos	= f.y + 1;
						if(grid[f.x + pos * WIDTH] == OBSTACLE_MAPPED_COLOR) {
							  return true;
						}
					}
				else if(f.dir == WEST) {
						    pos	= f.x - 1;
							if(grid[pos + f.y * WIDTH] == OBSTACLE_MAPPED_COLOR) {
								  return true;
							}
						}
				return false;
			}
			
			
			public static void showFantome(Fantome f,InterfacePacman ifp) { 
					ifp.dessinerFantome(f.x, f.y,f.c, f.dir);
					
			} 

			
			public static void collisionFantomePacman() { 
				
			}
			
			
			public static void fantomeLogicAndVisual(Fantome f[],int grid[],InterfacePacman ifp) { 
				for(int i=0;i<f.length;i++) { 
					showFantome(f[i],ifp);
					pickDirection(f[i]);
					if(!collisionFantomeWorld(f[i],grid)) {
						moveInDir(f[i]);
					}
				}
			}
			
			
		/**
		 * 
		 * GAME LOOP
		 */
			
	
		
		public static void jeu() { 
		
			 boolean over = false;
			 int map[] = new int[WIDTH * HEIGHT];
			 map = loadLevel(SPAWN_PATH);
			 
			 Pacman pac = createPacman(WIDTH / 2 , HEIGHT / 2 ,1, EAST);
			 InterfacePacman ifp = new InterfacePacman(WIDTH,HEIGHT);

			 Fantome fantomes[] = createFantomes(4,5,5,2,2,map);
			 
		
			 
			 while(!over) {
				 if(TIMER < 10000) TIMER++; else TIMER = 0;
				 ifp.clearScreen(WIDTH,HEIGHT);
				 drawWorldColorMapping(map,WIDTH,HEIGHT,ifp);
				 pacmanLogicAndVisual(pac,ifp,map);
				 fantomeLogicAndVisual(fantomes,map,ifp);
				 
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
