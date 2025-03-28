package eu.jasperlorelai.soundfetcher;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.SoundEventKeys;

public class SoundFetcher {

	@SuppressWarnings({"ResultOfMethodCallIgnored"})
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter file name: ");
			String version = scanner.nextLine();
			if (version == null || version.isEmpty()) return;

			File soundFile = new File(version + (version.endsWith(".txt") ? "" : ".txt"));
			soundFile.delete();
			soundFile.createNewFile();

			List<String> sounds = new ArrayList<>();
			BufferedWriter writer = new BufferedWriter(new FileWriter(soundFile));
			for (Field field : SoundEventKeys.class.getDeclaredFields()) {
				try {
					@SuppressWarnings("rawtypes")
					TypedKey key = (TypedKey) field.get(null);
					sounds.add(key.value());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			Collections.sort(sounds);
			for (String sound : sounds) {
				writer.append(sound);
				writer.newLine();
			}

			writer.close();
		} catch (IOException e) {
			//noinspection CallToPrintStackTrace
			e.printStackTrace();
		}
	}

}
