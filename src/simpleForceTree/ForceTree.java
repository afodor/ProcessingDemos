package simpleForceTree;

import processing.core.PApplet;

// modified from http://processingjs.nihongoresources.com/graphs/ (from Javascript to Java )

public class ForceTree extends PApplet
{
	int width = 300;
	int height = 300;
	

DirectedGraph g=null;
int padding=30;

public void setup()
{
  frameRate(24);
  noLoop();
}


@Override
	public void settings()
	{
		super.settings();
		   size(width,height);
			 
	}

public void draw()
{
  background(255);
  if(g==null)
  {
    fill(0);
    String s = "Click on this canvas, and pick one:";
    float tw = textWidth(s);
    text(s, (width-tw)/2, height/2 - 50);

    s = "Press \"t\" for a tree,";
    tw = textWidth(s);
    text(s, (width-tw)/2, height/2 - 20);

    s = "\"c\" for a circle graph,";
    tw = textWidth(s);
    text(s, (width-tw)/2, height/2);

    s = "and \"f\" for an FD graph";
    tw = textWidth(s);
    text(s, (width-tw)/2, height/2 + 20);
  }
  else
  {
    boolean done = g.reflow();
    g.draw();
    if(!done) { loop(); } else { noLoop(); }
  }
}

public void keyPressed()
{
  // tree
  if(key=='t' || key==116) {
    makeTree();
    redraw(); }
  // circular graph
  else if(key=='c' || key==99) {
    makeGraph();
    redraw();  }
  // force-directed graph
  else if(key=='f' || key==102) {
    makeGraph();
    g.setFlowAlgorithm(new ForceDirectedFlowAlgorithm());
    redraw(); }
}


public void makeGraph()
{
  // define a graph
  g = new DirectedGraph(width, height);

  // define some nodes
  Node n1 = new Node("1",padding,padding,this);
  Node n2 = new Node("2",padding,height-padding,this);
  Node n3 = new Node("3",width-padding,height-padding,this);
  Node n4 = new Node("4",width-padding,padding,this);
  Node n5 = new Node("5",width-3*padding,height-2*padding,this);
  Node n6 = new Node("6",width-3*padding,2*padding,this);

  // add nodes to graph
  g.addNode(n1);
  g.addNode(n2);
  g.addNode(n3);
  g.addNode(n4);
  g.addNode(n5);
  g.addNode(n6);

  // link nodes
  g.linkNodes(n1,n2);
  g.linkNodes(n2,n3);
  g.linkNodes(n3,n4);
  g.linkNodes(n4,n1);
  g.linkNodes(n1,n3);
  g.linkNodes(n2,n4);
  g.linkNodes(n5,n6);
  g.linkNodes(n1,n6);
  g.linkNodes(n2,n5);
}

void makeTree()
{
  // define a root node
  Node root = new Node("root",0,0,this);

  // define a Tree
  g = new Tree(root,width,height);

  // techincall g is of type DirectedGraph, so build a Tree alias:
  Tree t = (Tree) g;

  // define some children
  Node ca = new Node("a",0,0,this);
  Node caa = new Node("aa",0,0,this);
  Node cab = new Node("ab",0,0,this);
  Node cb = new Node("b",0,0,this);
  Node cba = new Node("ba",0,0,this);
  Node cbb = new Node("bb",0,0,this);
  Node cbba = new Node("bba",0,0,this);
  Node cbbb = new Node("bbb",0,0,this);
  Node cbbba = new Node("bbba",0,0,this);
  Node cbbbb = new Node("bbbb",0,0,this);

  // add all nodes to tree
  t.addChild(root, ca);
  t.addChild(root, cb);
  t.addChild(ca, caa);
  t.addChild(ca, cab);
  t.addChild(cb, cba);
  t.addChild(cb, cbb);
  t.addChild(cbb, cbba);
  t.addChild(cbb, cbbb);
  t.addChild(cbbb, cbbba);
  t.addChild(cbbb, cbbbb);
}
		 public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "simpleForceTree.ForceTree" });
		  }
}
