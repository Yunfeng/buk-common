package cn.buk.util;

import org.dom4j.io.DocumentResult;

import javax.xml.bind.*;
import java.io.StringReader;

public class XmlUtil {
	public static String convertToXml(Object obj, Class... c) {

		String result = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(c);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
			m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");
//            m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "");

			DocumentResult documentResult = new DocumentResult();
			m.marshal(obj, documentResult);

			result = documentResult.getDocument().asXML();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static <T>T convertXmlTo(String xml, Class<T> c) {
		T response = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(c);
			Unmarshaller m = jc.createUnmarshaller();
			Object obj = m.unmarshal(new StringReader(xml));

			if (obj.getClass() == JAXBElement.class) obj = ((JAXBElement)obj).getValue();

			response= (T) obj;

		} catch (JAXBException e) {
			System.out.println(e.getMessage());
			System.out.println(xml);
		}

		return response;
	}
}
