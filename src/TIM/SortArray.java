package TIM;

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
}
