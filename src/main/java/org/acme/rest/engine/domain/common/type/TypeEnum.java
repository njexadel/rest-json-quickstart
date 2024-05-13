package org.acme.rest.engine.domain.common.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum TypeEnum {
    TYPE("", false),
    VOID("", false),
    BOOLEAN("Boolean", true),
    INTEGER("Integer", true),
    DECIMAL("Decimal", true),
    STRING("String", true);

    private final String value;
    private final Boolean variableType;

    @JsonValue
    public String getValue() {
        return value;
    }

    public static boolean isVariableType(String type) {
        return Arrays.stream(values()).anyMatch(v -> v.getVariableType() && v.getValue().equals(type));
    }

    public static Optional<TypeEnum> fromVariableType(String type) {
        return Arrays.stream(values())
                .filter(v -> v.getVariableType() && v.getValue().equals(type)).findFirst();
    }

    public static boolean mustAutomaticCast(TypeEnum resultType, TypeEnum type) {
        return DECIMAL.equals(resultType) && INTEGER.equals(type);
    }
}
