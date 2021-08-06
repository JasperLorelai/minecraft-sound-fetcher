package com.jasperlorelai.soundfetcher;

import org.bukkit.Sound;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SoundFetcher {

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter file name: ");
			String version = scanner.nextLine();
			if (version == null || version.isEmpty()) return;

			File sounds = new File(version + ".txt");
			sounds.delete();
			sounds.createNewFile();

			FileWriter writer = new FileWriter(sounds);
			for (Sound sound : Sound.values()) {
				writer.append(sound.getKey().value()).append("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
