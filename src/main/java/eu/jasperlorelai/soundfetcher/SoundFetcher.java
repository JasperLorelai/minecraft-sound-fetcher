package eu.jasperlorelai.soundfetcher;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;

import net.minecraft.SharedConstants;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.SoundEventKeys;

public class SoundFetcher {

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Path soundFile = Paths.get(SharedConstants.VERSION_STRING + ".txt");

		List<String> sounds = new ArrayList<>();
		try {
			for (Field field : SoundEventKeys.class.getDeclaredFields()) {
				@SuppressWarnings("rawtypes")
				TypedKey key = (TypedKey) field.get(null);
				sounds.add(key.value());
			}

			Collections.sort(sounds);
			Files.write(soundFile, sounds);
		} catch (Exception e) {
			//noinspection CallToPrintStackTrace
			e.printStackTrace();
		}
	}

}
