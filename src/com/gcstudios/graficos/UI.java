package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		
		for(int i = 0 ;i< Game.life;i++) {
			
			g.drawImage(Game.spritesheet.getSprite(80, 0, 16, 16),20+(i * 40), 20, 35, 35,null);
			
		}
		
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(Game.gold+"", 630, 42);
		g.drawImage(Game.spritesheet.getSprite(96, 0,16, 16),580, 10, 45, 45,null);
	}
	
}
