import java.util.Iterator;


public class Species {

	private String prefix;
	private int domains;
	private int amount;
	
	public Species(String _prefix, int _domains, int _amount)
	{
		prefix = _prefix;
		domains = _domains;
		amount = _amount;
	}
	
	public String toDSD(int _end)
	{
		if (amount == 0)
			return "";
		
		String toReturn = "species(" + amount + ",";
		
		for (int i = 1 ; i <= domains ; i++)
		{
			if (i == domains) toReturn += prefix + Integer.toString(i);
			else toReturn += prefix + Integer.toString(i) + ",";
		}
		
		if (_end != 1)
		{
			toReturn += ")\n| ";
		}
		else
		{
			toReturn += ")\n";
		}
		
		return toReturn;
	}
	
}
