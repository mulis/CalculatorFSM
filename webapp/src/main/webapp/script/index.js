$("document").ready(function () {

    var calculators = $("#calculators");
    var calculatorIdNumber = -1;
    var calculatorIdPrefix = "calculator";
    var calculatorId;

    function addCalculator() {
        calculatorIdNumber += 1;
        calculatorId = calculatorIdPrefix + calculatorIdNumber;
        calculators.append("<div id=\"" + calculatorId + "\"></div>")
        new Calculator(calculatorId, "/calculate");
    }

    function removeCalculator() {
        calculators.children().last().remove();
    }

    $("#addCalculatorButton").on("click", addCalculator);
    $("#removeCalculatorButton").on("click", removeCalculator);

});
