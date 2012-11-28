var Calculator = function(elementID, serviceURL) {

    this.element = $("#" + elementID);
    this.serviceURL = serviceURL;
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
