import java.util.ArrayList;
import java.util.Iterator;


public class Reaction
{

	private ArrayList<String> reactants;
	private ArrayList<String> products;
	private float propensity = 10000;
	private int index;
	public int domains;
	
	public Reaction(int _index, int _domains)
	{
		index = _index;
		domains = _domains;
		reactants = new ArrayList<String>();
		products = new ArrayList<String>();
	}
	
	public void addReactant(String reactant)
	{
		reactants.add(reactant);
	}
	
	public void addProduct(String product)
	{
		products.add(product);
	}
	
	public void setPropensity(float _num)
	{      
        propensity = _num;
	}
	
	public String toString()
	{
		String reactionString = "";
		
		Iterator<String> rIterator = reactants.iterator();
		
		while (rIterator.hasNext())
		{
			reactionString += (rIterator.next());
			if (rIterator.hasNext()) reactionString += " + ";
		}
		
		reactionString += " --> ";
				
		Iterator<String> pIterator = products.iterator();
		
		while (pIterator.hasNext())
		{
			reactionString += (pIterator.next());
			if (pIterator.hasNext()) reactionString += " + ";
		}
		
		return reactionString;
	}
	
	public String getSpecies()
	{
		String toReturn = "";
		
		Iterator<String> rIterator = reactants.iterator();
		
		while (rIterator.hasNext())
		{
			String prefix = rIterator.next();
			
			if (rIterator.hasNext()) toReturn += "species(" + genSpecies(prefix, domains, 1) + ")\n|";
			else toReturn += "species(" + genSpecies(prefix, domains, 0) + ")\n";
		}
		
		return toReturn;
	}
	
	public String getReactants()
	{
		String toReturn = "";
		
		Iterator<String> rIterator = reactants.iterator();
		
		while (rIterator.hasNext())
		{
			String prefix = rIterator.next();
			
			if (rIterator.hasNext()) toReturn += genSpecies(prefix, domains, 1);
			else toReturn += genSpecies(prefix, domains, 0);
		}
		
		return toReturn;
	}
	
	public String getProducts()
	{
		String toReturn = "";
		
		Iterator<String> pIterator = products.iterator();
		
		while (pIterator.hasNext())
		{
			String prefix = pIterator.next();
			
			if (pIterator.hasNext()) toReturn += genSpecies(prefix, domains, 1);
			else toReturn += genSpecies(prefix, domains, 0);
		}
		
		return toReturn;
	}
	
	public String genSpecies(String _prefix, int _domains, int _comma)
	{
		String toReturn = "";
		int i = 1;
		
		for ( ; i < _domains ; i++)
		{
			toReturn += _prefix + Integer.toString(i) + ",";
		}
		
		if (_comma == 1)
		{
			toReturn += _prefix + Integer.toString(i) + ",";
		}
		else
		{
			toReturn += _prefix + Integer.toString(i);
		}
		
		return toReturn;
	}
	
	public String toDSD(int _domains, int _end)
	{
		String funcName = "";
		int flag = 0;
		
		switch(reactants.size())
		{
			case 0: // ?
				flag = 1;
				break;
				
			case 1: // Unary
				funcName += "unary";
				break;
				
			case 2: // Binary
				funcName += "binary";
				break;
				
			case 3: // Ternary
				funcName += "ternary";
				break;
		}
		
		if (flag != 1) // unary binary ternary etc.
		{
			funcName += Integer.toString(products.size());
			
			funcName += "("; // Let us begin!
			
            funcName += Float.toString(propensity)+",";
			
            funcName += getReactants();
			
			if (products.size() > 0) funcName += ",";
			
			funcName += getProducts();
			
			funcName += ")";
			
            if (reactants.size() == 0)
            {
                funcName += "\n| ";

                funcName += getSpecies();

                if (_end != 1) funcName += "| ";
            }
            else
            {
                if (_end != 1) funcName += "\n| ";
            }
                        
			
		}
		else
		{
			funcName += getSpecies();
		}	
				
		return funcName;
	}
	
}
