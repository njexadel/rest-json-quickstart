package org.acme.rest.engine.parser.operation;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.AssignOperatorEnum;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.operation.assign.AssignOperation;
import org.acme.rest.engine.domain.variable.VariableOperand;
import org.acme.rest.engine.parser.AbstractParser;

import java.util.Map;

import static org.acme.rest.engine.domain.common.Constants.*;

public class AssignOperationParser extends AbstractParser {
    public static AssignOperation toAssignOperation(String path, JsonNode node, Map<String, TypeEnum> variablesMap) {
        VariableOperand variable = getVariableOperand(path, node, VARIABLE_KEY, variablesMap);
        OperandInterface operand = OperandParser.toOperand(path, node, OPERAND_KEY, variablesMap);

        TypeEnum resultType = variable.getType();
        operand = CastOperationParser.toAutomaticCast(path + "." + OPERAND_KEY, resultType, operand);

        AssignOperatorEnum assignOperator = AssignOperatorEnum.findAssignOperator(resultType)
                .orElseThrow(() -> error(path + "." + ASSIGN_KEY, "invalid assign operation"));
        return AssignOperation.builder()
                .operator(assignOperator)
                .type(TypeEnum.VOID)
                .variable(variable)
                .operand(operand)
                .build();
    }
}
