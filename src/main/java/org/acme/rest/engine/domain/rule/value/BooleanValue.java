package org.acme.rest.engine.domain.rule.value;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

@Getter
@AllArgsConstructor
public class BooleanValue implements ValueInterface {
    private Boolean value;

    @Override
    public TypeEnum getType() {
        return TypeEnum.BOOLEAN;
    }
}