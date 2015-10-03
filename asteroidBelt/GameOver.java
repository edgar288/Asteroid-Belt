package asteroidBelt;

import java.awt.*;
import java.awt.event.*;

public class GameOver {

	public void paint(Graphics brush, int width, int height){
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		
		brush.setColor(Color.GREEN);
		brush.setFont(new Font("SANS_SERIF", Font.PLAIN, 100));
		brush.drawString("Game Over", 140, 250);
	}
	
}
