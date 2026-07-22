package com.iasys.driver;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.iasys.utilities.ConfigReader;

public enum BrowserFactory {

	CHROME {
		@Override
		public WebDriver createDriver() {

			return new ChromeDriver(getOptionsChrome());
		}
	},
	
	EDGE{
		@Override
		public WebDriver createDriver() {

			return new EdgeDriver(getOptionsEdge());
		}
	};
		
		public ChromeOptions getOptionsChrome() {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.setAcceptInsecureCerts(true);
			options.setExperimentalOption("excludeSwitches",
			        List.of("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);
			ConfigReader config = ConfigReader.getInstance();
			if (Boolean.valueOf(config.get("headless")) == true) {

				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--high-dpi-support=1");
				options.addArguments("--force-device-scale-factor=1");

			} else {

				options.addArguments("--start-maximized");
			}

			return options;

		}
		
		public WebDriver createDriver() {
			// TODO Auto-generated method stub
			return null;
		}

		public EdgeOptions getOptionsEdge() {

			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.setAcceptInsecureCerts(true);
			options.setExperimentalOption("excludeSwitches",
			        List.of("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);
			ConfigReader config = ConfigReader.getInstance();
			if (Boolean.valueOf(config.get("headless")) == true) {

				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--high-dpi-support=1");
				options.addArguments("--force-device-scale-factor=1");

			} else {

				options.addArguments("--start-maximized");
			}

			return options;

		}
	

//	public abstract WebDriver createDriver();
//
//	public abstract MutableCapabilities getOptions();

}
