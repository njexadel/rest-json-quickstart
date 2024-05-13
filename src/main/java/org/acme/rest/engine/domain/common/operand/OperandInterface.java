package org.acme.rest.engine.domain.common.operand;

import org.acme.rest.engine.domain.common.type.TypeInterface;

public interface OperandInterface extends TypeInterface {
    default boolean isTerminal() {
        return false;
    }
}
