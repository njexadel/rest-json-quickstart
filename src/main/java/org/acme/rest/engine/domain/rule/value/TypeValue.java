package org.acme.rest.engine.domain.rule.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

@Getter
@AllArgsConstructor
public class TypeValue implements ValueInterface {
    private String value;

    @Override
    public TypeEnum getType() {
        return TypeEnum.TYPE;
    }
}