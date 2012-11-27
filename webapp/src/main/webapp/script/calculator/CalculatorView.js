Calculator.prototype.View = function(calculator) {

    var me = this;

    this.calculator = calculator;
    
    this.controls = {
         expressionInput : this.calculator.element.find(".calculator-expressionInput"),
         calculateButton : this.calculator.element.find(".calculator-calculateButton"),
         resultOutput : this.calculator.element.find(".calculator-resultTextArea"),
         clearButton : this.calculator.element.find(".calculator-clearButton")
     }

    this.controls.calculateButton.bind(
        "click",
        function() {
            me.calculator.element.trigger(calculator.events.EXPRESSION_READY);
        }
    );

    this.controls.expressionInput.bind(
        "keypress",
        function(aEvent) {
            var keycode = (aEvent.keyCode ? aEvent.keyCode : aEvent.which);
            if(keycode == "13"){
                me.calculator.element.trigger(calculator.events.EXPRESSION_READY);
            }
        }
    );

    this.controls.clearButton.bind(
        "click",
        function(aEvent) {
            me.clearResultOutput();
        }
    );

    $(this.calculator.element).bind(
        calculator.events.EXPRESSION_READY,
        function(aEvent) {
            me.updateModel();
            me.calculator.element.trigger(calculator.events.MODEL_READY);
            me.disableCalculateButton();
        }
    );

     $(this.calculator.element).bind(
        calculator.events.RESULT_READY,
        function(aEvent) {
            me.updateView();
            me.enableCalculateButton();
        }
    );

    $(this.calculator.element).bind(
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

