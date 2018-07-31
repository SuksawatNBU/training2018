package util.encoding;

import javax.xml.bind.DatatypeConverter;

public class EncodingUtil {
	public String encoding(String str) throws Exception{
		String result = "";
        String encoded = DatatypeConverter.printBase64Binary(str.getBytes());
        result = encoded.substring(encoded.length()-2,encoded.length())+encoded.substring(encoded.length()-5,encoded.length()-2)+encoded.substring(5,encoded.length()-5)+encoded.substring(2,5) +encoded.substring(0,2);
		return result;
	}

	public String decoding(String encoded){
		String result = "";
        String decoded = encoded.substring(encoded.length()-2,encoded.length())+encoded.substring(encoded.length()-5,encoded.length()-2)+encoded.substring(5,encoded.length()-5)+encoded.substring(2,5) +encoded.substring(0,2);
        result = new String(DatatypeConverter.parseBase64Binary(decoded));
		return result;
	}
}
