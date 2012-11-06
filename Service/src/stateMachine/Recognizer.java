package stateMachine;

import calculator.Context;
import finiteStateMachine.IStateRecognizer;
import finiteStateMachine.exception.RecognitionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recognizer implements IStateRecognizer<Context, State> {

    private final Matcher matcher;

    public Recognizer(String pattern) {
        this.matcher = Pattern.compile(pattern).matcher("");
    }

    @Override
    public boolean recognize(Context context, State state) throws RecognitionException {

        matcher.reset(context.getExpression());

        if (matcher.find(context.getPosition())) {
            context.setPosition(matcher.end());
            return true;
        }

        return false;

    }

}
