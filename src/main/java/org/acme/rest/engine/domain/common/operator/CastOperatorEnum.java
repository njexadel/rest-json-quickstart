package org.acme.rest.engine.domain.common.operator;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.value.DecimalValue;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.acme.rest.engine.domain.common.Constants.CAST_KEY;

@Getter
@AllArgsConstructor
public enum CastOperatorEnum implements OperatorInterface {
    CAST_DEC(CAST_KEY, TypeEnum.DECIMAL, TypeEnum.INTEGER),
    CAST_INT(CAST_KEY, TypeEnum.INTEGER, TypeEnum.DECIMAL);

    public final String operator;
    public final TypeEnum type;
    public final TypeEnum operandType;

    public static boolean isCastOperator(String operator) {
        return Arrays.stream(values())
                .anyMatch(op -> Objects.equals(op.getOperator(), operator));
    }

    public static Optional<CastOperatorEnum> findOperator(TypeEnum resultType, TypeEnum type) {
        if (Objects.equals(resultType, type)) {
            return Optional.empty();
        }
        return Arrays.stream(values())
                .filter(op -> Objects.equals(op.getType(), resultType)
                        && Objects.equals(op.getOperandType(), type))
                .findFirst();
    }

    public static TypeEnum findResultType(TypeEnum operand1, TypeEnum operand2) {
        if (Objects.equals(operand1, operand2)) {
            return operand1;
        }
        if ((Objects.equals(TypeEnum.DECIMAL, operand1) && Objects.equals(TypeEnum.INTEGER, operand2))
                || (Objects.equals(TypeEnum.INTEGER, operand1) && Objects.equals(TypeEnum.DECIMAL, operand2))) {
            return TypeEnum.DECIMAL;
        }
        return null;
    }

    @JsonValue
    public String jsonValue() {
        return getOperator();
    }
}
