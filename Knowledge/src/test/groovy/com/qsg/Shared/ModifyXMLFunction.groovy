package test.groovy.com.qsg.Shared
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModifyXMLFunction {

	static Node nextSibbling;

	public void deleteElement(Document doc) {
		NodeList eventhandler = doc.getElementsByTagName("EventHeader");
		Element ele = null;

		// loop for each event header
		for (int i = 0; i < eventhandler.getLength(); i++) {
			ele = (Element) eventhandler.item(i);
			Node genderNode = ele.getElementsByTagName("E05_01").item(0);
			nextSibbling = doc.getElementsByTagName("E05_02").item(0);
			ele.removeChild(genderNode);
		}
	}

	public void updateElement(Document doc, String attrname, String attrValue) {
		NodeList eventHeader = doc.getElementsByTagName("EventHeader");
		Element ele = null;
		
		// loop for each event header
		for (int i = 0; i < eventHeader.getLength(); i++) {
			ele = (Element) eventHeader.item(i);
			Element tagElement = doc.createElement("E05_01");
			tagElement.setAttribute(attrname, attrValue);
			ele.appendChild(tagElement);
			eventHeader.item(0).insertBefore(tagElement, nextSibbling);
		}
	}

}