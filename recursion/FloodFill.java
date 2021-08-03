package recursion;

public class FloodFill {
	
	static void floodFill(int arr[][],int r, int c, int toFill, int prevFill )
	{
		
		int rows = arr.length;
		int columns = arr[0].length;
		
		if(r<0 || r>= rows  || c>= columns || c<0)
			return;
		
		if(arr[r][c]!=prevFill)
			return;
		
		arr[r][c] = toFill;
		
		floodFill(arr, r+1, c, toFill, prevFill);
		floodFill(arr, r-1, c, toFill, prevFill);
		floodFill(arr, r, c+1, toFill, prevFill);
		floodFill(arr, r, c-1, toFill, prevFill);		
	}
	
	static void printMat(int mat[][])
	{
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		int arr[][]= {
				{1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,0,0},
				{1,0,0,1,1,0,1,0},
				{1,2,2,2,2,0,1,0},
				{1,1,1,2,2,0,1,0},
				{1,1,1,2,2,2,2,0},
				{1,1,1,1,1,2,1,1},
				{1,1,1,1,1,2,2,1},
		};
		
		floodFill(arr, 0, 0, 8, 1);
		
		printMat(arr);
				
		}

}


