package com.rejs.csvloader.yaml;

import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.yaml.exception.YamlPropertiesLoadFailException;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class ImportPropertiesLoader {
    private final FileSystemAccessObject fileSAO;

    public ImportProperties loadProperties(String filename){
        Resource file = fileSAO.load(filename);

        Yaml yaml = new Yaml(new Constructor(ImportProperties.class, new LoaderOptions()));
        try(InputStream inputStream = file.getInputStream()){
            return yaml.load(inputStream);
        } catch (IOException | RuntimeException e) {
            throw new YamlPropertiesLoadFailException(e);
        }

    }

}
