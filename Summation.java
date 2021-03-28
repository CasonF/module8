import java.util.Scanner;

public class Summation extends Thread {
	
	private int[] vals;
	
	private int floor, ceiling, partial;
	
	public Summation (int[] vals, int floor, int ceiling)
	{
		this.vals = vals;
		this.floor = floor;
		this.ceiling = Math.min(ceiling, vals.length);
	}
	
	public int getPartial()
	{
		return partial;
	}
	
	public void run()
	{
		partial = sum(vals, floor, ceiling);
	}
	
	/////////////////////////////////
	//////// Single thread //////////
	/////////////////////////////////
	public static int sum(int[] vals)
	{
		return sum(vals, 0, vals.length);
	}
	
	public static int sum(int[] vals, int floor, int ceiling)
	{
		int total = 0;
		
		for (int i = floor; i < ceiling; i++)
		{
			total += vals[i];
		}
		
		return total;
	}
	
	/////////////////////////////////////////
	//// Thread count determined by CPU  ////
	/////////////////////////////////////////
	public static int parallelSum(int[] vals)
	{
		return parallelSum(vals, Runtime.getRuntime().availableProcessors());
	}
	
	public static int parallelSum(int[] vals, int threads)
	{
		int num = (int) Math.ceil(vals.length * 1.0 / threads);;
		
		Summation[] sums = new Summation[threads];
		
		for (int i = 0; i < threads; i++)
		{
			sums[i] = new Summation(vals, i * num, (i + 1) * num);
			sums[i].start();
		}
		
		try
		{
			for (Summation sum : sums)
			{
				sum.join();
			}
		}
		catch (InterruptedException e) {}
		
		int total = 0;
		
		for (Summation sum : sums)
		{
			total += sum.getPartial();
		}
		
		return total;
	}
	
	///////////////////////////////////////////
	///////// Set custom thread count /////////
	///////////////////////////////////////////
	public static int setThreadsSum(int[] vals)
	{
		int threads = 4;
		
		return setThreadSum(vals, threads);
	}
	
	public static int setThreadSum(int[] vals, int threads)
	{
		int num = (int) Math.ceil(vals.length * 1.0 / threads);;
		
		Summation[] sums = new Summation[threads];
		
		for (int i = 0; i < threads; i++)
		{
			sums[i] = new Summation(vals, i * num, (i + 1) * num);
			sums[i].start();
		}
		
		try
		{
			for (Summation sum : sums)
			{
				sum.join();
			}
		}
		catch (InterruptedException e) {}
		
		int total = 0;
		
		for (Summation sum : sums)
		{
			total += sum.getPartial();
		}
		
		return total;
	}

}
