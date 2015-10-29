package basic;

import processing.core.PApplet;

// modified (from JavaScript to Java ) from https://processing.org/examples/
public class Bounce extends PApplet
{
	int rad = 60;        // Width of the shape
	float xpos, ypos;    // Starting position of shape    

	float xspeed = 2.8f;  // Speed of the shape
	float yspeed = 2.2f;  // Speed of the shape

	int xdirection = 1;  // Left or Right
	int ydirection = 1;  // Top to Bottom


	public void setup() 
	{
	  noStroke();
	  frameRate(30);
	  ellipseMode(RADIUS);
	  // Set the starting position of the shape
	  xpos = width/2;
	  ypos = height/2;
	}
	
	  @Override
		public void settings()
		{
			super.settings();
			   size(500,500);
				 
		}

	public void draw() 
	{
	  background(102);
	  
	  // Update the position of the shape
	  xpos = xpos + ( xspeed * xdirection );
	  ypos = ypos + ( yspeed * ydirection );
	  
	  // Test to see if the shape exceeds the boundaries of the screen
	  // If it does, reverse its direction by multiplying by -1
	  if (xpos > width-rad || xpos < rad) {
	    xdirection *= -1;
	  }
	  if (ypos > height-rad || ypos < rad) {
	    ydirection *= -1;
	  }

	  // Draw the shape
	  ellipse(xpos, ypos, rad, rad);
	}
	
	
	 public static void main(String args[]) 
	 {
		    PApplet.main(new String[] { "--present", "basic.Bounce" });
	 }
}
