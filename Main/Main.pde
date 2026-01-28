float x = 10;

ArrayList<Node> nodes = new ArrayList<Node>();
ArrayList<Edge> edges = new ArrayList<Edge>();


void setup() {
  fullScreen();
  frameRate(30);
  smooth(2);  
  Node a = new Node(200, 700, 200);
  Node b = new Node(700, 200, 200);
  Node c = new Node(1400, 700, 200);

  nodes.add(a);
  nodes.add(b);
  nodes.add(c);

  Edge ab = new Edge(a, b);
  //Edge ba = new Edge(b, a);
  Edge bc = new Edge(b, c);
  //Edge cb = new Edge(c, b);
  Edge ac = new Edge(a, c);
  //Edge ca = new Edge(c, a);

  edges.add(ab);
//  edges.add(ba);
  edges.add(bc);
//  edges.add(cb);
  edges.add(ac);
//  edges.add(ca);

  // important: attach outgoing edges to nodes
  a.outgoing.add(ab);
  a.outgoing.add(ac);

  //b.outgoing.add(ba);
  b.outgoing.add(bc);

  //c.outgoing.add(ca);
  //c.outgoing.add(cb);
}


void draw() {
  background(204);


  // draw edges first
  for (Edge e : edges) {
    e.draw();
  }

  // draw nodes on top
  for (Node n : nodes) {
    n.draw();
  }
}







void mousePressed(){
  
}
