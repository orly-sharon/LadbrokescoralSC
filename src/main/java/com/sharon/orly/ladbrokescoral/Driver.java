package com.sharon.orly.ladbrokescoral;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {

	static Logger LOG = LogManager.getLogger(Driver.class);

	public void start() throws IOException {
		List<String> soundCloudUrlList = setUrlList();
		HashMap<String, GetSoundCloud> soundCloudCollection = new HashMap<String, GetSoundCloud>();
		runRequest(soundCloudCollection, soundCloudUrlList);
		sendToLog(soundCloudCollection);
		removeById(soundCloudCollection, 85609752L);
		sendToLog(soundCloudCollection);
	}

	private void sendToLog(HashMap<String, GetSoundCloud> soundCloudCollection) {
		for (String key : soundCloudCollection.keySet()) {
			LOG.debug(String.format("Result of collection key:%s , value: %s ", key,
					soundCloudCollection.get(key).printSoundCloudList()));
		}
	}

	private void removeById(HashMap<String, GetSoundCloud> soundCloudCollection, Long id) {
		for (String key : soundCloudCollection.keySet()) {
			List<SoundCloud> soundCloudList = soundCloudCollection.get(key).getSoundCloudList();
			for (SoundCloud soundCloud : soundCloudList) {
				if (soundCloud.getID() == id) {
					soundCloudCollection.get(key).getSoundCloudList().remove(soundCloud);
				}
			}
		}
	}

	private void removeByURL(HashMap<String, GetSoundCloud> soundCloudCollection, String url) {
		for (String key : soundCloudCollection.keySet()) {
			List<SoundCloud> soundCloudList = soundCloudCollection.get(key).getSoundCloudList();
			for (SoundCloud soundCloud : soundCloudList) {
				if (soundCloud.getPermalinkURL() == url) {
					soundCloudCollection.get(key).getSoundCloudList().remove(soundCloud);
				}
			}
		}
	}

	private List<String> setUrlList() throws IOException {

		String bandName = "The_Corrs";
		String bandName1 = "savage_garden";
		String bandName2 = "Lady_Antebellum";

		CreateConnection createConnection = new CreateConnection();
		LOG.debug("Connection made");
		List<String> soundCloudUrlList = new ArrayList<String>();
		String url = createConnection.BuiledUrl(bandName);
		LOG.debug("band Name" + bandName);
		String url1 = createConnection.BuiledUrl(bandName1);
		LOG.debug("band Name" + bandName1);
		String url2 = createConnection.BuiledUrl(bandName2);
		LOG.debug("band Name" + bandName2);
		soundCloudUrlList.add(url);
		soundCloudUrlList.add(url1);
		soundCloudUrlList.add(url2);
		return soundCloudUrlList;
	}

	private void runRequest(HashMap<String, GetSoundCloud> soundCloudCollection, List<String> soundCloudUrlList)
			throws MalformedURLException {
		ExecutorService executor = Executors.newCachedThreadPool();

		for (String urllist : soundCloudUrlList) {

			GetSoundCloud getSoundCloud = new GetSoundCloud(urllist);
			soundCloudCollection.put(urllist, getSoundCloud);
			executor.execute(getSoundCloud);
		}
		executor.shutdown();
	}

}
