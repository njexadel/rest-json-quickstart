package org.acme.rest.engine.domain.common.operator;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.acme.rest.engine.domain.common.Constants.ASSIGN_KEY;

@Getter
@AllArgsConstructor
public enum AssignOperatorEnum implements OperatorInterface {
    BOOL_ASSIGN(ASSIGN_KEY, TypeEnum.VOID, TypeEnum.BOOLEAN),
    INT_ASSIGN(ASSIGN_KEY, TypeEnum.VOID, TypeEnum.INTEGER),
    DEC_ASSIGN(ASSIGN_KEY, TypeEnum.VOID, TypeEnum.DECIMAL);

    public final String operator;
    public final TypeEnum type;
    public final TypeEnum variableType;

    public static boolean isAssignOperator(String operator) {
        return Arrays.stream(values())
                .anyMatch(op -> Objects.equals(op.getOperator(), operator));
    }

    public static Optional<AssignOperatorEnum> findAssignOperator(TypeEnum variableType) {
        return Arrays.stream(values())
                .filter(op -> Objects.equals(op.getVariableType(), variableType))
                .findFirst();
    }

    @JsonValue
    public String jsonValue() {
        return getOperator();
    }
}
