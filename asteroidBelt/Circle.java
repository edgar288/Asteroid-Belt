package asteroidBelt;

import java.awt.*;

public class Circle {

	private Point[] shape;   // An array of points.
	public Point position;   // The offset mentioned above.
	public double rotation; // Zero degrees is due east.
	
	public Circle(Point[] inShape, Point inPosition, double inRotation) {
		shape = inShape;
		position = inPosition;
		rotation = inRotation;

		// First, we find the shape's top-most left-most boundary, its origin.
		Point origin = shape[0].clone();
		for (Point p : shape) {
			if (p.x < origin.x) origin.x = p.x;
			if (p.y < origin.y) origin.y = p.y;
		}
	}
	
	public boolean contains(Point intersect)
	{
		if(this.contains(intersect)){
			return true;
		}
		return false;
	}

	public Point[] getPoints() {
		Point center = findCenter();
		Point[] points = new Point[shape.length];
		for (int i = 0; i < shape.length; i++) {
			//		    for (Point p : shape) {
			Point p = shape[i];
			double x = ((p.x-center.x) * Math.cos(Math.toRadians(rotation)))
					- ((p.y-center.y) * Math.sin(Math.toRadians(rotation)))
					+ center.x/2 + position.x;
			double y = ((p.x-center.x) * Math.sin(Math.toRadians(rotation)))
					+ ((p.y-center.y) * Math.cos(Math.toRadians(rotation)))
					+ center.y/2 + position.y;
			points[i] = new Point(x,y);
		}
		return points;
	}


	private Point findCenter() {
		Point sum = new Point(0,0);
		for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
			sum.x += (shape[i].x + shape[j].x)
					* (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
			sum.y += (shape[i].y + shape[j].y)
					* (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
		}
		double area = findArea();
		return new Point(Math.abs(sum.x/(6*area)),Math.abs(sum.y/(6*area)));
	}
	
	private double findArea() {
		double sum = 0;
		for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
			sum += shape[i].x*shape[j].y-shape[j].x*shape[i].y;
		}
		return Math.abs(sum/2);
	}
}
