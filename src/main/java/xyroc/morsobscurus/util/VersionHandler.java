package xyroc.morsobscurus.util;

import java.net.URL;
import java.util.Scanner;

import org.apache.logging.log4j.Level;

import xyroc.morsobscurus.MorsObscurus;

public class VersionHandler {

	public static final String adress = "https://onedrive.live.com/download?cid=38549A55717AFBAB&resid=38549A55717AFBAB%21107&authkey=AOaaqYa8yOBINhY";
	private static String id = "0";
	private static String desc = "";

	public static void checkForUpdates() {
		new Thread("Mors Obscurus Version Verification") {
			public void run() {
				try {
					URL url = new URL(adress);
					Scanner scanner = new Scanner(url.openStream());
					boolean running = true;
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						if (line == null) {
							running = false;
							break;
						}
						String[] args = line.split("=");
						String name = args[0];
						if (name.equals("versionID"))
							id = args[1];
						if (name.equals("desc"))
							desc = args[1];
					}
					scanner.close();
					if (isNewVersion(id)) {
						MorsObscurus.logger.log(Level.INFO, "Mod version verified: There is a new version available: '" + desc
								+ "'. Be sure to create a backup before updating any mods!");
					} else {
						MorsObscurus.logger.log(Level.INFO, "Mod version verified: already up to date.");
					}
				} catch (Exception e) {
					MorsObscurus.logger.log(Level.ERROR, "Failed to verify the mod version:" + e.toString());
					e.printStackTrace();
				}
			}
		}.start();
	}

	private static boolean isNewVersion(String id) {
		int i = Integer.parseInt(id);
		if (i > MorsObscurus.versionId)
			return true;
		return false;
	}

}
