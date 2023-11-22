package com.example.appinventario;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPlanetScale extends AsyncTask <Void, Void, Connection> {
    @Override
    protected Connection doInBackground(Void... voids) {
        Connection connection = null;

        try {

            String url = "jdbc:mysql://aws.connect.psdb.cloud/appinventario?&sslMode=VERIFY_IDENTITY";
            String dbUsername = "mp06x9kf57vlvn4ox1h7";
            String dbPassword ="pscale_pw_RgqIBycxvfBattEuFStTAzmneE5Ke5mcalZO7kSDaRo";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    protected void onPostExecute(Connection connection) {
        // Aquí puedes manejar la conexión una vez que se haya completado en el hilo aparte
        System.out.println(connection);
        if (connection != null) {
            // La conexión se ha establecido correctamente, realiza las operaciones necesarias con la base de datos
            System.out.println("La conexión se establecio correctamente");
        } else {
            // La conexión falló
            Log.e("ConnectToPlanetScale", "La conexión a PlanetScale falló");
        }
    }
}
