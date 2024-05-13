package org.acme.rest.engine.domain.rule.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

@Getter
@AllArgsConstructor
public class IntegerValue implements ValueInterface {
    private Long value;

    @Override
    public TypeEnum getType() {
        return TypeEnum.INTEGER;
    }
}
