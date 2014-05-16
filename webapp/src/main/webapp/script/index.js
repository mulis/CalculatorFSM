$("document").ready(function () {

    var calculators = $("#calculators");
    var calculatorIdNumber = -1;
    var calculatorIdPrefix = "calculator";
    var calculatorId;

    function addCalculator(viewName) {
        calculatorIdNumber += 1;
        calculatorId = calculatorIdPrefix + calculatorIdNumber;
        calculators.append("<div id=\"" + calculatorId + "\"></div>");
        new Calculator(calculatorId, "/calculate", viewName);
    }

    function removeCalculator() {
        calculators.children().last().remove();
    }

    $("#addSimpleViewCalculatorButton").on("click", function () {
        addCalculator("ViewSimple");
    });
    $("#addButtonsViewCalculatorButton").on("click", function () {
        addCalculator("ViewButtons");
    });
    $("#addExpressViewCalculatorButton").on("click", function () {
        addCalculator("ViewExpress");
    });
    $("#removeCalculatorButton").on("click", removeCalculator);

});
