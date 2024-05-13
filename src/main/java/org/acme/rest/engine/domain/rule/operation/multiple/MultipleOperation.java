package org.acme.rest.engine.domain.rule.operation.multiple;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.MultipleOperatorEnum;
import org.acme.rest.engine.domain.rule.operation.AbstractOperation;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MultipleOperation extends AbstractOperation {
    MultipleOperatorEnum operator;
    List<OperandInterface> operands;
}
