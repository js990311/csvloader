package com.rejs.csvloader.yaml.properties.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DatabaseProperties {
    private String host;
    private String username;
    private String password;
    private String driver;
}
