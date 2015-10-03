package asteroidBelt;

import java.awt.Graphics;
import java.awt.*;

public class Asteroid extends Polygon{
	
	private boolean remove;
	
	public Asteroid(Point[] shape, Point position, double rotation)
	{
		super(shape, position, rotation);
		remove = false;
	}

	public void paint(Graphics brush)
	{
		Point[] newAsteroid = super.getPoints();
		if(remove == false){
			brush.setColor(Color.white);
		}else{
			brush.setColor(Color.black);
		}

		for(int i = 0; i < 5; i++){
			brush.drawLine((int)newAsteroid[i].x, (int)newAsteroid[i].y, (int)newAsteroid[i + 1].x,(int)newAsteroid[i + 1].y);
		}
		brush.drawLine((int)newAsteroid[5].x, (int)newAsteroid[5].y, (int)newAsteroid[0].x,(int) newAsteroid[0].y);
	}
	
	public void move()
	{
		this.position.x += (Math.cos(Math.toRadians(20)) * 2);
		this.position.y += (Math.sin(Math.toRadians(20)) * 2);

		this.position.x += 1;
		this.position.y += 1;

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
	
	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
}
