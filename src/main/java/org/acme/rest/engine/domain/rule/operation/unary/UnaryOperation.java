package org.acme.rest.engine.domain.rule.operation.unary;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.BinaryOperatorEnum;
import org.acme.rest.engine.domain.common.operator.UnaryOperatorEnum;
import org.acme.rest.engine.domain.rule.operation.AbstractOperation;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UnaryOperation  extends AbstractOperation {
    UnaryOperatorEnum operator;
    OperandInterface operand;
}
