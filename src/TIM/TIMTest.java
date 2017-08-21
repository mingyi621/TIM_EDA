package TIM;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class TIMTest {

	public static void main(String[] args) throws IOException
	{
		// Read the file.
		FileReader fr = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fr);
		
		// Read the number of seller and buyer from the first two lines.
		int numberOfSeller = 0;
		int numberOfBuyer = 0;
		if(br.ready())
		{
			numberOfSeller = Integer.parseInt(br.readLine());
			numberOfBuyer = Integer.parseInt(br.readLine());			
		}
		
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
		
		// Close the file.
		fr.close();
				
		// Find the median ask of A(-j)
		double[] medianAsk = new double[numberOfSeller];
		for(int i = 0; i < numberOfSeller; i++)
		{
			// Create array ask of minus j
			double[] askOfMinusJ = new double[numberOfSeller-1];
			for(int j = 0, k = 0; j < numberOfSeller - 1; j++, k++)
			{
				if(i == k)
				{
					k++;
				}
				askOfMinusJ[j] = askArray[k];
			}
			
			// Sort array ask of minus j
			for(int j = 0; j < askOfMinusJ.length; j++)
			{
				int target = 0;
				for(int k = j + 1; k < askOfMinusJ.length; k++)
				{
					if(askOfMinusJ[k] < askOfMinusJ[j])
					{
						if(target == 0)
							target = k;
						if(target != 0 && askOfMinusJ[k] < askOfMinusJ[target])
						{
							target = k;
						}
					}
				}
				if(target != 0)
				{
					double temp;
					temp = askOfMinusJ[j];
					askOfMinusJ[j] = askOfMinusJ[target];
					askOfMinusJ[target] = temp;
				}				
			}
			
			// Determine the median
			if(askOfMinusJ.length % 2 == 1)
			{
				medianAsk[i] = askOfMinusJ[(askOfMinusJ.length - 1) / 2];
			}
			else
			{
				medianAsk[i] = (askOfMinusJ[askOfMinusJ.length / 2] + askOfMinusJ[askOfMinusJ.length / 2 - 1]) / 2;
			}
		}
		
		// Find the number and the buyer who is not less than ask
		int[] numberOfNotLessThanAskJ = new int[numberOfSeller];
		boolean[][] notLessThanIndicator = new boolean[numberOfBuyer][numberOfSeller];
		for(int ask = 0; ask < numberOfSeller; ask++)
		{
			numberOfNotLessThanAskJ[ask] = 0;
			for(int i = 0; i < numberOfBuyer; i++)
			{
				if(bidArray[i][ask] >= askArray[ask])
				{
					numberOfNotLessThanAskJ[ask]++;
					notLessThanIndicator[i][ask] = true;
				}
			}
		}
		
		// Find the Buyer Candidate
		boolean[][] buyerCandidate = new boolean[numberOfBuyer][numberOfSeller];
		double[][] buyerCandidatePrice = new double[numberOfBuyer][numberOfSeller];
		
		for(int s = 0; s < numberOfSeller; s++)
		{
			// If the number of buyer whose bid is not less than ask is 1
			if(numberOfNotLessThanAskJ[s] == 1)
			{
				int notLessThanBuyer = -1;
				
				// Find the only one buyer that is not less than ask
				for(int buyer = 0; buyer < numberOfBuyer; buyer++)
				{
					if(notLessThanIndicator[buyer][s] == true)
					{
						notLessThanBuyer = buyer;
						break;
					}
				}
				
				// Determine the buyer is the candidate or not
				if(bidArray[notLessThanBuyer][s] >= medianAsk[s] && askArray[s] <= medianAsk[s])
				{
					buyerCandidate[notLessThanBuyer][s] = true;
					buyerCandidatePrice[notLessThanBuyer][s] = medianAsk[s];
				}
			}
			
			// If the number of buyer whose bid is not less than ask is bigger than 1
			if(numberOfNotLessThanAskJ[s] > 1)
			{
				double highestBid = -1;
				double secondHighestBid = -1;
				int highestBidBuyer = -1;
				int secondHighestBuyer = -1;
				
				// Determine the highest and the second highest buyer
				for(int buyer = 0; buyer < numberOfBuyer; buyer++)
				{
					if(notLessThanIndicator[buyer][s] == true)
					{
						if(bidArray[buyer][s] > highestBid)
						{
							secondHighestBid = highestBid;
							secondHighestBuyer = highestBidBuyer;
							highestBid = bidArray[buyer][s];
							highestBidBuyer = buyer;
							continue;
						}
						if(bidArray[buyer][s] > secondHighestBid && bidArray[buyer][s] <= highestBid) 
						{
							secondHighestBid = bidArray[buyer][s];
							secondHighestBuyer = buyer;
							continue;
						}
					}
				}
				
				if(highestBid >= medianAsk[s])
				{
					buyerCandidate[highestBidBuyer][s] = true;
					buyerCandidatePrice[highestBidBuyer][s] = (medianAsk[s] >= secondHighestBid)? medianAsk[s]: secondHighestBid;
				}
			}
		}
		
		// If there are more than 1 buyer candidate, choose the one with the highest utility as the winner.
		for(int buyer = 0; buyer < numberOfBuyer; buyer++)
		{
			int b = 0;
			int seller = 0;
			
			//Determine the number of candidates.
			for(seller = 0; seller < numberOfSeller; seller++)
			{
				if(buyerCandidate[buyer][seller] == true)
				{
					b++;
				}
			}
			//If there are more than one candidates.
			if(b > 1)
			{
				double[] utility = new double[seller];
				for(seller = 0; seller < numberOfSeller; seller++)
				{
					utility[seller] = bidArray[buyer][seller] - buyerCandidatePrice[buyer][seller];
				}
				double higestUtility = -1;
				int highestUtilitySeller = -1;
				for(seller = 0; seller < numberOfSeller; seller++)
				{
					if(utility[seller] > higestUtility)
					{
						higestUtility = utility[seller];
						highestUtilitySeller = seller;
					}
				}
				for(seller = 0; seller < numberOfSeller; seller++)
				{
					if(highestUtilitySeller != seller)
					{
						buyerCandidate[buyer][seller] = false;
						buyerCandidatePrice[buyer][seller] = 0;
					}
				}		
			}		
		}
		
		// Output the result on the screen.
		for(int i = 0; i< numberOfBuyer; i++)
		{
			for(int j = 0; j< numberOfSeller; j++)
			{
				if(buyerCandidate[i][j] == true)
					System.out.printf("Buyer %d wins seller %d with price %.1f.\n", i+1, j+1, buyerCandidatePrice[i][j]);
			}
		}
		
		// Output the result to the file.
		FileWriter fw = new FileWriter("output.txt");
		for(int i = 0; i< numberOfBuyer; i++)
		{
			for(int j = 0; j< numberOfSeller; j++)
			{
				if(buyerCandidate[i][j] == true)
					fw.write(String.format("Buyer %d wins seller %d with price %.1f.\n", i+1, j+1, buyerCandidatePrice[i][j]));
			}
		}
		fw.flush();
		fw.close();

	}
}
