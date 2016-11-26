package by.kanarski.booking.utils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class SerializationUtil {

    public static <T> SerialBlob serialize(T obj) throws SQLException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SerialBlob blob = new SerialBlob(baos.toByteArray());
        return blob;
    }

    public static <T> T deserialize(Blob blob, T outputType) throws SQLException {
        T obj = null;
        InputStream binaryStram = blob.getBinaryStream();
        try (ObjectInputStream ois = new ObjectInputStream(binaryStram)) {
            obj = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
