package com.cct.test;

import util.cryptography.DecryptionUtil;
import util.cryptography.EncryptionUtil;
import util.type.CrytographyType.DecType;
import util.type.CrytographyType.EncType;

public class MainTest {

	public static void main(String[] args) {
		try {
//			String result = EncryptionUtil.doEncrypt("Hello", "CCT", EncType.AES128);
//			System.out.println(result);
			
//			String result = DecryptionUtil.doDecrypt("6aBtnra8ybJtd4E0h3pFhg==", DecType.AES128);
//			System.out.println("result : " + result);
			
			String input = EncryptionUtil.doEncrypt("Hello", "cct", EncType.AES128);
			System.out.println(input);
			
			String result = DecryptionUtil.doDecrypt("6aBtnra8ybJtd4E0h3pFhg==", "cct", DecType.AES128);
			System.out.println("result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
