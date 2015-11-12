var menu = [
    ["Home", "./index.xhtml", false],
    ["History", "./history.xhtml", false],
    ["Exit", "./exit.xhtml", false],
    ["Logoff", "./logoff.xhtml", false]
    // ["Setup", "./setup.html", false]
];

function menuList(active, type, style) {
    var active = typeof active !== 'undefined' ? active : 'Home';
    var html = "<form><ul";
    html += " class=" + type + " style=" + style + ">";
    for (var i = 0; i < menu.length; i++) {
        html += "<li";
        if (menu[i][0] == active) {
            html += " class='selected'";
        }
        html += "><a href='" + menu[i][1] + "'";
        //html += "><h:commandLink value='" + menu[i][1] + "'";
        if (menu[i][2])
            html += "onclick='window.open(this.href);return false;'";
        html += ">" + menu[i][0] + "<\/a><\/li>";
        //html += ">" + menu[i][0] + "<\/h:commandLink><\/li>";
    }
    return html + "<\/ul></form>";
}


if (navigator.userAgent.indexOf("IEMobile") >= 0) {
    document.body.className += " iemobile";
    window.onscroll = function() {
        window.scroll(0, 0);
    };
}


function showMenu() {
    document.getElementById("toggle-menu").className="no-hidden";
}
