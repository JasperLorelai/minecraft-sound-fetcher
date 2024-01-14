package eu.jasperlorelai.soundfetcher;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class SoundFetcher {

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter file name: ");
			String version = scanner.nextLine();
			if (version == null || version.isEmpty()) return;

			File sounds = new File(version + (version.endsWith(".txt") ? "" : ".txt"));
			sounds.delete();
			sounds.createNewFile();

			BufferedWriter writer = new BufferedWriter(new FileWriter(sounds));
			for (Sound sound : Sound.values()) {
				writer.append(sound.getKey().value());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
