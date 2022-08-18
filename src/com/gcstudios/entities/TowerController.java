package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class TowerController extends Entity {

	public static boolean isPressed = false;
	public int xTarget, yTarget;
	public static int dir=1;

	public TowerController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

	}

	public void tick() {
		
		if(isPressed) {
			
			isPressed = false;
			boolean liberado = true;
			int xx = (xTarget / 16) * 16;
			int yy = (yTarget / 16) * 16;

			Player player = new Player(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(32, 0, 16, 16));

			for (int i = 0; i < Game.entities.size(); i++) {
				
				Entity e = Game.entities.get(i);
				if (e instanceof Player) {
					if (Entity.isColidding(e, player)) {
						liberado = false;

					}

				}

			}
			
			if(World.isFree(xx, yy)) {
				
				liberado = false;
				
			}
			
			if(liberado) {
				
				Game.entities.add(player);
				Game.gold-=100;
			}
			
		}
		
		if(Game.life <= 0) {
			
			System.exit(1);
			
		}
		
	}
	public void render(Graphics g) {
		
		if(dir == 1) {
			//cima
			sprite = tower1[5];
			
		}else if(dir == 2) {
			//baixo
			sprite = tower1[2];
			
		}else if(dir == 3) {
			//direita
			sprite = tower1[0];
			
		}else if(dir == 4) {
			//esquerda
			
			sprite = tower1[1];
		}
		
		super.render(g);
	}

}
