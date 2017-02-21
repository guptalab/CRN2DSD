
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class XMLReader
{
	static final String LIST_OF_REACTIONS = "listOfReactions";
	static final String REACTION = "reaction";
	static final String SPECIES = "species";
	static final String LIST_OF_REACTANTS = "listOfReactants";
	static final String LIST_OF_PRODUCTS = "listOfProducts";
	static final String SPECIES_REFERENCE = "speciesReference";
	
	public ArrayList<Reaction> readConfig(String configFile)
	{
		ArrayList<Reaction> reactionsToReturn = new ArrayList<Reaction>();
		
		try
		{	 
			File fXmlFile = new File(configFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 		 
			NodeList nList = doc.getElementsByTagName(REACTION);
		 
			int count = 0;
		 
			for (int i = 0; i < nList.getLength(); i++)
			{
				
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Reaction reaction = new Reaction(++count, Constants.DOMAINS);
					
					Element eElement = (Element) nNode;
		 
					Node reactantsNode = eElement.getElementsByTagName(LIST_OF_REACTANTS).item(0);
					Node productsNode = eElement.getElementsByTagName(LIST_OF_PRODUCTS).item(0);

					NodeList reactantsList = reactantsNode.getChildNodes();
					
					for (int j = 0; j < reactantsList.getLength(); j++)
					{
						Node reactant = reactantsList.item(j);
						
						if (reactant.getNodeType() == Node.ELEMENT_NODE)
						{
							Element species = (Element) reactant;
							reaction.addReactant(species.getAttribute("species"));
						}
					}
					
					try
					{
						NodeList productsList = productsNode.getChildNodes();
						
						for (int j = 0; j < productsList.getLength(); j++)
						{
							Node product = productsList.item(j);
							
							if (product.getNodeType() == Node.ELEMENT_NODE)
							{
								Element species = (Element) product;
								reaction.addProduct(species.getAttribute("species"));
							}
						}
					}
					catch (Exception e)
					{
						
					}
					
					reaction.setPropensity(Float.parseFloat(eElement.getAttribute("propensity")));
					
					reactionsToReturn.add(reaction);		 
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return reactionsToReturn;
	}
	
	public ArrayList<Species> readSpecies(String configFile)
	{
		ArrayList<Species> speciesToReturn = new ArrayList<Species>();
		
		try
		{	 
			File fXmlFile = new File(configFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 		 
			NodeList nList = doc.getElementsByTagName(SPECIES);
		 
			int count = 0;
		 
			for (int i = 0; i < nList.getLength(); i++)
			{
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String id = eElement.getAttribute("id");
					int amount = Integer.parseInt(eElement.getAttribute("initialAmount"));
					
					Species s = new Species(id, Constants.DOMAINS, amount);
					
					speciesToReturn.add(s);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return speciesToReturn;
	}
}
