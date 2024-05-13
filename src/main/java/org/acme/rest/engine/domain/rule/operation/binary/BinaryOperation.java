package org.acme.rest.engine.domain.rule.operation.binary;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.BinaryOperatorEnum;
import org.acme.rest.engine.domain.rule.operation.AbstractOperation;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BinaryOperation extends AbstractOperation {
    BinaryOperatorEnum operator;
    OperandInterface leftOperand;
    OperandInterface rightOperand;
}
