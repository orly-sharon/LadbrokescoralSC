package com.sharon.orly.ladbrokescoral;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetSoundCloud implements Runnable {

	private static final Logger log = LogManager.getLogger(GetSoundCloud.class);
	private URL url;
	private List<SoundCloud> soundCloudList = new ArrayList<SoundCloud>();

	public GetSoundCloud(String url) throws MalformedURLException {
		this.url = new URL(url);

	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public List<SoundCloud> getSoundCloudList() {
		return soundCloudList;
	}

	public void setSoundCloudList(List<SoundCloud> soundCloudList) {
		this.soundCloudList = soundCloudList;
	}

	public String printSoundCloudList() {
		StringBuffer str = new StringBuffer();
		for (SoundCloud soundCloud : getSoundCloudList()) {
			str.append(soundCloud.toString());
		}
		return str.toString();
	}

	@Override
	public String toString() {
		return "GetSoundCloud [url=" + url + ", soundCloudList=" + getSoundCloudList().toString() + "]";
	}

	public void run() {

		try {

			HttpURLConnection connection = (HttpURLConnection) getUrl().openConnection();
			connection.setRequestMethod("GET");
			log.debug("Reqest set to GET");
			connection.setDoOutput(true);

			int responseCode = connection.getResponseCode();

			log.debug("Response code from server is:" + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				log.debug("Response code from server is ok or created");
				String inputLine;
				int numberOfResult = 10;
				log.debug("Number of resluts from server is:" + numberOfResult);
				JSONParser parser = new JSONParser();

				URLConnection urlConnection = getUrl().openConnection();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

				while ((inputLine = bufferedReader.readLine()) != null) {
					JSONArray jsonArray = (JSONArray) parser.parse(inputLine);

					for (int i = 0; i < numberOfResult; i++) {
						Object object = jsonArray.get(i);
						SoundCloud soundCloud = new SoundCloud();
						JSONObject jsonObject = (JSONObject) object;

						soundCloud.setID((Long) jsonObject.get("id"));
						soundCloud.setPermalinkURL((String) jsonObject.get("permalink_url"));
						soundCloud.setTitle((String) jsonObject.get("title"));
						soundCloud.setUser((Long) jsonObject.get("user_id"));
						log.debug("Result: "+ soundCloud.toString());

						getSoundCloudList().add(soundCloud);
						log.debug("soundCloud was add to list");
						
					}

				}

				bufferedReader.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
