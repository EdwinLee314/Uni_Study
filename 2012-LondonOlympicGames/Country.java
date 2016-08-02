/*
	Student name Junqi Li
	Student No 16554835
	Assignment for CSE1OOF
*/
import java.util.*;
public class Country
{
	private String name;
	private int gold;
	private int silver;
	private int bronze;
	private int total;

	public Country(String name, int gold, int silver, int bronze)
	{
		this.name = name;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		total = gold + silver + bronze;
	}
	
	public Country(String name)
	{
		this.name = name;
		gold = 0;
		silver = 0;
		bronze = 0;
		total = 0;
	}
	
	public Country(String name, int gold, int silver, int bronze, int total)
	{
		this.name = name;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.total = total;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public int getSilver()
	{
		return silver;
	}
	
	public int getBronze()
	{
		return bronze;
	}	
	
	public int getTotal()
	{
		return total;
	}	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setGold(int gold)
	{
		if (gold < 0)
		{
			this.gold = 0;
		}
		else
		{
			this.gold = gold;
		}	
	}
	
	public void setSilver(int silver)
	{
		if (silver < 0)
		{
			this.silver = 0;
		}
		else
		{
			this.silver = silver;
		}	
	}
	
	public void setBronze(int bronze)
	{
		if (bronze < 0)
		{
			this.bronze = 0;
		}
		else
		{
			this.bronze = bronze;
		}	
	}
	
	public void addGold(int g)
	{
		if (g < 0)
		{
			this.gold = 0;
		}
		else
		{
			this.gold = this.gold + g;
		}	
	}
	
	public void addSilver(int s)
	{
		if (s < 0)
		{
			this.silver = 0;
		}
		else
		{
			this.silver = this.silver + s;
		}	
	}
	
	public void addBronze(int b)
	{
		if (b < 0)
		{
			this.bronze = 0;
		}
		else
		{
			this.bronze = this.bronze + b;
		}	
	}
	
	public void addTotal()
	{
		this.total = gold + silver + bronze;
	}
	
	public String toString()
	{
		String toString = name + " " + gold + " " + silver + " " + bronze + " " + total;
		return toString;
	}
}
/*
	Student name Junqi Li
	Student No 16554835
	Assignment for CSE1OOF
*/