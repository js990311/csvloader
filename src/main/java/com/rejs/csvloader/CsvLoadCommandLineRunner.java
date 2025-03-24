package com.rejs.csvloader;

import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class CsvLoadCommandLineRunner implements CommandLineRunner {
    private final CsvLoadService csvLoadService;
    private final ImportPropertiesLoader loader;
    private final FileSystemAccessObject fao;

    @Override
    public void run(String... args) throws Exception {
        String ymlPath = null;
        String csvPath = null;

        for (int i = 0; i < args.length; i++) {
            if ("-y".equals(args[i]) && i + 1 < args.length) {
                ymlPath = args[++i];
            } else if ("-c".equals(args[i]) && i + 1 < args.length) {
                csvPath = args[++i];
            }
        }

        if(ymlPath == null || csvPath == null){
            throw new IllegalArgumentException("we need 2 parameter");
        }

        ImportProperties properties = loader.loadProperties(ymlPath);
        Resource data = fao.load(csvPath);

        csvLoadService.load(properties, data);
    }
}
