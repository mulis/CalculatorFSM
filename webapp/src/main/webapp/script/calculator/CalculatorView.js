// CalculatorViewSimple

Calculator.prototype.View = function (calculator) {
};

Calculator.prototype.View.prototype.loadTemplate = function (name) {

    var me = this;

    for (var i = 0; i < document.scripts.length; i++) {
        var scriptSrc = document.scripts.item(i).src;
        if (scriptSrc.indexOf(name) > -1) {
            name = scriptSrc.substring(0, scriptSrc.lastIndexOf(".js")) + ".tmpl";
        }
    }

    $.get(name)
        .success(function (data) {
            me.calculator.element.html(data);
            me.initControls();
        })
        .error(function (data) {
            me.calculator.element.text("Calculator view template file " + name + ".tmpl loading error.");
        })
        .complete(function (data) {
        })
    ;

};

