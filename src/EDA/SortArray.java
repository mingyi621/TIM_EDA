package EDA;
import java.util.Random;

public class SortArray 
{
	public static void selectionSort(double[] inputArray)
	{
		int i, j, min;
		double temp;
		for (i = 0; i < inputArray.length - 1; i++)
		{
			min = i;
			for(j = i + 1; j < inputArray.length; j++)
			{
				if(inputArray[j] < inputArray[min])
				{
					min = j;
				}
			}
			temp = inputArray[min];
			inputArray[min] = inputArray[i];
			inputArray[i] = temp;			
		}		
	}
	public static void sortBuyer(Buyer[] buyer, int s)
	{
		for(int i = 0; i < buyer.length-1; i++)
		{
			int min = i;
			for(int j = i+1 ; j < buyer.length; j++)
			{
				if(buyer[min].getBidArray()[s] < buyer[j].getBidArray()[s])
				{
					min = j;
				}
			}
			Buyer temp;
			temp = buyer[min];
			buyer[min] = buyer[i];
			buyer[i] = temp;
		}
	}
	public static int[] createRandomArray(int arrayLength)
	{
		Random random = new Random();
		int[] result = new int[arrayLength];
		for(int i = 0; i < result.length;)
		{
			result[i] = random.nextInt(arrayLength);
			int j;
			for(j = 0; j < i; j++)
			{
				if(result[j] == result[i])
					break;
				else
					continue;
			}
			if(j == i)
				i++;
		}
		return result;
	}
	public static void printArray(int[] array)
	{
		System.out.print("[");
		for(int i = 0; i < array.length; i++)
		{
			System.out.printf("%d",array[i]);
			if(i != array.length - 1)
				System.out.print(",");
		}
		System.out.print("]\n");
	}
}