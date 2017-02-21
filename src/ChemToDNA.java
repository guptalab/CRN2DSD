import java.util.ArrayList;
import java.util.Iterator;


public class ChemToDNA
{       
	public static String perform(String filepath)
	{
        XMLReader read = new XMLReader();
	    ArrayList<Reaction> readConfig = read.readConfig(filepath);
	    ArrayList<Species> readSpecies = read.readSpecies(filepath);
	    
	    Iterator<Reaction> rIter = readConfig.iterator();
	    Iterator<Species> sIter = readSpecies.iterator();
	    
	    String inputCompounds = "( ";
	    
	    while (sIter.hasNext())
	    {
	    	Species s = sIter.next();
	    	inputCompounds += s.toDSD(0);
	    }
	    
	    while (rIter.hasNext())
	    {
	    	Reaction r = rIter.next();
	    	inputCompounds += r.toDSD(3, rIter.hasNext() ? 0 : 1);
	    }
	    
	    inputCompounds += "\n)";
	    	    
	    FunctionGenerator fg = new FunctionGenerator(Constants.DOMAINS);
	    
	    return fg.getFunctions() + inputCompounds;
	    
	}
}
