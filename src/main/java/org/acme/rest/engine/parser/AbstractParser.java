package org.acme.rest.engine.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.variable.VariableOperand;

import java.util.Iterator;
import java.util.Map;

public abstract class AbstractParser {
    protected static IllegalStateException error(String path, String message) {
        return new IllegalStateException("Error '" + path + "': " + message);
    }

    protected static JsonNode getNode(String path, JsonNode node, String key) {
        String typePath = path + "." + key;
        if (!node.has(key)) {
            throw error(typePath, "missing '" + key + "' field");
        }
        return node.get(key);
    }

    protected static Iterator<JsonNode> getArrayIterator(String path, JsonNode node, String key) {
        String arrayPath = path + "." + key;
        JsonNode arrNode = getNode(path, node, key);
        if (!arrNode.isArray()) {
            throw error(arrayPath, "expected array type");
        }
        return arrNode.elements();
    }

    protected static Long getPositiveLong(String path, JsonNode node, String key) {
        String valPath = path + "." + key;
        JsonNode valNode = getNode(path, node, key);
        if (!valNode.isIntegralNumber()) {
            throw error(valPath, "must be an integer");
        }
        long val = valNode.asLong();
        if (val <= 0) {
            throw error(path, "must be a positive integer");
        }
        return val;
    }

    protected static String getStringValue(String path, JsonNode node, String key) {
        JsonNode opNode = getNode(path, node, key);
        if (!opNode.isTextual()) {
            String typePath = path + "." + key;
            throw error(typePath, "expected field to be string");
        }
        return opNode.asText();
    }

    protected static VariableOperand getVariableOperand(String path, JsonNode node, String key,
                                                        Map<String, TypeEnum> variablesMap) {
        String variableName = getStringValue(path, node, key);
        if (variablesMap.containsKey(variableName)) {
            return new VariableOperand(variableName, variablesMap.get(variableName));
        }
        throw error(path + "." + key, "variable not found");
    }
}
