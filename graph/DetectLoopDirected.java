package graph;

import java.util.LinkedList;
// There is cycle in graph only if there is back edge present in the graph
// a back edge is an edge that is from a to itself(self-loop) or one of its ancestors in the tree produced
// by dfs
// to check back-edge we can create a boolean array which will store whether the node is in recursion stack 
// or not

// Using dfs approach
// TC: O(V+E)
// SC: O(V)

public class DetectLoopDirected {
	
	static boolean cycle(Graph g)
	{
		int v = g.v;
		LinkedList<Integer> adj[] = g.adj;
		boolean visited[] = new boolean[v];
		boolean recStack[] = new boolean[v];
		for(int i=0;i<v;i++)
		{
			
			if(dfs(v, adj, i, visited, recStack) == true)
				return true;

		}
		return false;
	}
	
	static boolean dfs(int v, LinkedList<Integer>[] adj, int s, boolean visited[], boolean recStack[])
	{
		if(recStack[s])   // back edge is found and hence cycle is present
			return true;
		
		if(visited[s])    // if this node is already visited and not present in recStack than it is a cross
			return false; // edge which does not form cycle
		
		visited[s] = true;
		recStack[s] = true;
		for(int i=0; i<adj[s].size();i++)
		{
			int n = adj[s].get(i);
			
		
			if(dfs(v, adj, n, visited, recStack) == true)
				return true;
				
		}
		
		// now move the node out of recursion stack
		recStack[s] = false;         // IMP: backtrack
		return false;
		
		
	}

	public static void main(String[] args) {
		

		
		
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		
		System.out.println(cycle(g));
		

		Graph g1 = new Graph(6);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		g1.addEdge(5, 4);
		g1.addEdge(3, 5);
		
		
		System.out.println(cycle(g1));
		
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(2, 3);
		g2.addEdge(2, 1);
		g2.addEdge(3, 4);
		g2.addEdge(4, 0);
		g2.addEdge(4, 2);
		
		System.out.println(cycle(g2));
		
		Graph g3 = new Graph(5);
		g3.addEdge(0, 1);
		g3.addEdge(0, 2);
		g3.addEdge(1, 2);
		g3.addEdge(2, 3);
	
		
		System.out.println(cycle(g3));
		
		Graph g4 = new Graph(5);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		//g3.addEdge(2, 3);
		
		System.out.println(cycle(g4));
	}

}
