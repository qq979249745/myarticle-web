window.onload=function (ev) {
    setInterval(function () {
        document.getElementById('time').innerText=getDateTime();
    },1000);
    var userLogin=document.getElementsByClassName("userLogin")[0];
    var userControl=document.getElementsByClassName("userControl")[0];
    userLogin.addEventListener("mouseover",function (evt) {
        userControl.style.display="block";
    });
    userLogin.addEventListener("mouseout",function (evt) {
        userControl.style.display="none";
    });
};
function login() {
    var iframe=document.getElementsByTagName("iframe")[0];
    iframe.src="login.jsp";
}
function register() {
    var iframe=document.getElementsByTagName("iframe")[0];
    iframe.src="register.jsp";
}
function index() {
    var iframe=document.getElementsByTagName("iframe")[0];
    iframe.src="search.jsp";
}
function modify() {
    var iframe=document.getElementsByTagName("iframe")[0];
    iframe.src="modify.jsp";
}
function logout() {
    var iframe=document.getElementsByTagName("iframe")[0];
    iframe.src="logout";
}
function myarticle(action) {
    var iframe=document.getElementsByTagName("iframe")[0];
    iframe.src="myinfo?action="+action;
}
function getDateTime() {
    var dt=new Date();
    var year=dt.getFullYear();
    var month=dt.getMonth()+1;
    var day=dt.getDate();
    var h=dt.getHours();
    var m=dt.getMinutes();
    var s=dt.getSeconds();
    month=month<10?"0"+month:month;
    day=day<10?"0"+day:day;
    h=h<10?"0"+h:h;
    m=m<10?"0"+m:m;
    s=s<10?"0"+s:s;
    return year+"年"+month+"月"+day+"日"+h+"时"+m+"分"+s+"秒";
}
