package com.rejs.csvloader.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class LocalFileSystemAccessObject implements FileSystemAccessObject {
    private static String ROOT = "files/";

    @Override
    public void save(String path, Resource resource) {
        String localSavePath = Paths.get(ROOT + path).toString();
        File file = new File(localSavePath);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] content = resource.getContentAsByteArray();
            outputStream.write(content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Resource load(String path) {
        Path localSavePath = Paths.get(ROOT + path);
        if(!Files.exists(localSavePath)){
            throw new NoSuchElementException();
        }
        try {
            return new UrlResource(localSavePath.toUri());
        }catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }
}
