/*
	Student name Junqi Li
	Student No 16554835
	Assignment for CSE1OOF
*/
import java.util.*;
public class MedalTally
{
	private int counter;
	private Country[] countryArray = new Country[300];
	
	public MedalTally()
	{
		counter = 0;
	}
	
	public void addCountry(Country p)
	{
		if(counter < countryArray.length)
		{
			countryArray[counter++] = p;
		}
		else
		{
			System.out.println("There is no more room for store.");
		}	
	}
	
	public void deleteCountry(String targetName)
	{
		for (int i = 0;i <counter;i++)
		{
			if ( targetName.equalsIgnoreCase(countryArray[i].getName() ))
			{
				countryArray[i] = null;
				for (int j = i;j < counter - 1;j++)
				{
					countryArray[j] = countryArray[j + 1];
				}
				counter = counter - 1;
			}
		}	
	}
	
	public Country search(String targetName)
	{
		for (int i = 0;i < counter; i++)
		{
			if ( targetName.equalsIgnoreCase(countryArray[i].getName() ))
			{
				return countryArray[i];
			}
		}
		return null;	
	}	

	public void goldTally()
	{
		for (int i = 0;i < counter;i++)
		{
			int lowerIndex = i;
			for (int j = i + 1; j < counter; j++)
			{
				if(countryArray[j].getGold() < countryArray[lowerIndex].getGold())
				{
					lowerIndex = j;
				}	
			}
			Country temp = countryArray[i];
			countryArray[i] = countryArray[lowerIndex];
			countryArray[lowerIndex] = temp;
		}	
	}
	
	public void silverTally()
	{
		for (int i = 0;i < counter;i++)
		{
			int lowerIndex = i;
			for (int j = i + 1; j < counter; j++)
			{
				if(countryArray[j].getSilver() < countryArray[lowerIndex].getSilver())
				{
					lowerIndex = j;
				}	
			}
			Country temp = countryArray[i];
			countryArray[i] = countryArray[lowerIndex];
			countryArray[lowerIndex] = temp;
		}	
	}
	
	public void bronzeTally()
	{
		for (int i = 0;i < counter;i++)
		{
			int lowerIndex = i;
			for (int j = i + 1; j < counter; j++)
			{
				if(countryArray[j].getBronze() < countryArray[lowerIndex].getBronze())
				{
					lowerIndex = j;
				}	
			}
			Country temp = countryArray[i];
			countryArray[i] = countryArray[lowerIndex];
			countryArray[lowerIndex] = temp;
		}	
	}
	
	public void totalTally()
	{
		for (int i = 0;i < counter;i++)
		{
			int lowerIndex = i;
			for (int j = i + 1; j < counter; j++)
			{
				if(countryArray[j].getTotal() < countryArray[lowerIndex].getTotal())
				{
					if(countryArray[j].getGold() < countryArray[lowerIndex].getGold())
					{
						lowerIndex = j;
					}
				}	
			}
			Country temp = countryArray[i];
			countryArray[i] = countryArray[lowerIndex];
			countryArray[lowerIndex] = temp;
		}	
	}
	
	public String toString()
	{
		String qwer = " ";
		for (int i = 0;i <counter;i++)
		{
			qwer = countryArray[i].toString() + "\n" + qwer;
		}
			return qwer; 
	}
}
/*
	Student name Junqi Li
	Student No 16554835
	Assignment for CSE1OOF
*/