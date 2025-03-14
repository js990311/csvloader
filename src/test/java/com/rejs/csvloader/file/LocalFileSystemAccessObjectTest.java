package com.rejs.csvloader.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class LocalFileSystemAccessObjectTest {
    private LocalFileSystemAccessObject fileSystemAccessObject = new LocalFileSystemAccessObject();

    @DisplayName("존재하는 파일의 load")
    @Test
    void load() {
        Resource csv = fileSystemAccessObject.load("test.csv");

        assertNotNull(csv, "리소스가 null이 아닐 것");
        assertTrue(csv.exists(), "파일이 존재해야 함");
        assertTrue(csv.isReadable(), "파일이 읽을 수 있어야 함");

        Resource yml = fileSystemAccessObject.load("test.yml");
        assertNotNull(yml, "리소스가 null이 아닐 것");
        assertTrue(yml.exists(), "파일이 존재해야 함");
        assertTrue(yml.isReadable(), "파일이 읽을 수 있어야 함");
    }
}