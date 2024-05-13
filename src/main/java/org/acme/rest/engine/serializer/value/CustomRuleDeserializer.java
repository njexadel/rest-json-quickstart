package org.acme.rest.engine.serializer.value;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.rule.Rule;
import org.acme.rest.engine.domain.rule.value.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CustomRuleDeserializer extends StdDeserializer<Rule> {
    public CustomRuleDeserializer() {
        this(null);
    }

    protected CustomRuleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Rule deserialize(JsonParser json, DeserializationContext context) throws IOException {
        if (json.getCurrentToken() != JsonToken.START_OBJECT) {
            throw new IOException("Invalid json token: " + json.getCurrentToken());
        }
        Rule rule = new Rule();
        while (json.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = json.getCurrentName();
            if (fieldName.equals(Rule.Fields.ruleOrder)) {
                rule.setRuleOrder(deserializeRuleOrder(json));
            } else if (fieldName.equals(Rule.Fields.when)) {

            } else if (fieldName.equals(Rule.Fields.then)) {
                if (!json.isExpectedStartArrayToken()) {
                    throw new IOException("Invalid '" + Rule.Fields.then + "' array value");
                }
            }
        }
        return rule;
    }

    private Integer deserializeRuleOrder(JsonParser json) throws IOException {
        json.nextToken();
        if (!json.isExpectedNumberIntToken()) {
            throw new IOException("'" + Rule.Fields.ruleOrder + "' must be an integer value");
        }
        int value = json.getIntValue();
        if (value <= 0) {
            throw new IOException("'" + Rule.Fields.ruleOrder + "' must be greater than 0");
        }
        return value;
    }

    private List<OperandInterface> deserializeWhen(JsonParser json) throws IOException {
        if (!json.isExpectedStartArrayToken()) {
            throw new IOException("'" + Rule.Fields.when + "' must be an array value");
        }
        if (!json.isExpectedStartArrayToken()) {
            throw new IOException("Invalid '" + Rule.Fields.when + "' array value");
        }
        return null;
    }

//    private OperandInterface deserializeOperand(JsonParser json) throws IOException {
//        if (!json.isExpectedStartObjectToken()) {
//
//        }
//    }

    private ValueInterface deserializeValue(JsonParser json) throws IOException {
        if (json.isExpectedStartArrayToken() || json.isExpectedStartObjectToken()) {
            throw new IOException("'" + Rule.Fields.then + "' must be an array value");
        }
        if (json.currentToken() == JsonToken.VALUE_FALSE || json.currentToken() == JsonToken.VALUE_TRUE) {
            return new BooleanValue(json.getBooleanValue());
        }
        if (json.currentToken() == JsonToken.VALUE_NUMBER_INT) {
            return new IntegerValue((long) json.getIntValue());
        }
        if (json.currentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
            return new DecimalValue(BigDecimal.valueOf(json.getDoubleValue()));
        }
        if (json.currentToken() == JsonToken.VALUE_STRING) {
            return new StringValue(json.getValueAsString());
        }
        throw new IOException("Invalid '" + Rule.Fields.then + "' value");
    }

//    private ValueInterface deserializeValue(JsonParser json, DeserializationContext deserializationContext) throws IOException, JacksonException {
//        JsonToken token = json.getCurrentToken();
//        if (token.isBoolean()) {
//            return new BooleanValue(Boolean.valueOf(token.asString()));
//        }
//        if (token.id() == JsonTokenId.ID_NUMBER_INT) {
//            return new IntegerValue(Long.valueOf(token.asString()));
//        }
//        if (token.id() == JsonTokenId.ID_NUMBER_FLOAT) {
//            return new DecimalValue(new BigDecimal(token.asString()));
//        }
//    }
}
