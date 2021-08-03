package graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class LongestPathInDAG {
	

	// below solve function will give longest path from any node to any node(it may or not cover all nodes)
	// dp[i] stores longest path from i to j where j can any node including i itself if all edges are negative
	// This will not work if cycle is present in the graph
	
	static void solve(WGraph g)
	{
		int v = g.v;
		LinkedList<Edge> adj[] = g.adj;
		
		boolean vis[] = new boolean[v];
		
		int dp[] = new int[v];
		//visit(s, adj, dp, vis);
		for(int i=0;i<v;i++)
		{
			if(vis[i] == false)
				visit(i, adj, dp, vis);
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i=0;i<v;i++)
		{
			ans = Math.max(ans, dp[i]);
		}
		
//		for(int i=0;i<v;i++)
//		{
//			System.out.print(dp[i]+" ");
//		}
//		System.out.println();
		System.out.println(ans);
		
	}
	
	static void visit(int v, LinkedList<Edge> adj[], int dp[], boolean vis[])
	{
		vis[v] = true;
		
		dp[v] = 0;
		
		for(int i=0;i<adj[v].size();i++)
		{
			int a = i;
			int b = adj[v].get(i).e;
			int w = adj[v].get(i).w;
			
			if(vis[b] != true)
				visit(b, adj, dp, vis);
			
			dp[v] = Math.max(dp[v], dp[b] + w);
		}
	}
	
	

	//////////////////////////////////////////////////////////////////////////////
	
	// TC : O(v+E)
	// This function will give length of longest path from a given source to that node
	// dist[i] stores length of longest path from source to i
	
	
	static void longest(WGraph g,int s)
	{
		int v = g.v;
		LinkedList<Edge> adj[] = g.adj;
		// first find topological sorting
		Stack<Integer> st = new Stack<>();
		boolean vis[] = new boolean[v];
		
		for(int i=0;i<v;i++)
		{
			if(vis[i] == false)
				topo(i, adj, st, vis);
		}
		
		// first initialize distance to all vertices as infinity and source as 0
		int dist[] = new int[v];
		Arrays.fill(dist, Integer.MIN_VALUE);
		
		dist[s] = 0;
		
		// now process according to topo sort
		while(!st.isEmpty())
		{
			int u = st.peek();
			st.pop();
			
			//update distance to all adjacent vertices
			if(dist[u] != Integer.MIN_VALUE)
			{
				for(int i=0;i <adj[u].size();i++)
				{
					int n = adj[u].get(i).e;
					int w = adj[u].get(i).w;
					if(dist[n] < dist[u] + w)
						dist[n] = dist[u] + w;
				}
			}
		}
		
		for(int i=0;i<v;i++)
		{
			System.out.print(dist[i] +" ");
		}
		System.out.println();
		
	}
	
	

	static void topo(int v, LinkedList<Edge> adj[], Stack<Integer> st, boolean visited[])
	{
		if(visited[v])
			return;
		
		visited[v] = true;
		
		for(int i=0;i<adj[v].size();i++)
		{
			if(!visited[adj[v].get(i).e])
			{
				topo(adj[v].get(i).e, adj, st, visited);
			}
		}
		
		st.push(v);
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	// Dijkstra can not work for finding longest path because in Dijkstra we use greedy approach and
		// it only works with positive weight. There we are sure that it will give correct answer for
		// positive weight but we can not use the same concept here.
		// IMP: Dijkstra will work if all the weights are negative because than we can use greedy approach
		// same as for shortest path
	static class Pair implements Comparable<Pair>
	{
		int v;
		String psf;
		int wsf;
		
		Pair(int v,String psf, int wsf)
		{
			this.v = v;
			this.psf = psf;
			this.wsf = wsf;
		}

		@Override
		public int compareTo(Pair o) {
			
			return this.wsf - o.wsf;
		}
	}
	
	static void solve(WGraph g,int s)
	{
		int v = g.v;
		LinkedList<Edge> adj[] = g.adj;
		PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
		//PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(s, s+"" , 0));
		boolean visited[] = new boolean[v];
		
		while(pq.size() > 0)
		{
			Pair p = pq.remove();
			if(visited[p.v] == true)
				continue;
			
			visited[p.v] = true;
			System.out.println(p.v + " via " + p.psf + " covering distance of " +  p.wsf);
			
			for(int i=0; i<adj[p.v].size();i++)
			{
				Edge nbr = adj[p.v].get(i);
				if(visited[nbr.e] == false)
					pq.add(new Pair(nbr.e, p.psf + nbr.e, p.wsf + nbr.w ));
			}
		}
	}
		
	///////////////////////////////////////////////////////////////////////////////////////////
	

	public static void main(String[] args) {
		
		WGraph g = new WGraph(6);
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 3);
		
		g.addEdge(1, 3, 6);
		g.addEdge(1, 2, 2);
		
		g.addEdge(2, 4, 4);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 3, 7);
		
		g.addEdge(3, 5, 1);
		g.addEdge(3, 4, -1);
		g.addEdge(4, 5, -2);
		
		solve(g);
		longest(g, 0);
		
		
		WGraph g1 = new WGraph(5);
		g1.addEdge(0, 1, 5);
		g1.addEdge(1, 2, 2);
		g1.addEdge(0, 3, -5);
		g1.addEdge(3, 2, -2);
		g1.addEdge(2, 4, -20);
		
		solve(g1);
		longest(g1,0);
		solve(g1, 0);
		
		
//		WGraph g = new WGraph(5);
//		g.addEdge(0, 3, 1);
//		g.addEdge(0, 2, 1);
//		
//		g.addEdge(1, 2, 1);
//		g.addEdge(1, 3, 1);
//		g.addEdge(3, 2, 1);
//		g.addEdge(4, 0, 1);
//		g.addEdge(4, 1, 1);
//		g.addEdge(4, 2, 1);
//		
//		solve(g);
	}

}
