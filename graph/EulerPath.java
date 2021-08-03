package graph;

// Euler circuit
//  a path where all the EDGES are visited exactly once and there exists a path between the last vertex 
// and first vertex

// Euler path
// all EDGES are visited exactly once but no need of path between last and first vertex
// Every euler circuit has euler path as well.

// Almost same as hamiltonian path and cycle, just there constraints were on VERTEX and here on EDGES

// Approach to find whether euler path or circuit exists or not

// Undirected :-

// Eulerian circuit - 
//  Degree of every node - EVEN
// Eulerian Path - 
//  Degree of (n-2) nodes should be EVEN and remaining two nodes should be ODD
//  STRICTLY 2 nodes can have degree odd (not 4 nodes, etc)

// Directed :-

// Eulerian circuit - 
//  InDegree of every node == OutDegree of every node 
// Eulerian Path - 
// InDegree of (n-2) nodes should be equal to OutDegree and remaining two nodes should - 
// Source Node -> OutDegree = Indegree + 1
// Destination Node -> Indegree = OutDegree + 1



public class EulerPath {

	public static void main(String[] args) {
		

	}

}
