package org.acme.rest.engine.domain.rule.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DecimalValue implements ValueInterface {
    private BigDecimal value;

    @Override
    public TypeEnum getType() {
        return TypeEnum.DECIMAL;
    }
}
