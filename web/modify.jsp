<%@ page import="bean.UserBean" %>
<%@ page import="dao.ArticleDao" %>
<%@ page import="dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的资料</title>
    <link href="css/modify.css" type="text/css" rel="stylesheet">
    <script>
        function choseImg(files) {
            console.log(files.files[0]);
            var img=document.getElementsByClassName("icon")[0];
            var file=files.files[0];
            var reader=new FileReader();
            reader.readAsDataURL(file);
            reader.onloadstart=function () {
                console.log('文件上传处理......')
            };
            reader.onload = function(e){
                img.setAttribute('src',reader.result);
            };
        }
        function modify() {
            var file=document.getElementById("img");
            if(file.files.length>0){
                if(file.files[0].size<1024*1024){
                    var form=document.getElementsByTagName("form")[0];
                    form.submit();
                }else {
                    alert("图片大小不能大于1M");
                }
            }else{
                alert("请先选择图片！");
            }
        }
    </script>
</head>
<body>
<%
    UserBean userBean= (UserBean) session.getAttribute("userBean");
%>
<form enctype="multipart/form-data" method="post" action="modify">
    <table border="1">
        <tr>
            <td colspan="3">
                <img class="icon" src="displayImg?userName=<%=userBean.getUserName()%>">
            </td>
        </tr>
        <tr>
            <td>
                <a href="javascript:;" class="file">修改头像
                    <input id="img" accept="image/jpeg,image/jpg,image/png" type="file" name="image" onchange="choseImg(this)">
                </a>
            </td>
            <td>
                <input type="button" class="file" onclick="modify()" value="保存">
            </td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td><%=userBean.getUserName()%></td>
        </tr>
        <tr>
            <td>我发布的文章数目：</td>
            <td><%=new ArticleDao().getArticleCount("userName",userBean.getUserName())%>篇</td>
        </tr>
        <tr>
            <td>我评论过的文章数目：</td>
            <td><%=UserDao.queryCount(userBean.getUserName())%>篇</td>
        </tr>
    </table>
</form>
</body>
</html>
