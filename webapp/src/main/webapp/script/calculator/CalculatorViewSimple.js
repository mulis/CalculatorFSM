// CalculatorViewSimple

Calculator.prototype.ViewSimple = function (calculator) {

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

Calculator.prototype.ViewSimple.prototype.updateModel = function () {
    this.calculator.model.setExpression(this.controls.expressionInput.val());
};

Calculator.prototype.ViewSimple.prototype.updateView = function () {
    var output = this.controls.resultTextArea.prop('value');
    output += this.calculator.model.getExpression() + "\n";
    output += "=" + this.calculator.model.toString() + "\n";
    this.controls.resultTextArea.prop('value', output);
    this.controls.resultTextArea.prop('scrollTop', this.controls.resultTextArea.prop('scrollHeight'));
};

Calculator.prototype.ViewSimple.prototype.enableCalculateButton = function () {
    this.controls.calculateButton.prop('disabled', false);
};

Calculator.prototype.ViewSimple.prototype.disableCalculateButton = function () {
    this.controls.calculateButton.prop('disabled', true);
};


Calculator.prototype.ViewSimple.prototype.clearResultOutput = function () {
    this.controls.resultTextArea.prop('value', '');
};

Calculator.prototype.ViewSimple.prototype.loadTemplate = function (name) {

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

Calculator.prototype.ViewSimple.prototype.initControls = function () {

    var me = this;

    this.controls = {
         expressionInput : this.calculator.element.find(".calculator-expressionInput"),
         calculateButton : this.calculator.element.find(".calculator-calculateButton"),
        resultTextArea: this.calculator.element.find(".calculator-resultTextArea"),
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
