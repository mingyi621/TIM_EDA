package EDA;

public class Seller 
{
	int sellerNumber;
	double ask;
	double medianAsk;
	int numberOfNotLessThanJ;
	double highestBid;
	double secondHighestBid;
	double pricePbs;
	int theBuyer;
	double thePrice;
	boolean sellerWinner;
	
	public Seller(int s, double inputAsk)
	{
		setSellerNumber(s);
		setAsk(inputAsk);
	}
	public void setSellerNumber(int s)
	{
		sellerNumber = s;
	}
	public int getSellerNumber()
	{
		return sellerNumber;
	}
	public void setAsk(double inputAsk)
	{
		ask = inputAsk;
	}
	public double getAsk()
	{
		return ask;
	}
	public void setMedianAsk(double[] askArray)
	{
		double[] askOfMinusJ = new double[askArray.length - 1];
		for(int j = 0, k = 0; j < askArray.length - 1; j++, k++)
		{
			if(sellerNumber == k)
			{
				k++;
			}
			askOfMinusJ[j] = askArray[k];
		}
		SortArray.selectionSort(askOfMinusJ);
		if(askOfMinusJ.length % 2 == 1)
		{
			medianAsk = askOfMinusJ[(askOfMinusJ.length - 1) / 2];
		}
		else
		{
			medianAsk = (askOfMinusJ[askOfMinusJ.length / 2] + askOfMinusJ[askOfMinusJ.length / 2 - 1]) / 2;
		}
	}
	public double getMedianAsk()
	{
		return medianAsk;
	}
	public void setNumberOfNotLessThanJ(boolean notLessThanIndicator)
	{ 
		if(notLessThanIndicator == true)
			numberOfNotLessThanJ++;
	}
	public int getNumberOfNotLessThanJ()
	{
		return numberOfNotLessThanJ;
	}
	public void setHighestBid(Buyer[] buyer, int s)
	{
		Buyer[] buyer2 = new Buyer[buyer.length];
		System.arraycopy(buyer, 0, buyer2, 0, buyer.length);
		SortArray.sortBuyer(buyer2, s);
		highestBid = buyer2[0].getBidArray()[s];
	}
	public double getHighestBid()
	{
		return highestBid;
	}
	public void setSecondHighestBid(Buyer[] buyer, int s)
	{
		Buyer[] buyer2 = new Buyer[buyer.length];
		System.arraycopy(buyer, 0, buyer2, 0, buyer.length);
		SortArray.sortBuyer(buyer2, s);
		secondHighestBid = buyer2[1].getBidArray()[s];
	}
	public double getSecondHighestBid()
	{
		return secondHighestBid;
	}
	public void setPricePbs()
	{
		pricePbs = medianAsk >= secondHighestBid ? medianAsk : secondHighestBid;
	}
	public double getPricePbs()
	{
		return pricePbs;
	}
	public void setTheBuyer(Buyer[] buyer, int s)
	{
		Buyer[] buyer2 = new Buyer[buyer.length];
		System.arraycopy(buyer, 0, buyer2, 0, buyer.length);
		SortArray.sortBuyer(buyer2, s);
		theBuyer = buyer2[0].getBuyerNumber();
	}
	public int getTheBuyer()
	{
		return theBuyer;
	}
	public void setThePrice()
	{
		if(getNumberOfNotLessThanJ() == 1)
			thePrice = pricePbs;
		if(getNumberOfNotLessThanJ() > 1)
			thePrice = getMedianAsk() >= getSecondHighestBid() ? getMedianAsk() : getSecondHighestBid();
	}
	public double getThePrice()
	{
		return thePrice;
	}
	public void setSellerWinner(boolean set)
	{
		sellerWinner = set;
	}
	public boolean getSellerWinner()
	{
		return sellerWinner;
	}
}
