package org.acme.rest.engine.domain.rule.operation.cast;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.CastOperatorEnum;
import org.acme.rest.engine.domain.rule.operation.AbstractOperation;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CastOperation extends AbstractOperation {
    CastOperatorEnum operator;
    OperandInterface operand;
}
