package EDA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EDA_ObjTest 
{
	public static void main(String args[]) throws IOException
	{
		// Read the file.
		FileReader fr = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fr);
			
		// Read the number of seller and buyer from the first two lines.
		int numberOfSeller = Integer.parseInt(br.readLine());
		int numberOfBuyer = Integer.parseInt(br.readLine());
			
		// Output the number of sellers and buyers.
		System.out.printf("Number of seller = %d\n", numberOfSeller);
		System.out.printf("Number of buyer = %d\n", numberOfBuyer);
			
		// Read the ask array from the third line.
		double[] askArray = new double[numberOfSeller];
		String askArrayString = br.readLine();
		System.out.printf("The ask array is %s\n", askArrayString);
		StringTokenizer stk1 = new StringTokenizer(askArrayString, "[,]");
		for(int i = 0; stk1.hasMoreTokens(); i++)
		{
	        askArray[i] = Double.parseDouble(stk1.nextToken());
	    }
			
		// Read the bid arrays from the rest of lines.
		double[][] bidArray = new double[numberOfBuyer][numberOfSeller];	
		String bidArrayString = new String();
		StringTokenizer stk2;
		for(int i = 0; i < numberOfBuyer; i++)
		{
			bidArrayString = br.readLine();
			System.out.printf("The bid array of bidder %d is %s\n", i+1, bidArrayString);
			stk2 = new StringTokenizer(bidArrayString, "[,]");
			for(int j = 0; j < numberOfSeller; j++)
			{
				bidArray[i][j] = Double.parseDouble(stk2.nextToken());
			}
		}
			
		Seller[] seller = new Seller[numberOfSeller];
		for(int s = 0; s < numberOfSeller; s++)
		{
			seller[s] = new Seller(s, askArray[s]);
		}
			
		// Set the bid array of buyers
		Buyer[] buyer = new Buyer[numberOfBuyer];
		for(int b = 0; b < numberOfBuyer; b++)
		{
			buyer[b] = new Buyer(b, bidArray[b]);
		}
		
		EDA_Obj eda = new EDA_Obj(buyer, seller, askArray, bidArray);
		eda.printResult();
	}

}
