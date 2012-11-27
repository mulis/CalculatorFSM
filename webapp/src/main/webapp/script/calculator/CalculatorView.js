Calculator.prototype.View = function(calculator) {

    var me = this;

    this.calculator = calculator;
    
    this.controls = {
         expressionInput : $("#expressionInput"),
         calculateButton : $("#calculateButton"),
         resultOutput : $("#resultTextArea"),
         clearButton : $("#clearButton")
     }

    this.controls.calculateButton.bind(
        "click",
        function() {
            $(document).trigger(calculator.events.EXPRESSION_READY);
        }
    );

    this.controls.expressionInput.bind(
        "keypress",
        function(aEvent) {
            var keycode = (aEvent.keyCode ? aEvent.keyCode : aEvent.which);
            if(keycode == "13"){
                $(document).trigger(calculator.events.EXPRESSION_READY);
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
        calculator.events.EXPRESSION_READY,
        function(aEvent) {
            me.updateModel();
            $(document).trigger(calculator.events.MODEL_READY);
            me.disableCalculateButton();
        }
    );

     $(document).bind(
        calculator.events.RESULT_READY,
        function(aEvent) {
            me.updateView();
            me.enableCalculateButton();
        }
    );

    $(document).bind(
        calculator.events.SERVICE_ERROR,
        function(aEvent) {
            me.updateView();
            me.enableCalculateButton();
        }
    );

}

Calculator.prototype.View.prototype.updateModel = function() {
    this.calculator.model.setExpression(this.controls.expressionInput.val());
}

Calculator.prototype.View.prototype.updateView = function() {
    this.controls.resultOutput.append(this.calculator.model.toString());
}

Calculator.prototype.View.prototype.enableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', false);
}

Calculator.prototype.View.prototype.disableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', true);
}

Calculator.prototype.View.prototype.clearResultOutput = function() {
    this.controls.resultOutput.text("");
}

