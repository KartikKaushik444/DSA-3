package graph;

// given a set of cities and distance between every pair of cities, the problem is to find the shortest 
// possible route that visits every city exactly once and returns  back to the starting point.

// It is different from Hamiltonian cycle. There we can take any route irrespective of distance.
// In this problem we need to choose the cycle with minimum distance.
public class TravellingSalesmanProblem {
	
	static void tsp(int [][]graph)
	{
		int n = graph.length;
		
		boolean[] vis = new boolean[n];
		// taking 0th node as the starting point 
		vis[0] = true;
		int ans = Integer.MAX_VALUE;
		ans = dfs(graph, vis, 0,n, 1, 0, ans);
		System.out.println(ans);
	}
	
	static int dfs(int [][]graph, boolean vis[], int curr, int n, int count, int cost, int ans)
	{
		vis[curr] = true;
		
		if(count == n && graph[curr][0] > 0)
		{
			ans = Math.min(ans, cost+graph[curr][0]);
			vis[curr] = false;
			return ans;
			
		}
		
		for(int i=0;i<n;i++)
		{
			if(vis[i] == false && graph[curr][i] > 0)
			{
				//vis[i] = true;
				ans = dfs(graph, vis, i, n, count+1, cost+graph[curr][i], ans);		
			}
		}
		vis[curr] = false;  // IMP: backtracking
		return ans;
	}

	public static void main(String[] args) {
		int [][]g  = {
				{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 0, 30}
		};
		tsp(g);
		

	}

}
