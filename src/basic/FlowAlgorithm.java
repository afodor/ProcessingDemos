package basic;

/**
 * Simmple graph layout system
 * http://processingjs.nihongoresources.com/graphs
 * This code is in the public domain
 */

//modified from http://processingjs.nihongoresources.com/graphs/ (from Javascript to Java )
// this is the interface for graph reflowing algorithms
public interface FlowAlgorithm {
  // returns "true" if done, or "false" if not done
  public boolean reflow(DirectedGraph g,int height, int width); }