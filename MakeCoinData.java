import java.io.*;
import java.util.*;
import java.text.*;

public class MakeCoinData
{
	public static void main(String[] args) throws Exception
	{
		final int MAX_RECORDS = 200;
		final String NEW_LINE = "\n";
		Calendar mycal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("coinData.txt"))));

		String[] qualityArray = {"low", "medium", "high", "mint"};

		String[] mintArray = {"P", "D", "S", "W"};

		double[] dArray = {.01, .05, .10, .25, .50, 1.00};

		Random r = new Random();

		for(int i=1; i<MAX_RECORDS; i++)
		{
			int year = r.nextInt(201);
			mycal.set(year+1800, 1, 1);

			String s = qualityArray[r.nextInt(qualityArray.length)]
				+ "," + mintArray[r.nextInt(mintArray.length)]
				+ "," + dArray[r.nextInt(dArray.length)]
				+ "," + sdf.format(mycal.getTime());

			out.println(s);
		}

		out.close();
	}
}
