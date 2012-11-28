// CalculatorViewButtons

Calculator.prototype.View = function(calculator) {

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

Calculator.prototype.View.prototype.updateModel = function() {
    this.calculator.model.setExpression(this.controls.display.text());
};

Calculator.prototype.View.prototype.updateView = function() {
    if (this.calculator.model.getError() == "") {
        this.controls.display.text(this.calculator.model.toString());
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

Calculator.prototype.View.prototype.enableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', false);
};

Calculator.prototype.View.prototype.disableCalculateButton = function() {
    this.controls.calculateButton.prop('disabled', true);
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
