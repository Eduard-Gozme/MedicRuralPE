package medicruralpe;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProtectorDatos {

    public static String protegerIdentificador(String identificador) {

        if (identificador == null || identificador.isBlank()) {
            throw new IllegalArgumentException(
                    "El identificador del responsable es obligatorio."
            );
        }

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    identificador.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder resultado = new StringBuilder();

            for (byte b : hash) {
                resultado.append(
                        String.format("%02x", b)
                );
            }

            return resultado.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException(
                    "No fue posible proteger el identificador.",
                    e
            );
        }
    }
}