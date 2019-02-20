package AccesoBD;

import java.io.BufferedReader;
import java.io.FileReader;

public class Conector {

    private static AccesoBD conectorBD = null;

    public static AccesoBD getConector() throws java.sql.SQLException, Exception {
        if (conectorBD == null) {
            String[] infoConexion = new String[4];
            int contador = 0;
            String linea;
            try (FileReader reader = new FileReader("Archivo.txt")) {
                BufferedReader buffer = new BufferedReader(reader);
                while ((linea = buffer.readLine()) != null) {
                    infoConexion[contador] = linea;
                    contador++;
                }

            }

            conectorBD = new AccesoBD(infoConexion[0], infoConexion[1] + "?user=" + infoConexion[2] + "&password=" + infoConexion[3]);

        }
        return conectorBD;
    }
}
