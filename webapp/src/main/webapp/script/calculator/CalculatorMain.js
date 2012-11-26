var CalculatorEvents;

$("document").ready(function() {

    CalculatorEvents = {
        EXPRESSION_READY : "expression-ready",
        MODEL_READY : "model-ready",
        RESULT_READY : "result-ready",
        SERVICE_ERROR : "service-error"
    };

    var model = new CalculatorModel();
    var view = new CalculatorView(model);
    var controller = new CalculatorController(model, "http://localhost:8080/calculator/calculate");

});
