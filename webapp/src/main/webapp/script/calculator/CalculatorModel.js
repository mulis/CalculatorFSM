Calculator.prototype.Model = function() {

    var model = new Object();

    var _privates = {
        expression : "",
        result : "",
        error : "",
        position : -1
    };

    model.prototype.setExpression = function(expression) {
        _privates.expression = expression;
    }

    model.prototype.getExpression = function(expression) {
        return _privates.expression;
    }

    model.prototype.setResult = function(result) {
        _privates.result = result;
    }
    
    model.prototype.getResult = function() {
        return _privates.result;
    }
    
    model.prototype.setError = function(error) {
        _privates.error = error;
    }
    
    model.prototype.getError = function() {
        return _privates.error;
    }
    
    model.prototype.setPosition = function(position) {
        _privates.position = position;
    }
    
    model.prototype.getPosition = function() {
        return _privates.position;
    }
    
    model.prototype.setAll = function(data) {
        if (data) {
            _privates.expression = data.expression;
            _privates.result = data.result;
            _privates.error = data.error;
            _privates.position = data.position;
        };
    }
    
    model.prototype.getAll = function() {
        return {
            "expression" : _privates.expression,
            "result" : _privates.result,
            "error" : _privates.error,
            "position" : _privates.position
        };
    }
    
    model.prototype.toString = function() {
        var string = "";
        if (_privates.result && _privates.result != "") {
            string += _privates.result + "\n";
        }
        else {
            string += _privates.error + "\n";
        }
        return string;
    }

    return model;

};