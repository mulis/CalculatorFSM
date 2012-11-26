var CalculatorView = function(model) {

    var me = this;
    this.model = model;

    $(document).bind(
        CalculatorEvents.EXPRESSION_READY,
        function(aEvent) {
            me.onExpressionReady(aEvent);
        }
    );

     $(document).bind(
        CalculatorEvents.RESULT_READY,
        function(aEvent) {
            me.outputResult();
        }
    );

    $(document).bind(
        CalculatorEvents.SERVICE_ERROR,
        function(aEvent) {
            me.outputResult();
        }
    );

    CalculatorControls.expressionInput.bind(
        "keypress",
        function(aEvent) {
            var keycode = (aEvent.keyCode ? aEvent.keyCode : aEvent.which);
            if(keycode == "13"){
                CalculatorControls.calculateButton.click();
            }
        }
    );

    CalculatorControls.clearButton.bind(
        "click",
        function(aEvent) {
            CalculatorControls.resultOutput.text("");
        }
    );

}

CalculatorView.prototype.onExpressionReady = function(aEvent) {
    CalculatorControls.calculateButton.prop('disabled', true);
    this.model.setExpression(CalculatorControls.expressionInput.val());
}

CalculatorView.prototype.outputResult = function() {
    CalculatorControls.calculateButton.prop('disabled', false);
    CalculatorControls.resultOutput.append(this.model.toString());
}
