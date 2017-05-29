import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class AlphaBeta {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("What phrase would you like to decrypt? ");
		String encrypted = sc.next();
		System.out.print("What alpha code would you like to use? ");
		String alpha = sc.next();
		System.out.print("What beta code would you like to use? ");
		String beta = sc.next();
		String postReverseAlpha = reverseAlpha(encrypted, alpha);
		String postReverseBeta = reverseBeta(encrypted, beta);
		//System.out.println(postReverseAlpha);
		//System.out.println(postReverseBeta);
		doAlphaBeta(encrypted, alpha, beta);
	}
	
	public static void doAlphaBeta(String encrypted, String alpha, String beta){
		for (int i = 0; i < 10; i++){
			encrypted = reverseBeta(encrypted, beta);
			encrypted = reverseAlpha(encrypted, alpha);
		}
		System.out.println(encrypted);
	}
	
	public static String reverseBeta(String encrypted, String betaCode){
		int betaIndex = 0;
		int encryptedIndex = 0;
		StringBuilder newString = new StringBuilder(encrypted);
		while (encryptedIndex < encrypted.length()){
			int newAscii = encrypted.charAt(encryptedIndex) - betaCode.charAt(betaIndex) + 65;
			if (newAscii < 65){
				newAscii = 26 + newAscii;
			}
			char newChar = (char) newAscii;
			newString.setCharAt(encryptedIndex, newChar);
			encryptedIndex++;
			betaIndex++;
			if (betaIndex == betaCode.length()){
				betaIndex = 0;
			}
		}
		return newString.toString();
	}
	
	public static String reverseAlpha(String encrypted, String alphaCode){
		int alphaIndex = (encrypted.length() - 1) % alphaCode.length();
		int encryptedIndex = encrypted.length() - 1;
		StringBuilder newString = new StringBuilder(encrypted);
		while (encryptedIndex >= 0){
			char newChar = newString.toString().charAt((encryptedIndex + (alphaCode.charAt(alphaIndex) - 64)) % encrypted.length());
			char oldChar = newString.toString().charAt(encryptedIndex);
			newString.setCharAt((encryptedIndex + (alphaCode.charAt(alphaIndex) - 64)) % encrypted.length(), oldChar);
			newString.setCharAt(encryptedIndex, newChar);
			encryptedIndex--;
			alphaIndex--;
			if (alphaIndex == -1){
				alphaIndex = alphaCode.length() - 1;
			}
		}
		return newString.toString();
	}
}
