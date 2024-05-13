package org.acme.rest.engine.domain.rule.operation;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.type.TypeEnum;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractOperation implements OperandInterface {
    TypeEnum type;
}
