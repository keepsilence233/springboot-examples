package qx.leizige.freemarker;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = null;
        byte[] cLassBytes = new byte[0];
        try {
            path = Paths.get(new URI("/Users/chinese.youth/temp/HelloWorldTest.java"));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return defineClass(null,cLassBytes, 0, cLassBytes.length);
    }
}
