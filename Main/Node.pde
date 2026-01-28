class Node {


  ArrayList<Edge> outgoing = new ArrayList<Edge>();


  PVector dim; //for visual consctuciton

  int g, rhs;

  Node(int _x, int _y, int _size) {
    dim = new PVector(_x, _y, _size);
  }


  void draw() {
    fill(255);  
    //  strokeWeight(2);
    noStroke();
    circle(dim.x, dim.y, dim.z);
  }
}

class Edge {

  Node from;
  Node too;

  int cost;
  boolean blocked;



  Edge(Node _a, Node _b) {
    from = _a;
    too = _b;
  }

  void draw() {
    strokeWeight(20);
    stroke(0);
    line(from.dim.x, from.dim.y, too.dim.x, too.dim.y);
  }
}
