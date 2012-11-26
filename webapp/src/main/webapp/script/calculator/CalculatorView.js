var CalculatorView = function(model) {

    var me = this;
    this.model = model;

    $(document).bind(
        CalculatorEvents.EXPRESSION_READY,
        function(aEvent) {
            me.updateModel();
        }
    );

     $(document).bind(
        CalculatorEvents.RESULT_READY,
        function(aEvent) {
            me.updateView();
        }
    );

    $(document).bind(
        CalculatorEvents.SERVICE_ERROR,
        function(aEvent) {
            me.updateView();
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

CalculatorView.prototype.updateModel = function() {
    CalculatorControls.calculateButton.prop('disabled', true);
    this.model.setExpression(CalculatorControls.expressionInput.val());
}

CalculatorView.prototype.updateView = function() {
    CalculatorControls.calculateButton.prop('disabled', false);
    CalculatorControls.resultOutput.append(this.model.toString());
}
