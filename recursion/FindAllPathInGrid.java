package recursion;

public class FindAllPathInGrid {
	// find all path possible in grid from first block to last block if only allowed to move right or down 
	
	static int paths(int rows, int columns)         //A Grid         
	{
		if(rows == 1 || columns == 1)
			return 1;
		
		return paths(rows, columns-1) + paths(rows-1, columns);
	}
	// more optimized solution can be achieved using dp
	public static void main(String[] args) {
		
		System.out.println(paths(4,4));
		System.out.println(paths(3,7));
		
		

	}

}
