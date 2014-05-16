// CalculatorViewButtons

Calculator.prototype.ViewExpress = function (calculator) {

    var me = this;

    this.calculator = calculator;

    this.name = "CalculatorViewExpress";
    this.loadTemplate(this.name);

    $(this.calculator.element).bind(
        calculator.events.EXPRESSION_READY,
        function (aEvent) {
            me.updateModel();
            me.calculator.element.trigger(calculator.events.MODEL_READY);
        }
    );

    $(this.calculator.element).bind(
        calculator.events.RESULT_READY,
        function (aEvent) {
            me.updateView();
        }
    );

    $(this.calculator.element).bind(
        calculator.events.SERVICE_ERROR,
        function (aEvent) {
            me.updateView();
        }
    );

};

Calculator.prototype.ViewExpress.prototype.loadTemplate = Calculator.prototype.View.prototype.loadTemplate;

Calculator.prototype.ViewExpress.prototype.initControls = function () {

    var me = this;

    this.controls = {
        input: this.calculator.element.find(".calculator-expression-input"),
        canvas: this.calculator.element.find(".calculator-expression-canvas")
    };

    this.cursorPosition = 0;
    this.expression = "";

    this.controls.canvas.bind(
        "click",
        function () {
            me.controls.input.focus();
        }
    );

    this.controls.input.bind(
        "keydown",
        function (aEvent) {

            var keyCode = aEvent.keyCode || aEvent.which;

            if (keyCode == 13) { // enter
                me.calculator.element.trigger(me.calculator.events.EXPRESSION_READY);
            } else if (keyCode == 8) { // backspace
                if (me.cursorPosition > 0) {
                    me.cursorPosition -= 1;
                    me.expression = me.expression.substr(0, me.cursorPosition) + me.expression.substr(me.cursorPosition + 1);
                }
            } else if (keyCode == 46) { // delete
                if (me.cursorPosition < me.expression.length) {
                    me.expression = me.expression.substr(0, me.cursorPosition) + me.expression.substr(me.cursorPosition + 1);
                }
            } else if (keyCode == 37) { // left arrow
                if (me.cursorPosition > 0) {
                    me.cursorPosition -= 1;
                }
            } else if (keyCode == 39) { // right arrow
                if (me.cursorPosition < me.expression.length) {
                    me.cursorPosition += 1;
                }
            } else if (keyCode == 36) { // home
                me.cursorPosition = 0;
            } else if (keyCode == 35) { // end
                me.cursorPosition = me.expression.length;
            }

            me.displayExpression();

        }
    );

    this.controls.input.bind(
        "keypress",
        function (aEvent) {
            var keyCode = aEvent.keyCode || aEvent.which;
            me.expression = me.expression.substr(0, me.cursorPosition) + String.fromCharCode(keyCode) + me.expression.substr(me.cursorPosition);
            me.cursorPosition += 1;
            me.displayExpression();
        }
    );

};

Calculator.prototype.ViewExpress.prototype.displayExpression = function () {
    var output = this.expression.substr(0, this.cursorPosition).replace(/ /g, "&nbsp;") +
        "<span class=\"calculator-cursor\">|</span>" +
        this.expression.substr(this.cursorPosition).replace(/ /g, "&nbsp;");
    this.controls.canvas.html(output);
};

Calculator.prototype.ViewExpress.prototype.updateView = function () {
    this.controls.canvas.empty().append(this.convertModelToView());
};

Calculator.prototype.ViewExpress.prototype.convertModelToView = function () {

    var expression = this.calculator.model.getExpression();
    var token;

    while ((token = this.getToken(expression))) {

        if (token.type === this.tokens.PARENTHESIS_LEFT) {


        }

    }

};

Calculator.prototype.ViewExpress.prototype.tokens = {
    NUMBER: {
        type: "NUMBER",
        regexp: "[\+|\-]?\d*\.?\d+([eE][\+\-]?\d+)?"
    },
    OPERATOR_ADDITION: {
        type: "OPERATOR_ADDITION",
        regexp: "\+"
    },
    OPERATOR_SUBTRACTION: {
        type: "OPERATOR_SUBTRACTION",
        regexp: "\-"
    },
    OPERATOR_MULTIPLICATION: {
        type: "OPERATOR_MULTIPLICATION",
        regexp: "\*"
    },
    OPERATOR_DIVISION: {
        type: "OPERATOR_DIVISION",
        regexp: "\/"
    },
    OPERATOR_EXPONENTIATION: {
        type: "OPERATOR_EXPONENTIATION",
        regexp: "\^"
    },
    PARENTHESIS_LEFT: {
        type: "PARENTHESIS_LEFT",
        regexp: "\("
    },
    PARENTHESIS_RIGHT: {
        type: "PARENTHESIS_RIGHT",
        regexp: "\)"
    },
    FUNCTION_SQR: {
        type: "FUNCTION_SQR",
        regexp: "sqr"
    },
    FUNCTION_MIN: {
        type: "FUNCTION_MIN",
        regexp: "min"
    },
    FUNCTION_MAX: {
        type: "FUNCTION_MAX",
        regexp: "max"
    },
    FUNCTION_SUM: {
        type: "FUNCTION_SUM",
        regexp: "sum"
    },
    FUNCTION_PI: {
        type: "FUNCTION_PI",
        regexp: "pi"
    }
};
