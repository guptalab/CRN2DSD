
public class FunctionGenerator
{

	private int domains;
	private String functions = "";
	public FunctionGenerator(int _domains)
	{
		domains = _domains;
		
		functions = "directive duration 100000000.0 points 500\ndirective scale 50.0\n\n";
		
		functions += genSpeciesFunction();
		
		for (int i = 0 ; i <= 4 ; i++)
		{
			functions += genUnaryFunction(i);
		}
		
		for (int i = 0 ; i <= 4 ; i++)
		{
			functions += genBinaryFunction(i);
		}
		
		for (int i = 0 ; i <= 4 ; i++)
		{
			functions += genTernaryFunction(i);
		}
	}
	
	public String genSpeciesFunction()
	{
		String toReturn = "def species(N,";
		
		toReturn += genSpecies("X", domains, 0);
		toReturn += ") = N * <" + genAlternateSequence("X", 1, domains) + ">\n\n";
		
		return toReturn;
	}
	
	public String genUnaryFunction(int _out)
	{
		String toReturn = "";
		
		switch (_out)
		{
			case 0:
				toReturn += "def unary0(R,";
				toReturn += genSpecies("A", domains, 0);
				toReturn += ") = \n";
				
				// Line 1
				toReturn += "( constant R * " + genAlternateComplimentSequence("A", 1, 1) + ":";
				toReturn += "[" + genAlternateSequence("A", 2, domains) + "]";
				
				toReturn += "\n)\n\n";
				break;
			case 1:
				toReturn += "def unary1(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("X1", domains, 0);
				toReturn += ") = \nnew I1\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains) + "]";
				toReturn += "<I1 X11^>\n";
				
				// Line 2
				toReturn += "| constant R * A" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
			case 2:
				toReturn += "def unary2(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^>\n";
				
				// Line 2
				toReturn += "| constant R * A" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
			case 3:
				toReturn += "def unary3(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 1);
				toReturn += genSpecies("X3", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\nnew I3\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^ I3 X31^>\n";
				
				// Line 2
				toReturn += "| constant R * A" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				toReturn += ":[I3 X31^]";
				toReturn += "<" + genAlternateSequence("X3", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
				
			case 4:
				toReturn += "def unary4(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 1);
				toReturn += genSpecies("X3", domains, 1);
				toReturn += genSpecies("X4", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\nnew I3\nnew I4\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^ I3 X31^ I4 X41^>\n";
				
				// Line 2
				toReturn += "| constant R * A" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				toReturn += ":[I3 X31^]";
				toReturn += "<" + genAlternateSequence("X3", 2, domains) + ">";
				toReturn += ":[I4 X41^]";
				toReturn += "<" + genAlternateSequence("X4", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
		}
		
		return toReturn;
	}

	public String genBinaryFunction(int _out)
	{
		String toReturn = "";
		
		switch (_out)
		{
			case 0:
				toReturn += "def binary0(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 0);
				toReturn += ") = \n";
				
				// Line 1
				toReturn += "( unary0(R,";
				toReturn += genSpecies("A", domains, 0) + ")\n";
				toReturn += "| unary0(R,";
				toReturn += genSpecies("B", domains, 0) + ")\n";
				
				toReturn += ")\n\n";
				break;
			case 1:
				toReturn += "def binary1(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("X1", domains, 0);
				toReturn += ") = \nnew I1\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains) + "]";
				toReturn += "<I1 X11^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 3
				toReturn += "| constant R * B" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
			case 2:
				toReturn += "def binary2(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 2
				toReturn += "| constant R * B" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
			case 3:
				toReturn += "def binary3(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 1);
				toReturn += genSpecies("X3", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\nnew I3\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^ I3 X31^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 2
				toReturn += "| constant R * B" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				toReturn += ":[I3 X31^]";
				toReturn += "<" + genAlternateSequence("X3", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
				
			case 4:
				toReturn += "def binary4(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 1);
				toReturn += genSpecies("X3", domains, 1);
				toReturn += genSpecies("X4", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\nnew I3\nnew I4\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^ I3 X31^ I4 X41^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 2
				toReturn += "| constant R * B" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				toReturn += ":[I3 X31^]";
				toReturn += "<" + genAlternateSequence("X3", 2, domains) + ">";
				toReturn += ":[I4 X41^]";
				toReturn += "<" + genAlternateSequence("X4", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
		}
		
		return toReturn;
	}

	public String genTernaryFunction(int _out)
	{
		String toReturn = "";
		
		switch (_out)
		{
			case 0:
				toReturn += "def ternary0(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("C", domains, 0);
				toReturn += ") = \n";
				
				// Line 1
				toReturn += "( unary0(R,";
				toReturn += genSpecies("A", domains, 0) + ")\n";
				toReturn += "| unary0(R,";
				toReturn += genSpecies("B", domains, 0) + ")\n";
				toReturn += "| unary0(R,";
				toReturn += genSpecies("C", domains, 0) + ")\n";
				
				toReturn += ")\n\n";
				break;
			case 1:
				toReturn += "def ternary1(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("C", domains, 1);
				toReturn += genSpecies("X1", domains, 0);
				toReturn += ") = \nnew I1\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains - 1) + " C1^]:";
				toReturn += "[" + genAlternateSequence("C", 2, domains) + "]";
				toReturn += "<I1 X11^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 3
				toReturn += "| constant 0 * <" + genAlternateSequence("B", 2, domains - 1) + " C1^>\n";
				
				// Line 4
				toReturn += "| constant R * C" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
			case 2:
				toReturn += "def ternary2(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("C", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains - 1) + " C1^]:";
				toReturn += "[" + genAlternateSequence("C", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 3
				toReturn += "| constant 0 * <" + genAlternateSequence("B", 2, domains - 1) + " C1^>\n";
				
				// Line 4
				toReturn += "| constant R * C" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
			case 3:
				toReturn += "def ternary3(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("C", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 1);
				toReturn += genSpecies("X3", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\nnew I3\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains - 1) + " C1^]:";
				toReturn += "[" + genAlternateSequence("C", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^ I3 X31^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 3
				toReturn += "| constant 0 * <" + genAlternateSequence("B", 2, domains - 1) + " C1^>\n";
				
				// Line 4
				toReturn += "| constant R * C" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				toReturn += ":[I3 X31^]";
				toReturn += "<" + genAlternateSequence("X3", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
				
			case 4:
				toReturn += "def ternary4(R,";
				toReturn += genSpecies("A", domains, 1);
				toReturn += genSpecies("B", domains, 1);
				toReturn += genSpecies("C", domains, 1);
				toReturn += genSpecies("X1", domains, 1);
				toReturn += genSpecies("X2", domains, 1);
				toReturn += genSpecies("X3", domains, 1);
				toReturn += genSpecies("X4", domains, 0);
				toReturn += ") = \nnew I1\nnew I2\nnew I3\nnew I4\n";
				
				// Line 1
				toReturn += "( constant R * A1^*:";
				toReturn += "[" + genAlternateSequence("A", 2, domains - 1) + " B1^]:";
				toReturn += "[" + genAlternateSequence("B", 2, domains - 1) + " C1^]:";
				toReturn += "[" + genAlternateSequence("C", 2, domains) + "]";
				toReturn += "<I1 X11^ I2 X21^ I3 X31^ I4 X41^>\n";
				
				// Line 2
				toReturn += "| constant 0 * <" + genAlternateSequence("A", 2, domains - 1) + " B1^>\n";
				
				// Line 3
				toReturn += "| constant 0 * <" + genAlternateSequence("B", 2, domains - 1) + " C1^>\n";
				
				// Line 4
				toReturn += "| constant R * C" + Integer.toString(domains) + "^*:[I1 X11^]";
				toReturn += "<" + genAlternateSequence("X1", 2, domains) + ">";
				toReturn += ":[I2 X21^]";
				toReturn += "<" + genAlternateSequence("X2", 2, domains) + ">";
				toReturn += ":[I3 X31^]";
				toReturn += "<" + genAlternateSequence("X3", 2, domains) + ">";
				toReturn += ":[I4 X41^]";
				toReturn += "<" + genAlternateSequence("X4", 2, domains) + ">";
				
				toReturn += "\n)\n\n";
				break;
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
	
	public String genAlternateSequence(String _prefix, int _start, int _end)
	{
		String toReturn = "";
		int i;
		for (i = _start ; i < _end ; i++)
		{
			if (i % 2 != 0)
			{
				toReturn += _prefix + Integer.toString(i) + "^ ";
			}
			else
			{
				toReturn += _prefix + Integer.toString(i) + " ";
			}
		}
		
		if (i % 2 != 0)
		{
			toReturn += _prefix + Integer.toString(i) + "^";
		}
		else
		{
			toReturn += _prefix + Integer.toString(i);
		}
		
		return toReturn;
	}
	
	public String genAlternateComplimentSequence(String _prefix, int _start, int _end)
	{
		String toReturn = "";
		int i;
		for (i = _start ; i < _end ; i++)
		{
			if (i % 2 != 0)
			{
				toReturn += _prefix + Integer.toString(i) + "^*:";
			}
			else
			{
				toReturn += _prefix + Integer.toString(i) + "*:";
			}
		}
		
		if (i % 2 != 0)
		{
			toReturn += _prefix + Integer.toString(i) + "^*";
		}
		else
		{
			toReturn += _prefix + Integer.toString(i) + "*";
		}
		
		return toReturn;
	}
	
	public String getFunctions()
	{
		return functions;
	}
}
