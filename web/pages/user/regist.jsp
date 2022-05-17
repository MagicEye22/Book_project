<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" content="text/html" http-equiv="Content-Type">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
    <%@include file="/pages/common/haed.jsp"%>

    <script type="text/javascript">

        $(function () {
            //用户名输入框绑定失去焦点事件
            $("#username").blur(function () {
                //获取用户名
                var username = this.value;

                $.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistUsername&username="+username,function (data) {
                   if (data.existUsername){
                       $("span.errorMsg").text("用户名已存在!");
                   }else{
                       $("span.errorMsg").text("用户名可用");
                   }

                });
            })



            //给验证码图片绑定单击事件
            $("#codeImg").click(function () {
                //在事件响应的function函数中有一个this对象，这个this对象是当前正在响应事件的dom对象
                //src属性表示img标签的图片路径。可读可写
               this.src="${basePath}kaptcha.jpg?d="+new Date();

            });


            //给注册绑定单击事件
            $("#sub_btn").click(function () {

                // 验证用户名：必须由字母，数字下划线组成，并且长度为5-12位
                //1,获取用户名输入框里的内容
                var username = $("#username").val();
                //去掉前后空格
                $.trim(username);
                //2，创建正则表达式
				var usernameRegExp=/^\w{5,12}$/;//\w 匹配字母或数字或下划线或汉字  {5,12}重复5到12次
				//3，使用test（）方法验证
				if (!usernameRegExp.test(username)){
					//获取显示错误的span便签
					$("span.errorMsg").text("用户名不合法!");

					return false;
				}

                //验证密码：必须由字母，下划线数字组成，并且长度位5-12位
                //1,获取用户名输入框里的内容
                var password = $("#password").val();
                //去掉前后空格
                $.trim(password);
                //2，创建正则表达式
                var passwordRegExp=/^\w{5,12}$/;//\w 匹配字母或数字或下划线或汉字  {5,12}重复5到12次
                //3，使用test（）方法验证
                if (!passwordRegExp.test(password)){
                    //获取显示错误的span便签
                    $("span.errorMsg").text("密码不合法!");

                    return false;
                }


                //确认密码：和密码相比较
                //获取确认密码
                var repwd = $("#repwd").val();
                //去掉前后空格
                $.trim(repwd);
                //和密码相比较
                if(repwd!=password){
                    $("span.errorMsg").text("确认密码和密码不一致");
                    return false;
                }

                // 邮箱验证：xxxxxx@xxx.com
                //获取邮箱内容
                var email = $("#email").val();
                //设置正则表达式
                var emailRegExp=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //比较
                if(!emailRegExp.test(email)){
                    $("span.errorMsg").text("邮箱格式不合法！");
                    return false;
                }
                //验证码:现在只需验证用户由输入即可
                var code = $("#code").val();
                //去掉前后空格
                $.trim(code);
                if (code==null||code==""){
                    $("span.errorMsg").text("验证码不能为空！");
                    return false;
                }

                $("span.errorMsg").text("");
            });
        });


    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                       <%-- <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        ${requestScope.msg}

                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                            value="${requestScope.name}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                            value="${requestScope.email}"
                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 90px;" id="code" name="code"/>
                        <img id="codeImg" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 130px;height: 40px">
                        <br/>
                        <br/>
                        <%--hideen 隐藏域--%>
                        <input type="hidden" name="action" value="regist" />
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>