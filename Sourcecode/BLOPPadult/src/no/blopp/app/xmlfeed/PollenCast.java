package no.blopp.app.xmlfeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import no.blopp.app.models.PollenStateAtDayModel;
import no.blopp.app.models.PollenState;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Fictional class to mimic the pollencast for a given date. 
 * @author aarseth_90
 *
 */

public class PollenCast extends XMLFeed
{
	private PollenStateAtDayModel pollenStateAtDayModel;

	public PollenCast()
	{
		super();
	}
	
	/*
	 * TODO: Need to make a class for interpreting real pollen-data when these are available 
	 */
	public void initializeDataFromXML(InputStream is)
	{
		try
		{
			
			pollenStateAtDayModel = new PollenStateAtDayModel();
			ArrayList<PollenState> pollenStateList = new ArrayList<PollenState>();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList pollenTypeList = doc.getElementsByTagName("Pollen");

			for (int i = 0; i < pollenTypeList.getLength(); i++)
			{
				Node node = pollenTypeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					String pollenName = getTagValue("Name", element);
					String distrDesc = getTagValue("Description", element);
					
					int distribution = Integer.parseInt(getTagValue(
							"Distribution", element));
					PollenState pollenState = new PollenState(pollenName,
							distribution, distrDesc);
					pollenStateList.add(pollenState);
				}
			}
			pollenStateAtDayModel.setPollenStatesAtDay(pollenStateList);
			
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private static String getTagValue(String sTag, Element eElement)
	{
		NodeList nlList =  eElement.getElementsByTagName(sTag)
				.item(0).getChildNodes();

		Node nValue = nlList.item(0);

		return nValue.getNodeValue();
	}
	public PollenStateAtDayModel getPollenStateAtDayModel()
	{
		return this.pollenStateAtDayModel;
	}
}
