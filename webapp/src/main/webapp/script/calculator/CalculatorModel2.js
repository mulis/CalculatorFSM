var CalculatorModel = function() {
    this.expression = "";
    this.result = "";
    this.error = "";
    this.position = -1;
};

CalculatorModel.prototype.setExpression = function(expression) {
    this.expression = expression;
}

CalculatorModel.prototype.getExpression = function(expression) {
    return this.expression;
}

CalculatorModel.prototype.setResult = function(result) {
    this.result = result;
}

CalculatorModel.prototype.getResult = function() {
    return this.result;
}

CalculatorModel.prototype.setError = function(error) {
    this.error = error;
}

CalculatorModel.prototype.getError = function() {
    return this.error;
}

CalculatorModel.prototype.setPosition = function(position) {
    this.position = position;
}

CalculatorModel.prototype.getPosition = function() {
    return this.position;
}

CalculatorModel.prototype.setAll = function(data) {
    if (data) {
        this.expression = data.expression;
        this.result = data.result;
        this.error = data.error;
        this.position = data.position;
    };
}

CalculatorModel.prototype.getAll = function() {
    return {
        "expression" : this.expression,
        "result" : this.result,
        "error" : this.error,
        "position" : this.position
    };
}

CalculatorModel.prototype.toString = function() {
    var string = "";
    if (this.result && this.result != "") {
        string += this.result + "\n";
    }
    else {
        string += this.error + "\n";
    }
    return string;
}
