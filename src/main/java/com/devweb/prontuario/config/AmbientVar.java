package com.devweb.prontuario.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AmbientVar {
    static Dotenv dotenv = Dotenv.configure().filename("/.env").load();
    static String databaseHost = dotenv.get("DATABASE_HOST");
    static String databasePort = dotenv.get("DATABASE_PORT");
    static String databaseName = dotenv.get("DATABASE_NAME");
    static String databaseUsername = dotenv.get("DATABASE_USERNAME");
    static String databasePassword = dotenv.get("DATABASE_PASSWORD");

    public static void run(){
        assert databaseHost != null;
        System.setProperty("DATABASE_HOST", databaseHost);
        assert databasePort != null;
        System.setProperty("DATABASE_PORT", databasePort);
        assert databaseName != null;
        System.setProperty("DATABASE_NAME", databaseName);
        assert databaseUsername != null;
        System.setProperty("DATABASE_USERNAME", databaseUsername);
        assert databasePassword != null;
        System.setProperty("DATABASE_PASSWORD", databasePassword);
    }


}
