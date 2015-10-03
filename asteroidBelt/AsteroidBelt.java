package asteroidBelt;

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
*/
import java.awt.*; 
import java.awt.event.*;

import javax.swing.Timer;

class AsteroidBelt extends Game {

	Point[]        shipPoints, points, asteroidPoints;
	Asteroid[]     allAsteroids;
	Ship           newShip;
	static int     lives, score;
	int            waitTimer = 0;

	public AsteroidBelt() {
		super("Asteroid Belt!",800,600);
		this.setFocusable(true);
		this.requestFocus();

		shipPoints =     new Point[]   {new Point(408, 304),new Point(390, 305),new Point(400, 305),new Point(390, 310),new Point(390, 300),
				                        new Point(388, 310),new Point(392, 310),new Point(388, 300),new Point(392, 300),new Point(400, 297),
				                        new Point(405, 302),new Point(408, 304)};
		
		asteroidPoints = new Point[]   {new Point(0, 40),new Point(0, 15),new Point(20, 0),new Point(40, 15),new Point(40, 40), new Point(20, 50)};
		
		allAsteroids =   new Asteroid[]{(new Asteroid(asteroidPoints, new Point(160,50),    0)), (new Asteroid(asteroidPoints, new Point(454,100),  2)), (new Asteroid(asteroidPoints, new Point(3,128),    20)), 
				                        (new Asteroid(asteroidPoints, new Point(221,400),  40)), (new Asteroid(asteroidPoints, new Point(75, 495), 90)), (new Asteroid(asteroidPoints, new Point(765,173), 130)),
				                        (new Asteroid(asteroidPoints, new Point(22,252),  160)), (new Asteroid(asteroidPoints, new Point(35,395), 200)), (new Asteroid(asteroidPoints, new Point(789,647), 230)),
				                        (new Asteroid(asteroidPoints, new Point(519,438), 255))};
		
		double rotate = 0;
		lives = 3;
		newShip = new Ship(shipPoints, new Point(400,300), rotate);
		
		this.addKeyListener(newShip);
	}

	public void paint(Graphics brush) {		
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);

		newShip.paint(brush);
		if(allAsteroids[0].isRemove() == false){
			newShip.move();
		}
		
		for(int i = 0; i < allAsteroids.length; i++)
		{
			allAsteroids[i].paint(brush);
			allAsteroids[i].move();
		}
		
		crashCheck(new Point(400,300), brush);
		if(allAsteroids[0].isRemove() == true){
			waitTimer++;
			if (waitTimer >= 260){
				for(int i = 0; i < allAsteroids.length; i++)
				{
					allAsteroids[i].setRemove(false);
				}
				waitTimer = 0;
			}
		}
		
		brush.setColor(Color.white);
		brush.drawString("Lives: " + lives, 20, 20);
		brush.drawString("Score: " + score, 20, 40);
		
		
		if((lives > 0) && (allAsteroids[0].isRemove() == false)){
			score += 5;
		}
		if(lives <= 0){
			end(brush);
		}
	}
	
	public void crashCheck(Point center, Graphics brush){
		for(int j = 0; j < allAsteroids.length; j++)
		{
			if(newShip.collisionDetection(allAsteroids[j]))
			{
				if(allAsteroids[0].isRemove() == false){
					lives -= 1;
					for(int i = 0; i < allAsteroids.length; i++)
					{
						allAsteroids[i].setRemove(true);
					}
				} 
				newShip.setPosition(center);
			}
		}
	}
	
	public static void end(Graphics brush){
		GameOver end = new GameOver();
		end.paint(brush, width, height);
		brush.setColor(Color.white);
		brush.setFont(new Font("SERIF", Font.PLAIN, 50));
		brush.drawString("Final Score: " + score, 235, 350);
	}
	
	public static void main (String[] args) {
		AsteroidBelt a = new AsteroidBelt();
		a.repaint();
	}
}