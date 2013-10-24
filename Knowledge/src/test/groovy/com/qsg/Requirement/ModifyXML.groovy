package test.groovy.com.qsg.Requirement
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import test.groovy.com.qsg.Shared.ModifyXMLFunction;

public class ModifyXML {

	public static void main(String[] args) {

		def filePath = "D:/E14_09.xml";
		def xmlFile = new File(filePath);
		def dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		def attrName = "xsi:nil";
		def attrValue = "true";

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			def xmlModify = new ModifyXMLFunction();

			// delete element
			xmlModify.deleteElement(doc);
			xmlModify.updateElement(doc, attrName, attrValue);

			// write the updated document to file
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					"D:/E14_09_updated.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("XML file updated successfully");

		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (TransformerException e1) {
			e1.printStackTrace();
		}
	}

}
