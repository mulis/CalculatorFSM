var CalculatorEvents;
var CalculatorControls;

$("document").ready(function() {

    CalculatorEvents = {
        EXPRESSION_READY : "expression-ready",
        RESULT_READY : "result-ready",
        SERVICE_ERROR : "service-error"
    };

    CalculatorControls = {
        expressionInput : $("#expressionInput"),
        calculateButton : $("#calculateButton"),
        resultOutput : $("#resultTextArea"),
        clearButton : $("#clearButton")
    }

    var model = new CalculatorModel();
    var view = new CalculatorView(model);
    var controller = new CalculatorController(model, view, "http://localhost:8080/calculator/calculate");

});
