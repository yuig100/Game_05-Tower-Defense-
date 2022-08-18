package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public double vida = 14;
	private int frames = 0, maxframes = 10, index = 0, maxindex = 1;
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		
			path = AStar.findPath(Game.world, new Vector2i(this.getX() / 16,this.getY() / 16),new Vector2i(World.xFinal,World.yFinal));
			followPath(path);
			
			if(this.getX() >= World.xFinal * 16) {
				
				Game.life--;
				destroySelf();
			}
			
			if(vida <= 0) {
				
				destroySelf();
				Game.gold +=10;
				
			}
			
	}	

	public boolean isCollidingWithPlayer() {

		Rectangle enemyCurrent = new Rectangle(this.getX(), this.getY(), width, height);

		Rectangle player = new Rectangle(World.xFinal, World.yFinal, World.TILE_SIZE, World.TILE_SIZE);

		return enemyCurrent.intersects(player);
	}
	
	public void destroySelf() {

		//Game.enemies.remove(this);
		Game.entities.remove(this);
		return;

	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect((int)x, (int)(y+1), 16, 4);
		g.setColor(Color.red);
		g.fillRect((int)x+1, (int)(y+2),(int)((vida/14) * 14), 2);

		
		frames++;
		
		if (frames == maxframes) {

			frames = 0;
			index++;

			if (index > maxindex) {

				index = 0;
			}

		}
		
		if(right)
		sprite = Entity.ENEMY1_RIGHT[index];
		else if(left)
		sprite = Entity.ENEMY1_LEFT[index];
		
		super.render(g);
	}

}
