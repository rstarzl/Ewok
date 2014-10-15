package Ewok.Utils;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	private Document doc;
	
	public XMLReader(String path){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(path);
        } catch (ParserConfigurationException e) {
        	e.printStackTrace();
        } catch (SAXException e){
        	e.printStackTrace();
        } catch (IOException e){
        	e.printStackTrace();
        }
	}
	

	/**
	 * �˻��Ǵ� ��� �ױ��� ���� ������.
	 * @param tagName
	 * @return
	 * @throws ExceptionInvalidForm 
	 */
	public ArrayList<String> getTagValueToList(String tagName) throws ExceptionInvalidForm{
		NodeList nodeList = doc.getElementsByTagName(tagName);
		ArrayList<String> value = new ArrayList<String>();
        // tagName �Ķ���Ϳ� ��Ī�Ǵ� ������ ������, �ݺ����� ���� ���� ����Ѵ�.
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node valueNode = nodeList.item(i).getChildNodes().item(0);
            // Tag�� ���� ����ϱ� ���ؼ��� Node Ÿ���� TextNode Ȥ�� CDATA_SECTION_NODE
            // �̾�� �Ѵ�.
            value.add(valueNode.getNodeValue());
        }
        
        if (value.size() == 0){
        	throw new ExceptionInvalidForm(tagName);
        }
        
        return	value;
	}
	
	public String getFirstTagValue(String tagName) throws ExceptionInvalidForm{
		NodeList nodeList = doc.getElementsByTagName(tagName);
		
        // tagName �Ķ���Ϳ� ��Ī�Ǵ� ������ ������, �ݺ����� ���� ���� ����Ѵ�.
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node valueNode = nodeList.item(i).getChildNodes().item(0);
            // Tag�� ���� ����ϱ� ���ؼ��� Node Ÿ���� TextNode Ȥ�� CDATA_SECTION_NODE
            // �̾�� �Ѵ�.
            return	valueNode.getNodeValue();
        }
        
        throw new ExceptionInvalidForm(tagName);
	}
	
	public String getAttribute(String tagName, String attributeName) throws ExceptionInvalidForm{
		NodeList nodeList = doc.getElementsByTagName(tagName);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			NamedNodeMap attrMap = nodeList.item(i).getAttributes();
			for (int j = 0; j < attrMap.getLength(); j++) {
				Node node = attrMap.item(j);
				if (node.getNodeName().equals(attributeName)){
					return	node.getNodeValue();	
				}
			}
		}
		
		throw new ExceptionInvalidForm(tagName);
	}
}
