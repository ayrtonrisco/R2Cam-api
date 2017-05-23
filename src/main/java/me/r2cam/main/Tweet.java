package me.r2cam.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Tweet {

	private final String RUTAFOTO = "/Users/ayrton.risco/Documents/pruebasTweet/captura.jpg";

	private final String HASHTAG = " #R2Cam";

	public String realizarTweet(String mensaje) {
		generarFoto();
		String respuesta = "Fallo";
		File foto = new File(RUTAFOTO);
		Twitter twitter = TwitterFactory.getSingleton();
		StatusUpdate status = new StatusUpdate(mensaje + HASHTAG);
		status.setMedia(foto);
		try {
			twitter.updateStatus(status);
			respuesta = "OK";
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	private void generarFoto() {
		String s = null;
		try {
			String[] terminal = { "/usr/bin/ffmpeg", "-y", "-i", "rtmp://localhost/live/directo", "-vframes",
					"1", "/root/imagenTemporal/captura.jpg" };
			Process p = Runtime.getRuntime().exec(terminal);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// Leemos la salida del comando
			System.out.println("Salida Standard:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// Leemos los errores si los hubiera
			System.out.println("Salida de Error(si la hay):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

}
