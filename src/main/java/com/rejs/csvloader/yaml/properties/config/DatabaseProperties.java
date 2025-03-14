package com.rejs.csvloader.yaml.properties.config;

import com.rejs.csvloader.yaml.properties.model.ImportWork;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DatabaseProperties {
    private String host;
    private String username;
    private String password;
    private String driver;
}
