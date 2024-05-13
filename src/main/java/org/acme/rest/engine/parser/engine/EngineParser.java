package org.acme.rest.engine.parser.engine;

import org.acme.rest.engine.domain.Engine;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.variable.Variable;
import org.acme.rest.engine.parser.AbstractParser;
import org.acme.rest.engine.request.EngineRequest;
import org.mapstruct.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.acme.rest.engine.parser.rule.RuleParser.toRules;

@Mapper(componentModel = "cdi", builder = @Builder(disableBuilder = true))
public abstract class EngineParser extends AbstractParser {
    @Mapping(target = "variables", source = "variables")
    @Mapping(target = "rules", ignore = true)
    public abstract Engine toEngine(EngineRequest request);

    @AfterMapping
    protected void afterMapping(@MappingTarget Engine engine, EngineRequest request) {
        String path = "$";
        List<Variable> variables = engine.getVariables();
        if (Objects.isNull(variables) || variables.isEmpty()) {
            throw error(path, "engine variables must be not empty");
        }
        Map<String, TypeEnum> variablesMap = new HashMap<>();
        for (int i = 0; i < variables.size(); i++) {
            String varPath = path + ".variables[" + i + "]";
            Variable v = variables.get(i);
            String name = v.getName();
            if (variablesMap.containsKey(name)) {
                throw error(varPath, "duplicate variable name: " + name);
            }
            variablesMap.put(name, v.getType());
        }
        engine.setRules(toRules(path, request.getRules(), variablesMap));
    }
}
