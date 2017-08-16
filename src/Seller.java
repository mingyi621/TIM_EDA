
public class Seller 
{
	int sellerNumber;
	double ask;
	double medianAsk;
	int numberOfNotLessThanJ;
	int theBuyer;
	boolean sellerCandidate;
	double sellerCandidatePrice;
	double utility;
	
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
//		System.out.printf("Median Ask = %.1f\n", medianAsk);
	}
	public double getMedianAsk()
	{
		return medianAsk;
	}
	public void setNumberOfNotLessThanJ(boolean[] notLessThanIndicator)
	{ 
		if(notLessThanIndicator[sellerNumber] == true)
			numberOfNotLessThanJ++;
	}
	public int getNumberOfNotLessThanJ()
	{
		return numberOfNotLessThanJ;
	}
	public void setTheBuyer(int b)
	{
		theBuyer = b;
	}
	public int getTheBuyer()
	{
		return theBuyer;
	}
	public void setSellerCandidate(boolean set)
	{
		sellerCandidate = set;
	}
	public boolean getSellerCandidate()
	{
		return sellerCandidate;
	}
	public void setSellerCandidatePrice(double inputPrice)
	{
		sellerCandidatePrice = inputPrice;
	}
	public double getSellerCandidatePrice()
	{
		return sellerCandidatePrice;
	}
	public void setUtility(double bidArray[][])
	{
		utility = bidArray[getTheBuyer()][getSellerNumber()]- sellerCandidatePrice;
	}
	public double getUtility()
	{
		return utility;
	}

}
