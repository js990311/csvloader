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

@Profile("!test")
@RequiredArgsConstructor
@Component
public class CsvLoadCommandLineRunner implements CommandLineRunner {
    private final CsvLoadService csvLoadService;
    private final ImportPropertiesLoader loader;
    private final FileSystemAccessObject fao = new LocalFileSystemAccessObject();

    @Override
    public void run(String... args) throws Exception {
        if(args.length < 2){
            throw new IllegalArgumentException("we need 2 parameter");
        }

        String ymlPath = args[0];
        String csvPath = args[1];

        ImportProperties properties = loader.loadProperties(ymlPath);
        Resource data = fao.load(csvPath);

        csvLoadService.load(properties, data);
    }
}
