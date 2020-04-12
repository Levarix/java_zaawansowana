package pl.jazapp.app;

import java.io.*;


public class SampleApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var obj = new UserContext(false);
        var out1 = new ByteArrayOutputStream();
        var out = new ObjectOutputStream(out1);
        out.writeObject(obj);

        var in = new ObjectInputStream(new ByteArrayInputStream(out1.toByteArray()));
        var readObj = (UserContext) in.readObject();

        System.out.println();
    }
}
