package graph;
// Kahn's algorithm is used to print topological sort of the list

import java.util.LinkedList;

// Idea is to use indegree to print topological order. First print the node with indegree 1 and then
// keep on reducing the indegree of its connected nodes and continue for other nodes as well.
// Use queue for this
// If any nodes are left after queue is empty than it means either it is undirected graph or directed grpah
// with cycle.
// All the nodes which will be left will be part of the cycle.
// This algo can be used to find whether a cycle exists in directed graph using BFS
public class KahnsAlgorithm {
	
	static void solve(Graph g)
	{
		int v= g.v;
		LinkedList<Integer> adj[] = g.adj;
		
		// first calculate indegree
		int indegree[] = new int[v];
		
		for(int i=0;i<v;i++)
		{
			for(int nbr:adj[i])
			{
				indegree[nbr]++;
			}
		}
		
		LinkedList<Integer> q = new LinkedList<>();
		// add all the nodes with indegree 0 in queue
		for(int i=0;i<v;i++)
		{
			if(indegree[i] == 0)
				q.add(i);
		}
		
		int count = 0;
		while(q.size() > 0)
		{
			int x = q.remove();
			System.out.print(x+" ");
			count++;
			for(int nbr:adj[x])
			{
				indegree[nbr]--;
				if(indegree[nbr] == 0)
					q.add(nbr);
			}
		}
		System.out.println();
		if(count != v)
			System.out.println("This graph is not DAG");
	}

	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(4, 3);
		g.addEdge(4, 5);
		g.addEdge(4, 6);
//		g.addEdge(4, 2);
		g.addEdge(5, 6);
		
		solve(g);
	}

}
