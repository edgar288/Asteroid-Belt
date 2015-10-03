package asteroidBelt;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ship extends Polygon implements KeyListener
{	
	Point[] newShip;
	private boolean forward, right, left;
	
	public Ship(Point[] shipPoints, Point newPoint, double rotate)
	{
		super(shipPoints, newPoint, rotate);
		forward = right = left = false;
	}
	
	public void paint(Graphics brush)
	{
		newShip = super.getPoints();
		createCircle(brush, Color.white, (int)newShip[0].x, (int)newShip[0].y, 15);
		
		createLine(brush, (int)newShip[1].x, (int)newShip[1].y, (int)newShip[2].x, (int)newShip[2].y);
		createLine(brush, (int)newShip[3].x, (int)newShip[3].y, (int)newShip[4].x, (int)newShip[4].y);
		createLine(brush, (int)newShip[5].x, (int)newShip[5].y, (int)newShip[6].x, (int)newShip[6].y);
		createLine(brush, (int)newShip[7].x, (int)newShip[7].y, (int)newShip[8].x, (int)newShip[8].y);

		createCircle(brush, Color.blue, (int)newShip[11].x, (int)newShip[11].y, 5);
	}
	
	public void createLine(Graphics brush, int x1, int y1, int x2, int y2){
		brush.drawLine(x1, y1, x2, y2);
	}
	
	public void createCircle(Graphics brush, Color c, int x, int y, int size){
		brush.setColor(c);
		brush.fillOval((x - (size/2)), (y - (size/2)), size, size);
		brush.drawOval((x - (size/2)), (y - (size/2)), size, size);
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_UP:
			forward = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_UP:
			forward = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void move()
	{			
		if(forward)
		{
			this.position.x += Math.cos(Math.toRadians(rotation));
			this.position.y += Math.sin(Math.toRadians(rotation));
		}

		if(right)
		{
			rotation += 2;
		}
		
		if(left)
		{
			rotation -= 2;
		}
		
		if((this.position.x > 800))
		{
			this.position.x -= 800;
		}
		else if((this.position.x < 0))
		{
			this.position.x += 800;
		}
		if((this.position.y < 0))
		{
			this.position.y += 600;
		}
		else if((this.position.y > 600))
		{
			this.position.y -= 600;
		}
	}

}