package org.acme.rest.engine.serializer.variable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.acme.rest.engine.domain.variable.VariableOperand;

import java.io.IOException;

public class CustomVariableOperandSerializer extends StdSerializer<VariableOperand> {
    public CustomVariableOperandSerializer() {
        this(null);
    }

    protected CustomVariableOperandSerializer(Class<VariableOperand> t) {
        super(t);
    }

    @Override
    public void serialize(VariableOperand value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getValue());
    }
}
