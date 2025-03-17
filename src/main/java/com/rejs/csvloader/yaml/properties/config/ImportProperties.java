package com.rejs.csvloader.yaml.properties.config;

import com.rejs.csvloader.yaml.properties.model.WorkProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ImportProperties {
    private List<WorkProperty> works;
    private DatabaseProperties database;
}
