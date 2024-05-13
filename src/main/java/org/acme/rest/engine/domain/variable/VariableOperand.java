package org.acme.rest.engine.domain.variable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.value.ValueInterface;
import org.acme.rest.engine.serializer.variable.CustomVariableOperandSerializer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(as = VariableOperand.class, using = CustomVariableOperandSerializer.class)
public class VariableOperand implements ValueInterface {
    private String value;
    private TypeEnum type;
}
