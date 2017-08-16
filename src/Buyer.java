
public class Buyer 
{
	int buyerNumber;
	double[] bidArray;
	boolean[] notLessThanIndicator;
	boolean buyerCandidate;
	double buyerCandidatePrice;

	public Buyer(int b, double[] inputBidArray)
	{
		setBuyerNumber(b);
		setBidArray(inputBidArray);
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
	public void setNotLessThanIndicator(double[] askArray)
	{
		notLessThanIndicator = new boolean[askArray.length];
		for(int s = 0; s < askArray.length; s++)
		{
			if(bidArray[s] >= askArray[s])
				notLessThanIndicator[s] = true;
		}
	}
	public boolean[] getNotLessThanIndicator()
	{
		return notLessThanIndicator;
	}
	public void setBuyerCandidate(boolean set)
	{
		buyerCandidate = set;
	}
	public boolean getBuyerCandidate()
	{
		return buyerCandidate;
	}
	public void setBuyerCandidatePrice(double inputPrice)
	{
		buyerCandidatePrice = inputPrice;
	}
	public double getBuyerCandidatePrice()
	{
		return buyerCandidatePrice;
	}
}
