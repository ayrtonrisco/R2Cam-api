package me.r2cam.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Tweet {

	private final String RUTAFFMPEG = "/usr/bin/ffmpeg";

	private final String RUTARTMP = "rtmp://localhost/live/directo";

	private final String RUTAFOTO = "/root/imagenTemporal/captura.jpg";

	private final String HASHTAG = " #R2Cam";

	private final CharSequence conInvalidData = "Invalid data found when processing";

	private final String OK = "OK";

	private final String FAIL = "FAIL";

	public String realizarTweet(String nomDispositivo) {
		boolean hayConex = generarFoto();
		String respuesta = FAIL;
		if (hayConex) {
			respuesta = subirTweet(nomDispositivo);
		}
		return respuesta;
	}

	private String subirTweet(String nomDispositivo) {
		String respuesta = FAIL;
		File foto = new File(RUTAFOTO);
		Twitter twitter = TwitterFactory.getSingleton();
		StatusUpdate status = new StatusUpdate(nomDispositivo + HASHTAG);
		status.setMedia(foto);
		try {
			twitter.updateStatus(status);
			respuesta = OK;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	private boolean generarFoto() {
		String s = "";
		boolean hayConexion = true;
		try {
			String[] terminal = { RUTAFFMPEG, "-y", "-i", RUTARTMP, "-vframes", "1", RUTAFOTO };
			Process p = Runtime.getRuntime().exec(terminal);
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			// Leemos los errores si los hubiera
			while (hayConexion && (s = stdError.readLine()) != null) {
				hayConexion = comprobarConexion(s);
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		return hayConexion;
	}

	private boolean comprobarConexion(String salidaConsola) {
		if (salidaConsola.contains(conInvalidData)) {
			return false;
		}
		return true;
	}

}
