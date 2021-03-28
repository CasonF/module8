import java.util.Random;

public class Execute {
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		
		int[] vals = new int[200000000];
		
		for (int i = 0; i < vals.length; i++)
		{
			vals[i] = rand.nextInt(10) + 1; //makes val[i] a number between 1 and 10
		}
		
		long start = System.currentTimeMillis();
		
		Summation.setThreadsSum(vals); //sets # threads to 4 since that's what I set it to in Summation class
		
		System.out.println("Parallel with set # of threads takes " + (System.currentTimeMillis() - start) + "ms");
		
		start = System.currentTimeMillis(); //resets start time to calc time of next thread(s)
		
		Summation.parallelSum(vals);
		
		System.out.println("Parallel threads take " + (System.currentTimeMillis() - start) + "ms");
		
		start = System.currentTimeMillis();
		
		Summation.sum(vals);
		
		System.out.println("Single thread takes " + (System.currentTimeMillis() - start) + "ms");
		
	}

}
