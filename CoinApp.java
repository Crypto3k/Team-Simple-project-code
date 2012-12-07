import java.util.*;

public class CoinApp
{
	CoinArray coinData;
	Scanner sc = new Scanner(System.in);

	public CoinApp()
	{
		coinData = new CoinArray();
		displayMenu();
	}

	public void displayMenu()
	{
		String choice = "-1";

		while(!choice.equals("0"))
		{
			System.out.println("0 = Exit");
			System.out.println("1 = Display All");
			System.out.println("2 = Total Coins for All Time");
			System.out.println("3 = Total Coins for Given Year");
			System.out.println("11= List Records for Customer");
			System.out.print("Enter number of choice: ");
			choice = sc.nextLine();

			if (choice.equals("1"))
				System.out.println(coinData);
			else if (choice.equals("2"))
				System.out.printf("\n $ %8.2f\n\n", coinData.totalCoinsAll());
			else if (choice.equals("3"))
			{
				System.out.print("Year for Coins Amount: ");
				int year = Integer.parseInt(sc.nextLine());
				System.out.printf("\n $ %8.2f\n\n", coinData.totalByYear(year));
			}
			else if (choice.equals("11"))
			{
				System.out.print("Enter customer first name: ");
				String fname = sc.nextLine();
				System.out.print("Enter customer last name: ");
				String lname = sc.nextLine();
				System.out.printf("\n%s\n\n",
					coinData.listCustRecords(fname, lname));
			}
		}
	}

	public static void main(String[] args)
	{
		CoinApp app = new CoinApp();
	}
}