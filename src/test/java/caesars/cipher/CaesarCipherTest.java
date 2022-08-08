package caesars.cipher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaesarCipherTest {

	private CaesarsCipher caesarCipher = new CaesarsCipher();
	
	@Test
	void testCipheredMessageWithOffsetOf1() {
		assertEquals("jo dpef xf usvtu", caesarCipher.cipher("in code we trust", 1));
	}

	@Test
	void testEmptyString() {
		assertEquals("", caesarCipher.cipher("", 12));
	}

	
}
