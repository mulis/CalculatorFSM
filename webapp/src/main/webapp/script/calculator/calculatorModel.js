var model = function() {
    var me = this;
    me.expression = "";
    me.result = "";
};

model.prototype.setExpression = function(expression) {
    me.expression = expression;
}

model.prototype.getExpression = function(expression) {
    return me.expression;
}

model.prototype.setResult = function(result) {
    me.result = result;
}

model.prototype.getResult = function() {
    return me.result;
}
