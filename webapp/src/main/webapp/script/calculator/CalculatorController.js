var CalculatorController = function(model, view, serviceURL) {

    var me = this;
    this.model = model;
    this.view = view;
    this.serviceURL = serviceURL;

    CalculatorControls.calculateButton.bind(
        "click",
        function() {
            $(document).trigger(CalculatorEvents.EXPRESSION_READY);
        }
    );

    $(document).bind(
        CalculatorEvents.EXPRESSION_READY,
        function() {
            me.calculate();
        }
    );

}

CalculatorController.prototype.calculate = function() {

    var me = this;

    $.post(
        me.serviceURL,
        {
            "expression" : me.model.getExpression()
        }
    )
    .success(function(data) {
        me.model.setAll(data);
        $(document).trigger(CalculatorEvents.RESULT_READY);
     })
    .error(function(data) {
        me.model.setAll({"error":"Calculation service error"});
        $(document).trigger(CalculatorEvents.SERVICE_ERROR);
    })
    .complete(function(data) {
    });
    ;

}
