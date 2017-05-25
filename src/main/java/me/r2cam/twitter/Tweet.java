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

	private final String RUTAFFMPEG = "/usr/local/bin/ffmpeg";

	// "/usr/bin/ffmpeg"
	private final String RUTARTMP = "rtmp://localhost/adictos/tutorial";

	private final String RUTAFOTO = "/Users/ayrton.risco/Documents/pruebasTweet/captura.jpg";
	// /root/imagenTemporal/captura.jpg

	private final String HASHTAG = " #R2Cam";

	private final CharSequence conRefused = "(Connection refused)";

	private boolean hayConexion = true;

	public String realizarTweet(String mensaje) {
		generarFoto();
		String respuesta = "Fallo";
		/*
		 * File foto = new File(RUTAFOTO); Twitter twitter = TwitterFactory.getSingleton(); StatusUpdate status = new
		 * StatusUpdate(mensaje + HASHTAG); status.setMedia(foto); try { twitter.updateStatus(status); respuesta = "OK"; }
		 * catch (TwitterException e) { e.printStackTrace(); }
		 */
		return respuesta;
	}

	private void generarFoto() {
		String s = "";
		// boolean hayConxion = true;
		try {
			System.out.println("generarFoto");
			String[] terminal = { RUTAFFMPEG, "-y", "-i", RUTARTMP, "-vframes", "1", RUTAFOTO };
			Process p = Runtime.getRuntime().exec(terminal);
			// BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// Leemos la salida del comando
			System.out.println("Salida Standard:\n");
			/*
			 * while ((s = stdInput.readLine()) != null) { System.out.println("Entra Standar"); System.out.println(s); }
			 */

			// System.out.println("Salida de S: "+s.length()+"\nY: "+s.isEmpty());

			// Leemos los errores si los hubiera
			System.out.println("Salida de Error(si la hay):\n");
			while ((s = stdError.readLine()) != null && hayConexion) {
				System.out.println(s);
				/*if (s.contains(conRefused)) {
					System.out.println("un break?");
					break;
				}*/
				comprobarConexion(s);
			}
			System.out.println("FinGenerarFoto");
		} catch (IOException ioe) {
			System.out.println(ioe);
		}finally {
			hayConexion = true;
		}
	}

	private void comprobarConexion(String salidaConsola) {
		if (salidaConsola.contains(conRefused)) {
			hayConexion = false;
		}
	}

}
