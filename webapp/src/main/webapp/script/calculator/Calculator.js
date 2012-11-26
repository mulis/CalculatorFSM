//var CalculatorEvents;

var Calculator = function(elementID) {

    var CalculatorEvents = {
        EXPRESSION_READY : "expression-ready",
        MODEL_READY : "model-ready",
        RESULT_READY : "result-ready",
        SERVICE_ERROR : "service-error"
    };

    var calculatorElement = $("#" + elementID);

//    var model = new CalculatorModel();
//    var view = new CalculatorView(model);
//    var controller = new CalculatorController(model, "http://localhost:8080/calculator/calculate");

    init();

};

Calculator.prototype.init = function() {
    this.model = new Calculator.prototype.Model();
}