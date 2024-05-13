package org.acme.rest.engine.parser.value;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.value.*;
import org.acme.rest.engine.domain.variable.VariableOperand;
import org.acme.rest.engine.parser.AbstractParser;

import java.util.Map;

public class ValueParser extends AbstractParser {
    public static ValueInterface toValue(String path, JsonNode node, Map<String, TypeEnum> variablesMap) {
        if (node.isIntegralNumber()) {
            return new IntegerValue(node.longValue());
        }
        if (node.isFloatingPointNumber()) {
            return new DecimalValue(node.decimalValue());
        }
        if (node.isBoolean()) {
            return new BooleanValue(node.asBoolean());
        }
        if (node.isTextual()) {
            String value = node.asText();
            if (variablesMap.containsKey(value)) {
                return new VariableOperand(value, variablesMap.get(value));
            }
            if (TypeEnum.isVariableType(value)) {
                return new TypeValue(value);
            }
            return new StringValue(value);
        }
        throw error(path, "invalid value type");
    }
}
