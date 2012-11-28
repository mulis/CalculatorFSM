// CalculatorViewSimple

Calculator.prototype.View = function(calculator) {

    var me = this;

    this.calculator = calculator;

    this.name = "CalculatorViewSimple";
    this.loadTemplate(this.name);

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

};

Calculator.prototype.View.prototype.updateModel = function() {
    this.calculator.model.setExpression(this.controls.expressionInput.val());
};

Calculator.prototype.View.prototype.updateView = function() {
    var output = this.controls.resultOutput;
    output.append(this.calculator.model.toString() + "\n");
    output.scrollTop(output[0].scrollHeight - output.height());
};

Calculator.prototype.View.prototype.enableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', false);
};

Calculator.prototype.View.prototype.disableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', true);
};


Calculator.prototype.View.prototype.clearResultOutput = function() {
    this.controls.resultOutput.text("");
};

Calculator.prototype.View.prototype.loadTemplate = function(name) {

    var me = this;

    for (var i = 0; i < document.scripts.length; i++) {
        var scriptSrc = document.scripts.item(i).src;
        if (scriptSrc.indexOf(name) > -1) {
            name = scriptSrc.substring(0, scriptSrc.lastIndexOf(".js")) + ".tmpl";
        }
    }

    $.get(
        name
    )
    .success(function(data) {
        me.calculator.element.html(data);
        me.initControls();
     })
    .error(function(data) {
        me.calculator.element.text("Calculator view template file " + name + ".tmpl loading error.");
    })
    .complete(function(data) {
    })
    ;
};

Calculator.prototype.View.prototype.initControls = function() {

    var me = this;

    this.controls = {
         expressionInput : this.calculator.element.find(".calculator-expressionInput"),
         calculateButton : this.calculator.element.find(".calculator-calculateButton"),
         resultOutput : this.calculator.element.find(".calculator-resultTextArea"),
         clearButton : this.calculator.element.find(".calculator-clearButton")
     };

    this.controls.calculateButton.bind(
        "click",
        function() {
            me.calculator.element.trigger(me.calculator.events.EXPRESSION_READY);
        }
    );

    this.controls.expressionInput.bind(
        "keypress",
        function(aEvent) {
            var keycode = (aEvent.keyCode ? aEvent.keyCode : aEvent.which);
            if(keycode == "13"){
                me.calculator.element.trigger(me.calculator.events.EXPRESSION_READY);
            }
        }
    );

    this.controls.clearButton.bind(
        "click",
        function(aEvent) {
            me.clearResultOutput();
        }
    );

};
