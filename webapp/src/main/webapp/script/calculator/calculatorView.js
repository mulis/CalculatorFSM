var view = function() {
    var me = this;
}

view.prototype.onExpressionInput = function() {
    $(document).trigger(Events.CALCULATION);
}
