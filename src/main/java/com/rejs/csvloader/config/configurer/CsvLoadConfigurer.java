package com.rejs.csvloader.config.configurer;

@FunctionalInterface
public interface CsvLoadConfigurer {
    void apply(CsvLoadBuilder builder);
}
