package org.edupro.webapi.constant;

import java.util.HashMap;
import java.util.Map;

public enum LookupGroup {
    AGAMA("AGAMA"),
    PEKERJAAN("PEKERJAAN"),
    GOL_DARAH("GOL_DARAH"),
    WARGA_NEGARA("WARGA_NEGARA"),
    GENDER("GENDER");

    private final String value;
    private static final Map<String, LookupGroup> BY_LABEL = new HashMap<>();

    static {
        for (LookupGroup e: values()) {
            BY_LABEL.put(e.value, e);
        }
    }

    private LookupGroup(String value) {
        this.value = value;
    }

    public static LookupGroup valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static Map<String, LookupGroup> getAllValue() {
        return BY_LABEL;
    }
}
