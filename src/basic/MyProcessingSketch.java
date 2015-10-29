package basic;

import processing.core.PApplet;


//modified from http://processingjs.nihongoresources.com/graphs/ (from Javascript to Java )
public class MyProcessingSketch extends PApplet
{

	  public void setup() {
	    background(0);
	  }
	  
	  @Override
	public void settings()
	{
		super.settings();
		   size(500,500);
			 
	}

	  public void draw() {
	    stroke(255);
	    if (mousePressed) {
	      line(mouseX,mouseY,pmouseX,pmouseY);
	    }
	  }
	  
	  public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "basic.MyProcessingSketch" });
		  }


}
