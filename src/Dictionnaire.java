import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dictionnaire {
	
	private static String dicPath;
	
	public static boolean find(String mot) {
		Path p = Paths.get(dicPath);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (mot.equals(line)) {
					System.out.println(line);
					return true;
				}
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return false;
	}
	
	
	public static String choose() {
		Path p = Paths.get(dicPath);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
			String line = null;
			int taille =0;
			while ((line = reader.readLine()) != null) {
				taille++;
			}
			int l = (int) (Math.random()*(taille-1));
			int count=0;
			BufferedReader reader1 = Files.newBufferedReader(p, charset);
			while ((line = reader1.readLine()) != null) {
				if (count == l) {
					return line;
				}
				count++;
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return "";
	}
	
	public static String evaluer(String recherche, String proposé) {
		String barre = "";
		if (proposé.length() == recherche.length()) {
			for (int i = 0; i < proposé.length(); i++) {
				if (proposé.charAt(i) == recherche.charAt(i)) {
					barre += "o";
				}
				if (proposé.charAt(i) != recherche.charAt(i) && recherche.indexOf(proposé.charAt(i)) != -1) {
					barre += "-";
				}
				if (recherche.indexOf(proposé.charAt(i)) == -1) {
					barre += "x";
				}
			}
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Le mot n'est pas de la bonne taille, réessayez:");
			String proposition = sc.nextLine();
			barre = evaluer(recherche, proposition);
		}
		
		return barre;
	}
	
	
	public static void main(String[] args) {
		dicPath = "/Users/Guillaume/Documents/Dev/Java/TP8/src/dico.txt";
		//find("test");
		Scanner sc = new Scanner(System.in);
		String mot =choose();
		int lettres = mot.length();
		char plettre = mot.charAt(0);
		System.out.println("Le mot a "+lettres+" lettres et commence par "+plettre+".");
		System.out.println("Entrez une proposition:");
		String proposition = sc.nextLine();
		System.out.println(evaluer(mot, proposition));
	}
}
