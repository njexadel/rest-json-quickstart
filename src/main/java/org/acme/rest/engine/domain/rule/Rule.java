package org.acme.rest.engine.domain.rule;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.serializer.value.CustomRuleDeserializer;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
//@JsonDeserialize(as = Rule.class, using = CustomRuleDeserializer.class)
public class Rule {
    private Integer ruleOrder;
    private String ruleName;
    private List<OperandInterface> when;
    private List<OperandInterface> then;
}
