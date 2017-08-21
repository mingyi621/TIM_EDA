package TIM;

import java.io.FileWriter;
import java.io.IOException;

public class TIM_Obj 
{
	Buyer[] buyer;
	Seller[] seller;
	double[] askArray;
	double[][] bidArray;
	
	public TIM_Obj(Buyer[] inputBuyer, Seller[] inputSeller, double[] inputAskArray, double[][] inputBidArray)
	{
		buyer = inputBuyer;
		seller = inputSeller;
		askArray = inputAskArray;
		bidArray = inputBidArray;
		candidateDeterminationAndPricing();
		candidateElimination();
	}
	
	public void candidateDeterminationAndPricing()
	{
		for(int s = 0; s < seller.length; s++)
		{
			seller[s].setMedianAsk(askArray);
			for(int b = 0; b < buyer.length; b++)
			{
				buyer[b].setNotLessThanIndicator(askArray);
				seller[s].setNumberOfNotLessThanJ(buyer[b].getNotLessThanIndicator());
			}
			if(seller[s].getNumberOfNotLessThanJ() == 1)
			{
				int theBuyer = -1;
				for(int b = 0; b < buyer.length; b++)
				{
					if(buyer[b].getNotLessThanIndicator()[s] == true && seller[s].getMedianAsk() >= seller[s].getAsk())
					{
						theBuyer = b;
						break;
					}
				}
				if(theBuyer != -1)
				{
					seller[s].setTheBuyer(theBuyer);
					buyer[theBuyer].setBuyerCandidate(true);
					seller[s].setSellerCandidate(true);
					buyer[theBuyer].setBuyerCandidatePrice(seller[s].getMedianAsk());
					seller[s].setSellerCandidatePrice(seller[s].getMedianAsk());
				}
			}
			if(seller[s].getNumberOfNotLessThanJ() > 1)
			{
				Buyer[] buyer2 = new Buyer[buyer.length];
				System.arraycopy(buyer, 0, buyer2, 0, buyer.length);
				SortArray.sortBuyer(buyer2, s);
				if(buyer2[0].getBidArray()[s] >= seller[s].getMedianAsk())
				{
					int theBuyer = buyer2[0].getBuyerNumber();
					seller[s].setTheBuyer(theBuyer);
					buyer[theBuyer].setBuyerCandidate(true);
					seller[s].setSellerCandidate(true);
					buyer[theBuyer].setBuyerCandidatePrice(seller[s].getMedianAsk() >= buyer2[1].getBidArray()[s]? seller[s].getMedianAsk() : buyer2[1].getBidArray()[s]);
					seller[s].setSellerCandidatePrice(seller[s].getMedianAsk() >= buyer2[1].getBidArray()[s]? seller[s].getMedianAsk() : buyer2[1].getBidArray()[s]);
				}
			}		
		}		
	}
	public void candidateElimination()
	{
		for(int s1 = 0; s1 < seller.length-1; s1++)
		{
			for(int s2 = s1 + 1; s2 < seller.length; s2++)
			{
			
				if(seller[s1].getTheBuyer() == seller[s2].getTheBuyer())
				{
					seller[s1].setUtility(bidArray);
					seller[s2].setUtility(bidArray);
					if(seller[s1].getUtility() <= seller[s2].getUtility())
					{
						seller[s1].setSellerCandidate(false);
						seller[s1].setTheBuyer(0);
					}
					else
					{
						seller[s2].setSellerCandidate(false);
						seller[s2].setTheBuyer(0);
					}
				}			
			}
		}
	}
	public void printResult()
	{
		for(int s = 0; s < seller.length; s++)
		{
			if(seller[s].getSellerCandidate() == true)
			{
				System.out.printf("Seller %d wins buyer %d with price %.1f\n", s+1, seller[s].getTheBuyer()+1, seller[s].getSellerCandidatePrice());
			}
		}
	}
	public void printAll()
	{
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%.1f ", seller[s].getAsk());
		}
		System.out.println();
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%.1f ", seller[s].getMedianAsk());
		}
		System.out.println();
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%d ", seller[s].getNumberOfNotLessThanJ());
		}
		System.out.println();
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%d ", seller[s].getTheBuyer());
		}
		System.out.println();
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%d ", seller[s].getSellerCandidate()?1:0);
		}
		System.out.println();
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%.1f ", seller[s].getSellerCandidatePrice());
		}
		System.out.println();
		for(int s = 0; s < seller.length; s++)
		{
			System.out.printf("%.1f ", seller[s].getUtility());
		}
		System.out.println();
	}
	public void outputResult() throws IOException
	{
		FileWriter fw = new FileWriter("output.txt");
		for(int s = 0; s < seller.length; s++)
		{
			if(seller[s].getSellerCandidate() == true)
				fw.write(String.format("Seller %d wins buyer %d with price %.1f\n", s+1, seller[s].getTheBuyer()+1, seller[s].getSellerCandidatePrice()));	
		}
		fw.flush();
		fw.close();
	}
}
