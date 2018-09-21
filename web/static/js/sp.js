// $(function () {
//
//     $('body').bind('mousemove', function (e) {
//
//
//     });
// });
var _obj = null;

window.onload=function(){

    document.body.addEventListener("mousemove", function (e) {
        var scroll = this.getWindowScrollPosition();
        var x =e.pageX;
        var y =e.pageY;
        var obj = document.elementFromPoint(x + scroll.left, y + scroll.top);
        if (_obj != obj && null != _obj) {
            removeClass(_obj,'_bg');
        }
        if(null!=obj){
            addClass(obj,'_bg');
            _obj = obj;
        }
        console.log(obj);
    });
};

function hasClass( elements,cName ){
    return !!elements.className.match( new RegExp( "(\\s|^)" + cName + "(\\s|$)") );
};

function addClass( elements,cName ){
    if( !hasClass( elements,cName ) ){
        elements.className += " " + cName;
    };
};
function removeClass( elements,cName ){
    if( hasClass( elements,cName ) ){
        elements.className = elements.className.replace( new RegExp( "(\\s|^)" + cName + "(\\s|$)" ), " " );
    };
};