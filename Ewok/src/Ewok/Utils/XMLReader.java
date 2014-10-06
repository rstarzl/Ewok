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
	 * 검색되는 모든 테그의 값을 가져옴.
	 * @param tagName
	 * @return
	 * @throws ExceptionInvalidForm 
	 */
	public ArrayList<String> getTagValueToList(String tagName) throws ExceptionInvalidForm{
		NodeList nodeList = doc.getElementsByTagName(tagName);
		ArrayList<String> value = new ArrayList<String>();
        // tagName 파라미터와 매칭되는 노드들이 있으면, 반복문을 돌며 값을 출력한다.
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node valueNode = nodeList.item(i).getChildNodes().item(0);
            // Tag에 값을 출력하기 위해서는 Node 타입이 TextNode 혹은 CDATA_SECTION_NODE
            // 이어야 한다.
            value.add(valueNode.getNodeValue());
        }
        
        if (value.size() == 0){
        	throw new ExceptionInvalidForm(tagName);
        }
        
        return	value;
	}
	
	public String getFirstTagValue(String tagName) throws ExceptionInvalidForm{
		NodeList nodeList = doc.getElementsByTagName(tagName);
		
        // tagName 파라미터와 매칭되는 노드들이 있으면, 반복문을 돌며 값을 출력한다.
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node valueNode = nodeList.item(i).getChildNodes().item(0);
            // Tag에 값을 출력하기 위해서는 Node 타입이 TextNode 혹은 CDATA_SECTION_NODE
            // 이어야 한다.
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
