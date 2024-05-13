package org.acme.rest.engine.parser.operation;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.CastOperatorEnum;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.operation.cast.CastOperation;
import org.acme.rest.engine.parser.AbstractParser;

import java.util.Map;
import java.util.Objects;

import static org.acme.rest.engine.domain.common.Constants.OPERAND_KEY;
import static org.acme.rest.engine.domain.common.Constants.TYPE_KEY;

public class CastOperationParser extends AbstractParser {
    public static OperandInterface toCastOperation(String path, JsonNode node, Map<String, TypeEnum> variablesMap) {
        String type = getStringValue(path, node, TYPE_KEY);
        OperandInterface operand = OperandParser.toOperand(path, node, OPERAND_KEY, variablesMap);

        TypeEnum resultType = TypeEnum.fromVariableType(type)
                .orElseThrow(() -> error(path, "invalid type '" + type + "'for casting"));

        return toAutomaticCast(path + "." + OPERAND_KEY, resultType, operand);
    }

    public static OperandInterface toAutomaticCast(String path, TypeEnum resultType, OperandInterface operand) {
        TypeEnum operandType = operand.getType();
        if (Objects.equals(resultType, operandType)) {
            return operand;
        }
        CastOperatorEnum castOperator = CastOperatorEnum.findOperator(resultType, operandType)
                .orElseThrow(() -> error(path, "invalid type '" + operandType + "' for operand"));;
        return CastOperation.builder()
                .operator(castOperator)
                .type(resultType)
                .operand(operand)
                .build();
    }
}
