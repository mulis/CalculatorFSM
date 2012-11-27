Calculator.prototype.Model = function(calculator) {

    var me = this;

    this.calculator = calculator;

    this.properties = {
        "expression" : "",
        "result" : "",
        "error" : "",
        "position" : -1
    };

};

Calculator.prototype.Model.prototype.setExpression = function(expression) {
    this.properties.expression = expression;
};

Calculator.prototype.Model.prototype.getExpression = function(expression) {
    return this.properties.expression;
};

Calculator.prototype.Model.prototype.setResult = function(result) {
    this.properties.result = result;
};

Calculator.prototype.Model.prototype.getResult = function() {
    return this.properties.result;
};

Calculator.prototype.Model.prototype.setError = function(error) {
    this.properties.error = error;
};

Calculator.prototype.Model.prototype.getError = function() {
    return this.properties.error;
};

Calculator.prototype.Model.prototype.setPosition = function(position) {
    this.properties.position = position;
};

Calculator.prototype.Model.prototype.getPosition = function() {
    return this.properties.position;
};

Calculator.prototype.Model.prototype.setAll = function(data) {
    if (data) {
        this.properties.expression = data.expression;
        this.properties.result = data.result;
        this.properties.error = data.error;
        this.properties.position = data.position;
    };
};

Calculator.prototype.Model.prototype.getAll = function() {
    return {
        "expression" : this.properties.expression,
        "result" : this.properties.result,
        "error" : this.properties.error,
        "position" : this.properties.position
    };
};

Calculator.prototype.Model.prototype.toString = function() {
    var string = "";
    if (this.properties.result && this.properties.result != "") {
        string += this.properties.result + "\n";
    }
    else {
        string += this.properties.error + "\n";
    }
    return string;
};
