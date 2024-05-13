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
public enum UnaryOperatorEnum implements OperatorInterface{
    NOT("not", TypeEnum.BOOLEAN, TypeEnum.BOOLEAN);

    public final String operator;
    public final TypeEnum type;
    public final TypeEnum operandType;

    public static boolean isUnaryOperator(String operator) {
        return operator.equals("not");
    }

    public static Optional<UnaryOperatorEnum> findOperator(String operator) {
        return Arrays.stream(values())
                .filter(op -> Objects.equals(op.getOperator(), operator))
                .findFirst();
    }

    @JsonValue
    public String jsonValue() {
        return getOperator();
    }
}
