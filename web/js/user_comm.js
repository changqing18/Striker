/**
 * Created by 28713 on 2016/12/11.
 */
function PreUrl() {
    let u = document.referrer;
    let date = new Date();
    date.setMinutes(date.getMinutes() + 15);

    if (u == "")
        u = "/index.html";

    if (u.indexOf("_info.html") === -1 &&
        u.indexOf("register.html") === -1 &&
        u.indexOf("login.html") === -1) {
        document.cookie = "preurl=\"" + u + "\"; expires=" + date.toUTCString() + "path=/";
    }
}
function getRequest() {
    let info = -1;
    let Email = "";
    let name = "";
    let requests = location.search;
    if (requests.indexOf("?") !== -1) {
        let str = requests.substr(1).split("&");
        try {
            info = decodeURIComponent(str[0].split("=")[1]);
            Email = decodeURIComponent(str[1].split("=")[1]);
            name = str[2].split("=")[1];
        } catch (ignored) {
        }
    }
    return [info,Email,name];
}