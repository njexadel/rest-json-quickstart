package org.acme.rest.engine.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.rest.engine.domain.variable.Variable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EngineRequest {
    private List<Variable> variables;
    private List<JsonNode> rules;
}
