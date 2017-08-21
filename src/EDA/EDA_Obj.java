package EDA;

public class EDA_Obj
{
	Buyer[] buyer;
	Seller[] seller;
	double[] askArray;
	double[][] bidArray;
		
	public EDA_Obj(Buyer[] inputBuyer, Seller[] inputSeller, double[] inputAskArray, double[][] inputBidArray)
	{
		buyer = inputBuyer;
		seller = inputSeller;
		askArray = inputAskArray;
		bidArray = inputBidArray;
		EDA_Implementation();
	}

	public void EDA_Implementation()
	{
//		int[] randomArray = {2,4,6,0,3,5,1};
		int[] randomArray = SortArray.createRandomArray(seller.length);
		System.out.print("\nThe order of the sellers: ");
		SortArray.printArray(randomArray);
		for(int i = 0, s = randomArray[i]; i < seller.length ; i++, s = randomArray[i])
		{
			seller[s].setMedianAsk(askArray);
			for(int b = 0; b < buyer.length; b++)
			{
				buyer[b].setNotLessThanIndicator(askArray[s], s);
				seller[s].setNumberOfNotLessThanJ(buyer[b].getNotLessThanIndicator(s));
			}
			if(seller[s].getNumberOfNotLessThanJ() == 1)
			{
				seller[s].setSecondHighestBid(buyer, s);
				seller[s].setPricePbs();
				seller[s].setHighestBid(buyer, s);
				if(seller[s].getHighestBid() >= seller[s].getPricePbs() && seller[s].getAsk() <= seller[s].getPricePbs())
				{
					seller[s].setTheBuyer(buyer, s);
					buyer[seller[s].getTheBuyer()].setBuyerWinner(true);
					seller[s].setSellerWinner(true);
					buyer[seller[s].getTheBuyer()].setThePrice(seller[s].getPricePbs());
					seller[s].setThePrice();
				}
			}
			if(seller[s].getNumberOfNotLessThanJ() >= 1)
			{
				seller[s].setHighestBid(buyer, s);
				seller[s].setSecondHighestBid(buyer, s);
				if(seller[s].getHighestBid() >= seller[s].getMedianAsk())
				{
					seller[s].setTheBuyer(buyer, s);
					buyer[seller[s].getTheBuyer()].setBuyerWinner(true);
					seller[s].setSellerWinner(true);
					buyer[seller[s].getTheBuyer()].setThePrice(seller[s].getPricePbs());
					seller[s].setThePrice();
				}			
			}
			if(i == seller.length-1) 
				break;
		}
	}
	public void printResult()
	{
		for(int s = 0; s < seller.length; s++)
		{
			if(seller[s].getSellerWinner() == true)
			{
				System.out.printf("Seller %d wins buyer %d with price %.1f\n", s + 1, seller[s].getTheBuyer() + 1, seller[s].getThePrice());
			}
		}
	}
}
