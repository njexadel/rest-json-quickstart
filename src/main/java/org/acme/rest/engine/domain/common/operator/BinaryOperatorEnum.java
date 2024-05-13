package org.acme.rest.engine.domain.common.operator;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.acme.rest.engine.domain.common.Constants.*;

@Getter
@AllArgsConstructor
public enum BinaryOperatorEnum implements OperatorInterface {
    // Boolean operations
    BOOL_EQUALS(EQUALS_KEY, TypeEnum.BOOLEAN, TypeEnum.BOOLEAN),

    // Integer operations
    INT_EQUALS(EQUALS_KEY, TypeEnum.BOOLEAN, TypeEnum.INTEGER),
    INT_PLUS(PLUS_KEY, TypeEnum.INTEGER, TypeEnum.INTEGER),
    INT_MINUS(MINUS_KEY, TypeEnum.INTEGER, TypeEnum.INTEGER),
    INT_MULT(MULT_KEY, TypeEnum.INTEGER, TypeEnum.INTEGER),
    INT_DIV(DIV_KEY, TypeEnum.INTEGER, TypeEnum.INTEGER),

    // Decimal operations
    DEC_EQUALS(EQUALS_KEY, TypeEnum.BOOLEAN, TypeEnum.DECIMAL),
    DEC_PLUS(PLUS_KEY, TypeEnum.DECIMAL, TypeEnum.DECIMAL),
    DEC_MINUS(MINUS_KEY, TypeEnum.DECIMAL, TypeEnum.DECIMAL),
    DEC_MULT(MULT_KEY, TypeEnum.DECIMAL, TypeEnum.DECIMAL),
    DEC_DIV(DIV_KEY, TypeEnum.DECIMAL, TypeEnum.DECIMAL);


    public final String operator;
    public final TypeEnum resultType;
    public final TypeEnum operandsType;

    public static boolean isBinaryOperator(String operator) {
        return Arrays.stream(values())
                .anyMatch(op -> Objects.equals(op.getOperator(), operator));
    }

    public static Optional<BinaryOperatorEnum> findOperator(String operator,
                                                            TypeEnum operandsType) {
        return Arrays.stream(values())
                .filter(op -> Objects.equals(op.getOperator(), operator)
                        && Objects.equals(op.getOperandsType(), operandsType))
                .findFirst();
    }

    @JsonValue
    public String jsonValue() {
        return getOperator();
    }
}
