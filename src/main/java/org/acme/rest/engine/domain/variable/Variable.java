package org.acme.rest.engine.domain.variable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.rest.engine.domain.common.type.TypeEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Variable {
    private String name;
    private TypeEnum type;
}
