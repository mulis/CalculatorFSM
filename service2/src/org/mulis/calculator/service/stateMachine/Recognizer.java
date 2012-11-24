package org.mulis.calculator.service.stateMachine;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.api.finiteStateMachine.StateRecognizer;
import org.mulis.calculator.service.calculator.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recognizer implements StateRecognizer<Context, State> {

    private final Matcher matcher;

    public Recognizer(String pattern) {
        this.matcher = Pattern.compile(pattern).matcher("");
    }

    public Matcher getMatcher() {
        return matcher;
    }

    @Override
    public boolean recognize(Context context, State state) throws StateMachineException {

        matcher.reset(context.getExpression());

        if (matcher.find(context.getPosition())) {
            context.setPosition(matcher.end());
            return true;
        }

        return false;

    }

}
