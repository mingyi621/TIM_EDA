package EDA;

public class Buyer 
{
	int buyerNumber;
	double[] bidArray;
	boolean[] notLessThanIndicator;
	double thePrice;
	boolean buyerWinner;
	
	public Buyer(int b, double[] inputBidArray)
	{
		setBuyerNumber(b);
		setBidArray(inputBidArray);
		notLessThanIndicator = new boolean[bidArray.length];
	}
	public void setBuyerNumber(int b)
	{
		buyerNumber = b;
	}
	public int getBuyerNumber()
	{
		return buyerNumber;
	}
	public void setBidArray(double[] inputBidArray)
	{
		bidArray = inputBidArray;
	}
	public double[] getBidArray()
	{
		return bidArray;
	}
	public void setNotLessThanIndicator(double ask, int s)
	{
		if(bidArray[s] >= ask && getBuyerWinner() == false)
		{
			notLessThanIndicator[s] = true;
		}
	}
	public boolean getNotLessThanIndicator(int s)
	{
		return notLessThanIndicator[s];
	}
	public void setThePrice(double inputPrice)
	{
		thePrice = inputPrice;
	}
	public double getThePrice()
	{
		return thePrice;
	}
	public void setBuyerWinner(boolean set)
	{
		buyerWinner = set;
		for(int s = 0; s < bidArray.length; s++)
			bidArray[s] = 0; 
	}
	public boolean getBuyerWinner()
	{
		return buyerWinner;
	}
}
