package org.acme.rest.engine.domain.rule.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.acme.rest.engine.domain.common.operand.OperandInterface;

public interface ValueInterface extends OperandInterface {
    <T> T getValue();

    @JsonIgnore
    default boolean isTerminal() {
        return true;
    }
}
