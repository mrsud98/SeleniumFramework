package com.iasys.utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static ConfigReader instance;

	private final Properties config = new Properties();

	private final Properties env = new Properties();

	private ConfigReader() {

		try {

			InputStream configFile = getClass().getClassLoader().getResourceAsStream("config/config.properties");

			config.load(configFile);

			String environment = System.getProperty("env", config.getProperty("env"));

			InputStream envFile = getClass().getClassLoader()
					.getResourceAsStream("config/" + environment + ".properties");

			env.load(envFile);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}
	}

	public static ConfigReader getInstance() {

		if (instance == null)

			instance = new ConfigReader();

		return instance;
	}

	public String get(String key) {

		if (env.containsKey(key))

			return env.getProperty(key);

		return config.getProperty(key);
	}
}