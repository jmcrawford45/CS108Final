package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PictureResponseQuestionTest {

	@Before
	public void setUp() throws Exception {
		PictureResponseQuestion pic = new PictureResponseQuestion("What is this image?", "duck", "http://pngimg.com/upload/duck_PNG5011.png");
		System.out.println(pic.returnHTMLSingleQuestion());
	}

	@Test
	public void test() {
		
	}

}
