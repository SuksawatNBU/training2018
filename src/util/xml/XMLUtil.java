package util.xml;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;

public class XMLUtil {

	public static void objectToXML(String filePath, Object object) throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, FileUtils.getFile(filePath));
			jaxbMarshaller.marshal(object, System.out);
		} catch (Exception e) {
			throw e;
		}
	}

	public static Object xmlToObject(String filePath, Object object) throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			object = jaxbUnmarshaller.unmarshal(FileUtils.getFile(filePath));
		} catch (Exception e) {
			throw e;
		}
		return object;
	}
	
	public static String objectToXMLString(Object object) throws Exception {
		String xmlString = null;
		StringWriter sw = new StringWriter();
		try {
			Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(object, sw);
			xmlString = sw.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				sw.close();
			} catch (Exception swe) {
				
			}
		}
		return xmlString;
	}
}
