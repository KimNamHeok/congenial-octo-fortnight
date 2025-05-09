package kr.or.ddit.props;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertiestTest {
	
	@Test
	void test() throws IOException {
		String qualifiedName = "/kr/or/ddit/mbit/Mbti.properties";
		InputStream is = PropertiestTest.class.getResourceAsStream(qualifiedName);
		Reader reader = new InputStreamReader(is, "UTF-8");
		Properties mbtiProps = new Properties();
		mbtiProps.load(reader);
		System.out.println(mbtiProps.size());
		mbtiProps.forEach((n,v)->System.out.printf("%s : %s\n", n, v));
	}
}
