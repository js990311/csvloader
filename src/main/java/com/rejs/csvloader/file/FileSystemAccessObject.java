package com.rejs.csvloader.file;

import org.springframework.core.io.Resource;

public interface FileSystemAccessObject {
    public void save(String path, Resource resource);
    public Resource load(String path);
}
