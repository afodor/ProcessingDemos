package simpleForceTree;

import java.util.ArrayList;
/**
 * Simmple graph layout system
 * http://processingjs.nihongoresources.com/graphs
 * This code is in the public domain
 * 
 * modified from JavaScript to Java...
 */


//modified from http://processingjs.nihongoresources.com/graphs/ (from Javascript to Java )
// This is a generic node in a graph
public class Node
{
	private final ForceTree parent;
	 // visualisation-specific
	  int x=0;
	  int y=0;
	  int r1=10;
	  int r2=10;
	
	
  ArrayList<Node> inlinks = new ArrayList<Node>();
  ArrayList<Node> outlinks = new ArrayList<Node>();;
  String label;

  Node(String _label, int _x, int _y, ForceTree parent) {
	 // System.out.println("Constructing " + x + " " + y);
    label=_label; this.x=_x; this.y=_y; r1=5; r2=5; this.parent = parent; }

  void addIncomingLink(Node n) {
    if(!inlinks.contains(n)) {
      inlinks.add(n);}}

  ArrayList<Node> getIncomingLinks(){
    return inlinks; }

  int getIncomingLinksCount() {
    return inlinks.size(); }

  void addOutgoingLink(Node n) {
    if(!outlinks.contains(n)) {
      outlinks.add(n);}}

  ArrayList<Node> getOutgoingLinks(){
    return outlinks; }

  int getOutgoingLinksCount() {
    return outlinks.size(); }

  float getShortestLinkLength() {
    if(inlinks.size()==0 && outlinks.size()==0) { return -1; }
    float l = 100;
    for(Node inode: inlinks) {
      int dx = inode.x-x;
      int dy = inode.y-y;
      float il =(float) Math.sqrt(dx*dx + dy*dy);
      if(il<l) { l=il; }}
    for(Node onode: outlinks) {
      int dx = onode.x-x;
      int dy = onode.y-y;
      float ol = (float) Math.sqrt(dx*dx + dy*dy);
      if(ol<l) { l=ol; }}
    return l; }

  boolean equals(Node other) {
    if(this==other) return true;
    return label.equals(other.label); }

 

  void setPosition(int _x, int _y) {
    x=_x; y=_y; }

  void setRadii(int _r1, int _r2) {
    r1=_r1; r2=_r2; }

  public void draw() {
    parent.stroke(0);
    parent.fill(255);
    
    for(Node o: outlinks) {
      drawArrow(x,y,o.x,o.y);
      //System.out.println(x + " " + y + " "+ o.x + " " + o.y);
      }
    parent.ellipse(x,y,r1*2,r2*2);
    parent.fill(50,50,255);
    parent.text(label,x+r1*2,y+r2*2);
  }

  int[] arrowhead = {0,-4,0,4,7,0};
  void drawArrow(int x, int y, int ox, int oy)
  {
    int dx=ox-x;
    int dy=oy-y;
    float angle = getDirection(dx,dy);
    float vl = (float) (Math.sqrt(dx*dx+dy*dy) - Math.sqrt(r1*r1+r2*r2)*1.5);
    int[] end = rotateCoordinate(vl, 0, angle);
    parent.line(x,y,x+end[0],y+end[1]);
    drawArrowHead(x+end[0], y+end[1], angle);
  }
  void drawArrowHead(int ox, int oy, float angle) {
    int[] rc1 = rotateCoordinate(arrowhead[0], arrowhead[1], angle);
    int[] rc2 = rotateCoordinate(arrowhead[2], arrowhead[3], angle);
    int[] rc3 = rotateCoordinate(arrowhead[4], arrowhead[5], angle);
    int[] narrow = {ox+ rc1[0], oy+ rc1[1], ox+ rc2[0], oy+ rc2[1], ox+ rc3[0], oy+ rc3[1]};
    parent.stroke(0);
    parent.fill(255);
    parent.triangle(narrow[0], narrow[1], narrow[2], narrow[3], narrow[4], narrow[5]);
  }
  

//=============================================
//     Some universal helper functions
//=============================================

//universal helper function: get the angle (in radians) for a particular dx/dy
public static float getDirection(double dx, double dy) {
	double PI = Math.PI;
 // quadrant offsets
 double d1 = 0.0;
 double d2 = PI/2.0;
 double d3 = PI;
 double d4 = 3.0*PI/2.0;
 // compute angle basd on dx and dy values
 double angle = 0;
 float adx = (float)Math.abs((float)dx);
 float ady = (float)Math.abs((float)dy);
 // Vertical lines are one of two angles
 if(dx==0) { angle = (dy>=0? d2 : d4); }
 // Horizontal lines are also one of two angles
 else if(dy==0) { angle = (dx>=0? d1 : d3); }
 // The rest requires trigonometry (note: two use dx/dy and two use dy/dx!)
 else if(dx>0 && dy>0) { angle = d1 + Math.atan(ady/adx); }		// direction: X+, Y+
 else if(dx<0 && dy>0) { angle = d2 + Math.atan(adx/ady); }		// direction: X-, Y+
 else if(dx<0 && dy<0) { angle = d3 + Math.atan(ady/adx); }		// direction: X-, Y-
 else if(dx>0 && dy<0) { angle = d4 + Math.atan(adx/ady); }		// direction: X+, Y-
 // return directionality in positive radians
 return (float)((angle + 2*PI)%(2*PI)); }

//universal helper function: rotate a coordinate over (0,0) by [angle] radians
public static int[] rotateCoordinate(float x, float y, float angle) {
 int[] rc = {0,0};
 rc[0] = (int)(x*Math.cos(angle) - y*Math.sin(angle));
 rc[1] = (int)(x*Math.sin(angle) + y*Math.cos(angle));
 return rc; }
}