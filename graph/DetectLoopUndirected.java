package graph;

import java.util.ArrayDeque;
import java.util.LinkedList;

// in undirected graph we do not have to keep track of recursion stack members
// but in this we need to keep track of parent of every node because in edge u-v we can reach u to v
// and v to u, but it is not a cycle, and we can check this by keeping track of parent
public class DetectLoopUndirected {
	
	static boolean cycle(Graph g)
	{
		int v = g.v;
		LinkedList<Integer>[] adj= g.adj; 
		boolean visited[] = new boolean[v];
		for(int i=0;i<v;i++)
		{
			if(dfs(v, adj, i, visited, -1) == true)
				return true;
		}
		return false;
	}
	
	
	static boolean dfs(int v, LinkedList<Integer> []adj, int s, boolean visited[], int parent)
	{
		visited[s] = true;
		
		for(int i=0;i<adj[s].size(); i++)
			{
				int n = adj[s].get(i);
				
				if(visited[n] == true)   // possibility of cycle 
				{
					if(parent != n)      // if next node is not the parent than it is a cycle
					{
						return true;
					}
				}
				
				else
				{
					if(dfs(v, adj, n, visited, s))
						return true;
				}
				
			}
		
		return false;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////
	// using bfs
	
	static boolean solve(Graph g)
	{
		int v = g.v;
		LinkedList<Integer> adj[] = g.adj;
		boolean visited[] = new boolean[v];
		// giving every vertex chance because graph might be have multiple components
		
		for(int i=0;i<v;i++)
		{
			if(visited[i] == false)    //IMP:
			{
				if(solveUtil(v, adj, i, visited) == true)
				return true;
			}
		}
		return false;
	}
	
	static boolean solveUtil(int v, LinkedList<Integer> adj[], int s, boolean visited[])
	{
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(s);
		
		while(q.size() > 0)
		{
			// r m* w a*
			int p = q.removeFirst();
			
			if(visited[p] == true)
			{
				return true;
			}
			
			visited[p] = true;
			
			for(int i=0;i < adj[p].size();i++)
			{
				if(visited[adj[p].get(i)] == false)      //IMP:
					q.add(adj[p].get(i));
			}
		}
		return false;
	}

	public static void main(String[] args) {
		
		
		Graph g = new Graph(7);
		
		g.addEdge(0, 1);
		g.addEdge(1, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 2);
		g.addEdge(4, 5);
		g.addEdge(5, 4);
		g.addEdge(4, 6);
		g.addEdge(6, 4);
		g.addEdge(5, 6);
		g.addEdge(6, 5);
		
		System.out.println(solve(g));
		System.out.println(cycle(g));
		
		
		Graph g1 = new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(0, 3);
		g1.addEdge(1, 0);
		g1.addEdge(2, 0);
		g1.addEdge(2, 3);
		g1.addEdge(3, 0);
		g1.addEdge(3, 2);
		
		System.out.println(solve(g1));
		System.out.println(cycle(g1));
	}

}
