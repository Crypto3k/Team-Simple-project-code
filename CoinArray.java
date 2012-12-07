import java.util.*;
import java.io.File;

public class CoinArray
{
	Coin[] ca;
	int n;
	final int maxRecords = 200;

	public CoinArray()
	{
		ca = new Coin[maxRecords];
		readFile();
	}

	public void readFile()
		{
			try
			{
				Scanner sc = new Scanner(new File("coinData.txt"));
				int i = 0;

				while (sc.hasNext())
				{
					String s = sc.nextLine();
					String[] sd = s.split(",");

					ca[i] = new Coin(sd[0], sd[1], Double.parseDouble(sd[2]),
					Integer.parseInt(sd[3]));
					i++;
				}
					n = i;
			}
			catch (Exception e)
			{
				System.out.println("Exception with CoinArray readFile");
			}
		}

			//Pulls denomination off each coin and adds it to total and then returns the total
		public double totalValue()
		{
			double total = 0.0;
			for(int i=0; i<n; i++)
			{
				total += ca[i].getDenomination();
			}
			return total;
		}


			//Reads in a year entered and pulls all coins in that year, adds their denominations to total and then returns total.
		public double yearValue(int year)
		{
			double total = 0.0;
			for(int i=0; i<n; i++)
			{
				if(ca[i].getYear() == year)
				total += ca[i].getDenomination();
			}
			return total;
		}



		public void sortByQuality()
		{

			Arrays.sort(ca, 0, n, new SortByQuality());
		}

		public void sortByMint()
		{
			Arrays.sort(ca, 0, n, new SortByMint());
		}

		public void sortByDenomination()
		{
			Arrays.sort(ca, 0, n, new SortByDenomination());
		}

		public void sortByYear()
		{
			Arrays.sort(ca, 0, n, new SortByYear());
		}

		public String toString()
		{
			String s = "";

			for (int i=0; i<n; i++)
			s += ca[i] + "\n";

			return s;
		}

		class SortByQuality implements Comparator<Coin>
		{
			public int compare(Coin s1, Coin s2)
			{
				return s1.getQuality().compareTo(s2.getQuality());
			}
		}

		class SortByMint implements Comparator<Coin>
		{
			public int compare(Coin s1, Coin s2)
			{
				return s1.getMint().compareTo(s2.getMint());
			}
		}

		class SortByDenomination implements Comparator<Coin>
			{
				public int compare(Coin s1, Coin s2)
				{
					if (s1.getDenomination() < s2.getDenomination()) return -1;
					if (s1.getDenomination() > s2.getDenomination()) return 1;
					return 0;
				}
			}

		class SortByYear implements Comparator<Coin>
			{
				public int compare(Coin s1, Coin s2)
				{
					if (s1.getYear() < s2.getYear()) return -1;
					if (s1.getYear() > s2.getYear()) return 1;
					return 0;
				}
			}
	}