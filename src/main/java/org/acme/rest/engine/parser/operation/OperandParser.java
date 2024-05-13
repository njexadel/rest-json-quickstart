package org.acme.rest.engine.parser.operation;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.parser.AbstractParser;
import org.acme.rest.engine.parser.value.ValueParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OperandParser extends AbstractParser {
    public static List<OperandInterface> toOperandList(String path, JsonNode node, String key, Map<String, TypeEnum> variablesMap) {
        int count = 0;
        List<OperandInterface> operands = new ArrayList<>();
        Iterator<JsonNode> elements = getArrayIterator(path, node, key);
        while (elements.hasNext()) {
            JsonNode stmt = elements.next();
            String stmtPath = path + "[" + count + "]";
            operands.add(toOperand(stmtPath, stmt, variablesMap));
            count++;
        }
        return operands;
    }

    protected static OperandInterface toOperand(String path, JsonNode node, String key, Map<String, TypeEnum> variablesMap) {
        String opPath = path + "." + key;
        JsonNode opNode = getNode(path, node, key);
        return toOperand(opPath, opNode, variablesMap);
    }

    protected static OperandInterface toOperand(String path, JsonNode node, Map<String, TypeEnum> variablesMap) {
        if (node.isNull() || node.isArray()) {
            throw error(path, "missing valid operand");
        }
        if (!node.isContainerNode()) {
            return ValueParser.toValue(path, node, variablesMap);
        }
        return OperationParser.toOperation(path, node, variablesMap);
    }
}
