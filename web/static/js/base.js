(function ($) {
    $.fn.extend({
        appendFocus: function (myValue) {
            var $t = $(this)[0];
            if (document.selection) {
                this.focus();
                sel = document.selection.createRange();
                sel.text = myValue;
                this.focus();
            }
            else if ($t.selectionStart || $t.selectionStart == '0') {
                var startPos = $t.selectionStart;
                var endPos = $t.selectionEnd;
                var scrollTop = $t.scrollTop;
                $t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
                this.focus();
                $t.selectionStart = startPos + myValue.length;
                $t.selectionEnd = startPos + myValue.length;
                $t.scrollTop = scrollTop;
            }
            else {
                this.value += myValue;
                this.focus();
            }
        }
    })
})(jQuery);
var base = {
    checkAll: function () {
        var check = $('#check-all').prop('checked');
        // 过滤disabled元素，屏蔽影响
        var items = $('input.check-item:not(input[disabled])');
        items.prop('checked', check);
    }
};
$(function () {
    $('body').find('select[readonly]').each(function (index) {
        var item_name = $(this).attr('name');
        var item_id = $(this).attr('id');
        if (typeof(item_id) == 'undefined') {
            item_id = "";
        }
        if (typeof(item_name) == 'undefined') {
            item_name = "";
        }
        $(this).after('<input type="hidden" value="' + $(this).val() + '" name="' + item_name + '" id="' + item_id + '"/>');
        $(this).attr('disabled', 'disabled')
    })
})