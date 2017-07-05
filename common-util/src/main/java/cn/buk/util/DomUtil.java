package cn.buk.util;

import org.dom4j.Element;
import org.dom4j.Node;

public class DomUtil {
	public static String getNodeText(Node node, String nodeName) {
		if (node.selectSingleNode(nodeName)==null) return "";
		else return node.selectSingleNode(nodeName).getText();
	}
	
	public static String getElementAttribute(Element e, String attrName) {
		 if (e.attribute(attrName) == null) return "";
		 else return e.attributeValue(attrName);
	}
	
	public static String getNodeAttr(Node node, String attrName) {
		Element e = (Element)node;
		 if (e.attribute(attrName) == null) return "";
		 else return e.attributeValue(attrName);
	}
}
