package graph;

import java.util.LinkedList;
import java.util.PriorityQueue;


// given a weighted graph where nodes represent city and edges represent cost of
// flight from one city to other
// task is to find the cheapest flight from source to destination if at most you are
// allowed to take k stops
// only intermediate nodes will be counted as stops taken
public class CheapestFlightWithinKStops {
	// idea is to use dijkstra. Just add one more condition to check if stops till now
	// is less than k or not. If not than neglect and no need to proceed further
	
	static class Pair implements Comparable<Pair>
	{
		int s;
		int cost;
		int stopsTaken;
		
		Pair(int s, int cost, int k)
		{
			this.s = s;
			this.cost = cost;
			this.stopsTaken = k;
		}

		@Override
		public int compareTo(Pair o) {
			return this.cost - o.cost;
			
		}
	}
	
	static void solve(WGraph g, int src, int dest, int k)
	{
		int v = g.v;
		LinkedList<Edge> adj[] = g.adj;
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		pq.add(new Pair(src , 0 , 0));
		boolean visited[] = new boolean[v];
		
		while(!pq.isEmpty())
		{
			Pair curr = pq.poll();
			visited[curr.s] = true;
		
			
			if(curr.s == dest && curr.stopsTaken -1 <= k)
			{
				System.out.println(curr.cost);
				return;
			}
			int s = curr.s;
			int cost = curr.cost;
			int stops = curr.stopsTaken;
			for(int i=0;i<adj[s].size();i++)
			{
				int n = adj[s].get(i).e;
				int w = adj[s].get(i).w;
				if(!visited[n])
				{
					
					pq.add(new Pair(n, cost + w, stops + 1));
				}
			}
		}
		
		System.out.println("Not possible");
		
		
	}

	public static void main(String[] args) {
		WGraph g = new WGraph(3);
		g.addEdge(0, 1, 100);
		g.addEdge(0, 2, 500);
		g.addEdge(1, 2, 200);
		
		solve(g, 0, 2, 1);
		solve(g, 0, 2, 0);
		

	}

}
