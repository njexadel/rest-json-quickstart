package org.acme.rest.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.rest.engine.domain.rule.Rule;
import org.acme.rest.engine.domain.variable.Variable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    private List<Variable> variables;
    private List<Rule> rules;
}
