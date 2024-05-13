package org.acme.rest.engine.domain.common.operator;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum MultipleOperatorEnum implements OperatorInterface{
    AND("and", TypeEnum.BOOLEAN, TypeEnum.BOOLEAN),
    OR("or", TypeEnum.BOOLEAN, TypeEnum.BOOLEAN);

    public final String operator;
    public final TypeEnum type;
    public final TypeEnum operandsType;

    public static boolean isMultipleOperator(String operator) {
        return operator.equals("and") || operator.equals("or");
    }

    public static Optional<MultipleOperatorEnum> findOperator(String operator) {
        return Arrays.stream(values())
                .filter(op -> Objects.equals(op.getOperator(), operator))
                .findFirst();
    }

    @JsonValue
    public String jsonValue() {
        return getOperator();
    }
}
