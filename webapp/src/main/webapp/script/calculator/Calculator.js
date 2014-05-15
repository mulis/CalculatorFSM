var Calculator = function (elementID, serviceURL, viewName) {

    this.element = $("#" + elementID);
    this.serviceURL = serviceURL;
    this.events = {
        EXPRESSION_READY : "expression-ready",
        MODEL_READY : "model-ready",
        RESULT_READY : "result-ready",
        SERVICE_ERROR : "service-error"
    };

    this.model = new Calculator.prototype.Model(this);
    this.controller = new Calculator.prototype.Controller(this);
    if (viewName && Calculator.prototype[viewName]) {
        this.view = new Calculator.prototype[viewName](this);
    } else {
        this.view = new Calculator.prototype.ViewSimple(this);
    }

};
