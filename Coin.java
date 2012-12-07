public class Coin
{
	String quality;
	String mint;
	double denomination;
	int year;


	public Coin(String q, String m, double d, int y)
	{
		quality = q;
		mint = m;
		denomination = d;
		year = y;
	}


	public String getQuality() { return quality; }
	public String getMint() { return mint; }
	public double getDenomination() { return denomination; }
	public int getYear() { return year; }

	public String toString()
	{
		return "Quality: " + quality + ", " + "Mint: " +  mint + ", " + "Value: $"
		+ denomination + ", " + "Year: " + year;
	}
	public static void main(String[] args)
	{
		Coin c = new Coin("good", "washington", .5, 1990);
		System.out.println(c);
	}
}