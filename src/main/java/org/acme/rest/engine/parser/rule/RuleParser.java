package org.acme.rest.engine.parser.rule;

import com.fasterxml.jackson.databind.JsonNode;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.common.type.TypeEnum;
import org.acme.rest.engine.domain.rule.Rule;
import org.acme.rest.engine.parser.AbstractParser;
import org.acme.rest.engine.parser.operation.OperandParser;

import java.util.*;

public class RuleParser extends AbstractParser {
    public static List<Rule> toRules(String path, List<JsonNode> rules, Map<String, TypeEnum> variablesMap) {
        path = path + ".rules";
        if (Objects.isNull(rules) || rules.isEmpty()) {
            throw error(path, "engine rules must be not empty");
        }
        List<Rule> newRules = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            String rulePath = path + "[" + i + "]";
            JsonNode r = rules.get(i);
            newRules.add(toRule(rulePath, r, variablesMap));
        }
        int rulesCount = newRules.size();
        newRules.sort(Comparator.comparingInt(Rule::getRuleOrder));
        for (int i = 1; i < rulesCount; i++) {
            if (newRules.get(i-1).getRuleOrder() != i) {
                String rulePath = path + "[" + (i-1) + "]";
                throw error(rulePath, "expected rule order " + i);
            }
        }
        return newRules;
    }

    protected static Rule toRule(String path, JsonNode rule, Map<String, TypeEnum> variablesMap) {
        Rule newRule = new Rule();
        newRule.setRuleOrder(getPositiveLong(path, rule, Rule.Fields.ruleOrder).intValue());
        newRule.setRuleName(getStringValue(path, rule, Rule.Fields.ruleName));
        newRule.setWhen(toWhen(path + "." + Rule.Fields.when, rule, variablesMap));
        newRule.setThen(toThen(path + "." + Rule.Fields.then, rule, variablesMap));
        return newRule;
    }

    protected static List<OperandInterface> toWhen(String path, JsonNode rule, Map<String, TypeEnum> variablesMap) {
        List<OperandInterface> whenOperands = OperandParser.toOperandList(path, rule, Rule.Fields.when, variablesMap);
        for (int i = 0; i < whenOperands.size(); i++) {
            OperandInterface operand = whenOperands.get(i);
            if (!TypeEnum.BOOLEAN.equals(operand.getType())) {
                String operandPath = path + "[" + i + "]";
                throw error(operandPath, "expected boolean operand");
            }
        }
        return whenOperands;
    }

    protected static List<OperandInterface> toThen(String path, JsonNode rule, Map<String, TypeEnum> variablesMap) {
        List<OperandInterface> thenOperands = OperandParser.toOperandList(path, rule, Rule.Fields.then, variablesMap);
        for (int i = 0; i < thenOperands.size(); i++) {
            OperandInterface operand = thenOperands.get(i);
            if (!TypeEnum.VOID.equals(operand.getType())) {
                String operandPath = path + "[" + i + "]";
                throw error(operandPath, "expected void operand");
            }
        }
        return thenOperands;
    }
}
