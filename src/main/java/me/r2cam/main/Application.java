package me.r2cam.main;

import static spark.Spark.*;

import me.r2cam.json.JsonTransformer;
import me.r2cam.model.Motor;

public class Application {

	static Motor r2;
	static Tweet tweet;

	static final String OK = "OK";

	public static void main(String[] args) {
		short direccion = 0;
		r2 = new Motor("R2Cam", direccion);
		tweet = new Tweet();
		port(8081);
		get("/r2cam", "application/json", (req, res) -> {
			return r2;
		}, new JsonTransformer());
		put("/r2cam/direccion/", (req, res) -> {
			final short valor = Short.parseShort(req.body());
			r2.setDireccion(valor);
			return OK;
		}, new JsonTransformer());
		post("/r2cam/tweet/", (req, res) -> {
			final String movil = req.body();
			String resultado = tweet.realizarTweet(movil);
			return resultado;
		}, new JsonTransformer());
		
	}

}
