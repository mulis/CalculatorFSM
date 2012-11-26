var CalculatorView = function(model) {

    var me = this;
    this.model = model;
    this.controls = {
         expressionInput : $("#expressionInput"),
         calculateButton : $("#calculateButton"),
         resultOutput : $("#resultTextArea"),
         clearButton : $("#clearButton")
     }

    this.controls.calculateButton.bind(
        "click",
        function() {
            $(document).trigger(CalculatorEvents.EXPRESSION_READY);
        }
    );

    this.controls.expressionInput.bind(
        "keypress",
        function(aEvent) {
            var keycode = (aEvent.keyCode ? aEvent.keyCode : aEvent.which);
            if(keycode == "13"){
                $(document).trigger(CalculatorEvents.EXPRESSION_READY);
            }
        }
    );

    this.controls.clearButton.bind(
        "click",
        function(aEvent) {
            me.clearResultOutput();
        }
    );

    $(document).bind(
        CalculatorEvents.EXPRESSION_READY,
        function(aEvent) {
            me.updateModel();
            $(document).trigger(CalculatorEvents.MODEL_READY);
            me.disableCalculateButton();
        }
    );

     $(document).bind(
        CalculatorEvents.RESULT_READY,
        function(aEvent) {
            me.updateView();
            me.enableCalculateButton();
        }
    );

    $(document).bind(
        CalculatorEvents.SERVICE_ERROR,
        function(aEvent) {
            me.updateView();
            me.enableCalculateButton();
        }
    );

}

CalculatorView.prototype.updateModel = function() {
    this.model.setExpression(this.controls.expressionInput.val());
}

CalculatorView.prototype.updateView = function() {
    this.controls.resultOutput.append(this.model.toString());
}

CalculatorView.prototype.enableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', false);
}

CalculatorView.prototype.disableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', true);
}

CalculatorView.prototype.clearResultOutput = function() {
    this.controls.resultOutput.text("");
}

