package dashboard;

import java.util.Random;

public class CustomID {
	private long id;
	
	public CustomID() {
		id = new Random().nextLong();
	}
	
	public String toString(){
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
		StringBuilder s = new StringBuilder();
		s.append(characters.charAt((int)(id & 0x0F))); //first char will always be A-P
		for (int i = 4; i < 64; i += 6) {
			s.append(characters.charAt((int)((id & (0x3FL << i)) >>> i)));
		}
		return s.toString();
	}
}
