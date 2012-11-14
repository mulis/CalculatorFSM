package finiteStateMachine;

public interface StateRecognizer<Context, State> {

    public boolean recognize(Context context, State state) throws StateMachineException;

}
