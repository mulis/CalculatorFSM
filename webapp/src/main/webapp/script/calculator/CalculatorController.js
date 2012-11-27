Calculator.prototype.Controller = function(calculator) {

    var me = this;

    this.calculator = calculator;

    $(document).bind(
        calculator.events.MODEL_READY,
        function() {
            me.calculate();
        }
    );

}

Calculator.prototype.Controller.prototype.calculate = function() {

    var me = this;

    $.post(
        me.calculator.serviceURL,
        {"expression" : me.calculator.model.getExpression()}
    )
    .success(function(data) {
        me.calculator.model.setAll(data);
        $(document).trigger(me.calculator.events.RESULT_READY);
     })
    .error(function(data) {
        me.calculator.model.setAll({"error":"Calculation service error"});
        $(document).trigger(me.calculator.events.SERVICE_ERROR);
    })
    .complete(function(data) {
    });
    ;

}
