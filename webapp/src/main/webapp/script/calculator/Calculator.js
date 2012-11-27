//var CalculatorEvents;

var Calculator = function(elementID) {

    this.element = $("#" + elementID);

    this.serviceURL = "http://localhost:8080/calculator/calculate";

    this.events = {
        EXPRESSION_READY : "expression-ready",
        MODEL_READY : "model-ready",
        RESULT_READY : "result-ready",
        SERVICE_ERROR : "service-error"
    };

    this.model = new Calculator.prototype.Model(this);
    this.view = new Calculator.prototype.View(this);
    this.controller = new Calculator.prototype.Controller(this);

};

Calculator.prototype.init = function() {
//    this.model = new Calculator.prototype.Model();
}