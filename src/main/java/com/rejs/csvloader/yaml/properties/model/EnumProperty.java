package com.rejs.csvloader.yaml.properties.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class EnumProperty {
    /**
     * CSV에 저장된 value가 enumValue과 다른 경우
     */
    private Map<String, String> mappings;

    /**
     * CSV에 저장된 value가 enumValue와 같은 경우
     * 단 mappings가 존재하는 경우 mappings가 우선시됨
     */
    private List<String> values;
}
