package org.acme.rest.engine.domain.rule.operation.assign;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.operator.AssignOperatorEnum;
import org.acme.rest.engine.domain.rule.operation.AbstractOperation;
import org.acme.rest.engine.domain.variable.VariableOperand;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AssignOperation extends AbstractOperation {
    AssignOperatorEnum operator;
    VariableOperand variable;
    OperandInterface operand;
}

