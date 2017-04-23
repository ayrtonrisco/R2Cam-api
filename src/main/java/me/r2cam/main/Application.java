package me.r2cam.main;

import static spark.Spark.*;

import me.r2cam.json.JsonTransformer;
import me.r2cam.model.Motor;

public class Application {

	static Motor r2;

	static String mensaje;

	public static void main(String[] args) {
		short direccion = 0;
		r2 = new Motor("R2Cam", direccion);
		port(8080);
		get("/test", (req, res) -> "Esto es un test");
		get("/r2cam", "application/json", (req, res) -> {
			return r2;
		}, new JsonTransformer());
		put("/r2cam/:id", (req, res) -> {
			// final short valor = Short.parseShort(req.body());
			short var = 3;
			r2.setDireccion(var);
			return "ok";
		}, new JsonTransformer());
		put("/r2cam/direccion/", (req, res) -> {
			final short valor = Short.parseShort(req.body());
			r2.setDireccion(valor);
			return "hecho";
		}, new JsonTransformer());
	}

	public static void mensaje() {
		System.out.println(mensaje);
	}

}
