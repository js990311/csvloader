package com.rejs.csvloader.yaml.exception;

/**
 * Yaml 파일로부터 properties를 load하는 데 실패하는 경우
 */
public class YamlPropertiesLoadFailException extends RuntimeException{
    public YamlPropertiesLoadFailException(Throwable cause) {
        super(cause);
    }
}
