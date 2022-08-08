package caesars.cipher;

public class Main {

	public static void main(String[] args) {
		
		String message = "in code we trust";
		int offset = 1;
		
		CaesarsCipher caesarsCipher = new CaesarsCipher();
		String cipheredMessage = caesarsCipher.cipher(message, offset);
		
		System.out.println("Message: " + message);
		System.out.println("Offset: " + offset);
		System.out.println("Ciphered message: " + cipheredMessage);
	}

}
