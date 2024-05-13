package org.acme.rest.engine.parser.operation;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.*;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.operation.AbstractOperation;
import org.acme.rest.engine.domain.rule.operation.binary.BinaryOperation;
import org.acme.rest.engine.domain.rule.operation.multiple.MultipleOperation;
import org.acme.rest.engine.domain.rule.operation.unary.UnaryOperation;
import org.acme.rest.engine.parser.AbstractParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static org.acme.rest.engine.domain.common.Constants.*;
import static org.acme.rest.engine.parser.operation.OperandParser.toOperand;

public class OperationParser extends AbstractParser {
    public static OperandInterface toOperation(String path, JsonNode node, Map<String, TypeEnum> variablesMap) {
        String operatorPath = path + "." + OPERATOR_KEY;
        if (!node.has(OPERATOR_KEY)) {
            throw error(operatorPath, "missing operator field");
        }
        JsonNode opNode = node.get(OPERATOR_KEY);
        if (!opNode.isTextual()) {
            throw error(operatorPath, "expected string operand");
        }
        String operator = opNode.asText();
        if (CastOperatorEnum.isCastOperator(operator)) {
            return CastOperationParser.toCastOperation(path, node, variablesMap);
        }
        if (AssignOperatorEnum.isAssignOperator(operator)) {
            return AssignOperationParser.toAssignOperation(path, node, variablesMap);
        }
        if (UnaryOperatorEnum.isUnaryOperator(operator)) {
            return toUnaryOperation(path, operator, node, variablesMap);
        }
        if (BinaryOperatorEnum.isBinaryOperator(operator)) {
            return toBinaryOperation(path, operator, node, variablesMap);
        }
        if (MultipleOperatorEnum.isMultipleOperator(operator)) {
            return toMultipleOperation(path, operator, node, variablesMap);
        }
        throw error(operatorPath, "unsupported operation with operator " + operator);
    }

    protected static AbstractOperation toUnaryOperation(String path, String operator, JsonNode node,
                                                        Map<String, TypeEnum> variablesMap) {
        String operatorPath = path + "." + OPERATOR_KEY;
        String operandPath = path + "." + OPERAND_KEY;
        UnaryOperatorEnum op = UnaryOperatorEnum.findOperator(operator)
                .orElseThrow(() -> error(operatorPath, "invalid unary operator"));
        JsonNode operandNode = getNode(path, node, OPERAND_KEY);
        OperandInterface operand = toOperand(operandPath, operandNode, variablesMap);
        if (!Objects.equals(operand.getType(), op.getOperandType())) {
            throw error(operandPath, "expected '" + op.getOperandType().getValue() + "' for operand");
        }
        return UnaryOperation.builder()
                .operator(op)
                .type(op.getType())
                .operand(operand)
                .build();
    }

    protected static AbstractOperation toBinaryOperation(String path, String operator, JsonNode node,
                                                         Map<String, TypeEnum> variablesMap) {
        OperandInterface left = OperandParser.toOperand(path, node, LEFT_OPERAND_KEY, variablesMap);
        OperandInterface right = OperandParser.toOperand(path, node, RIGHT_OPERAND_KEY, variablesMap);
        TypeEnum leftType = left.getType();
        TypeEnum rightType = right.getType();
        TypeEnum resultType = CastOperatorEnum.findResultType(leftType, rightType);
        if (resultType == null) {
            throw error(path, "invalid types for operation '" + operator + "'");
        }
        String leftPath = path + "." + LEFT_OPERAND_KEY;
        String rightPath = path + "." + RIGHT_OPERAND_KEY;
        left = CastOperationParser.toAutomaticCast(leftPath, resultType, left);
        right = CastOperationParser.toAutomaticCast(rightPath, resultType, right);
        BinaryOperatorEnum op = BinaryOperatorEnum.findOperator(operator, resultType)
                .orElseThrow(() -> error(path + "." + OPERATOR_KEY, "invalid binary operator"));
        return BinaryOperation.builder()
                .operator(op)
                .type(op.getResultType())
                .leftOperand(left)
                .rightOperand(right)
                .build();
    }

    protected static AbstractOperation toMultipleOperation(String path, String operator, JsonNode node,
                                                           Map<String, TypeEnum> variablesMap) {
        int count = 0;
        String operatorPath = path + "." + OPERATOR_KEY;
        String operandsPath = path + "." + OPERANDS_KEY;
        ArrayList<OperandInterface> operands = new ArrayList<>();
        Iterator<JsonNode> arrayIt = getArrayIterator(path, node, OPERANDS_KEY);
        MultipleOperatorEnum op = MultipleOperatorEnum.findOperator(operator)
                .orElseThrow(() -> error(operatorPath, "invalid multiple operator"));
        while (arrayIt.hasNext()) {
            String elementPath = operandsPath + "[" + count + "]";
            JsonNode operandNode = arrayIt.next();
            OperandInterface operand = toOperand(elementPath, operandNode, variablesMap);
            if (!Objects.equals(operand.getType(), op.getOperandsType())) {
                throw error(elementPath, "expected '" + op.getOperandsType().getValue() + "' for operand");
            }
            operands.add(operand);
            count++;
        }
        if (operands.isEmpty()) {
            throw error(operandsPath, "missing operands");
        }
        return MultipleOperation.builder()
                .operator(op)
                .type(op.getType())
                .operands(operands)
                .build();
    }
}
