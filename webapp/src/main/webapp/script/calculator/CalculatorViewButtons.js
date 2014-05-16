// CalculatorViewButtons

Calculator.prototype.ViewButtons = function (calculator) {

    var me = this;

    this.calculator = calculator;

    this.name = "CalculatorViewButtons";
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

Calculator.prototype.ViewButtons.prototype.updateModel = function () {
    this.calculator.model.setExpression(this.controls.display.text());
};

Calculator.prototype.ViewButtons.prototype.updateView = function () {
    if (this.calculator.model.getError() == "") {
        this.controls.display.text(this.calculator.model.getValue());
    }
    else {
        var expression = this.calculator.model.getExpression();
        if (!expression) {
            expression = "";
        }
        var position = this.calculator.model.getPosition();
        if (!position) {
            position = 0;
        }
        var out = expression.substring(0, position) +
            "<span class='calculator-display-error'>" +
            expression.substring(position) + "</span>";
        this.controls.display.html(out);
    }
};

Calculator.prototype.ViewButtons.prototype.enableCalculateButton = function () {
    this.controls.calculateButton.prop('disabled', false);
};

Calculator.prototype.ViewButtons.prototype.disableCalculateButton = function () {
    this.controls.calculateButton.prop('disabled', true);
};

Calculator.prototype.ViewButtons.prototype.initControls = function () {

    var me = this;

    this.controls = {
         display : this.calculator.element.find(".calculator-display"),
         symbolButton : this.calculator.element.find(".calculator-button"),
         clearButton : this.calculator.element.find(".calculator-button-clear"),
         deleteButton : this.calculator.element.find(".calculator-button-delete"),
         calculateButton : this.calculator.element.find(".calculator-button-calculate")
     };

    this.controls.symbolButton.bind(
        "click",
        function() {
            me.controls.display.text(me.controls.display.text() + $(this).val());
        }
    );

    this.controls.clearButton.bind(
        "click",
        function() {
            me.controls.display.text("");
        }
    );

    this.controls.deleteButton.bind(
        "click",
        function() {
            var expression = me.controls.display.text();
            if (expression != "") {
                me.controls.display.text(expression.substring(0, expression.length - 1));
            }
        }
    );

    this.controls.calculateButton.bind(
        "click",
        function() {
            me.calculator.element.trigger(me.calculator.events.EXPRESSION_READY);
        }
    );

//    this.controls.display.bind(
//        "keypress",
//        function(aEvent) {
//            var keycode = (aEvent.keyCode ? aEvent.keyCode : aEvent.which);
//            if(keycode == "13"){
//                me.calculator.element.trigger(me.calculator.events.EXPRESSION_READY);
//            }
//        }
//    );

};

Calculator.prototype.ViewButtons.prototype.loadTemplate = Calculator.prototype.View.prototype.loadTemplate;
