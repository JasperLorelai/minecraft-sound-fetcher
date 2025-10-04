package eu.jasperlorelai.soundfetcher;

import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Collections;
import java.lang.reflect.Field;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.SoundEventKeys;

public class SoundFetcher {

	public static void main(String[] args) {
		try (var stream = SoundFetcher.class.getClassLoader().getResourceAsStream("config.properties")) {
			Properties properties = new Properties();
			properties.load(stream);
			String mcVersion = properties.getProperty("mcVersion");

			List<String> sounds = new ArrayList<>();
			for (Field field : SoundEventKeys.class.getDeclaredFields()) {
				@SuppressWarnings("rawtypes")
				TypedKey key = (TypedKey) field.get(null);
				sounds.add(key.value());
			}

			Collections.sort(sounds);
			Files.write(Paths.get(mcVersion + ".txt"), sounds);
		} catch (Exception e) {
			//noinspection CallToPrintStackTrace
			e.printStackTrace();
		}
	}

}
