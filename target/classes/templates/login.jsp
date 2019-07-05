<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AngelBell Office Automation</title>
  <meta name="description" content="AngelBell Office Automation">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="/static/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="/static/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <script src="/static/assets/js/jquery.min.js"></script>
  <script src="/static/assets/js/amazeui.min.js"></script>
  <script src="/static/assets/js/app.js"></script>
  <script src="/static/assets/js/layui.js"></script>
  <link rel="stylesheet" href="/static/assets/css/amazeui.min.css" />
  <link rel="stylesheet" href="/static/assets/css/admin.css">
  <link rel="stylesheet" href="/static/assets/css/app.css">

</head>

<body data-type="login">

  <div class="am-g myapp-login">
	<div class="myapp-login-logo-block  tpl-login-max">
		<div class="myapp-login-logo-text">
			<div class="myapp-login-logo-text">
				AngelBell Office Automation<span> Login</span> <i class="am-icon-skyatlas"></i>
				
			</div>
		</div>

		<div class="login-font">
			<i>Log In </i> or <span> Sign Up</span>
		</div>
		<div class="am-u-sm-10 login-am-center">
			<form class="am-form" method="post" id="login-form-hooks">
				<fieldset>
					<div class="am-form-group">
						<input name="username" type="text" class="" id="doc-ipt-email-1" placeholder="请输入用户名">
					</div>
					<div class="am-form-group">
						<input name="password" type="password" class="" id="doc-ipt-pwd-1" placeholder="请输入密码">
					</div>
					<p><button type="submit" lay-submit lay-filter="login" class="am-btn am-btn-default">登录</button></p>
				</fieldset>
			</form>
		</div>
	</div>
</div>
    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
              // layer.msg('玩命卖萌中', function(){
              //   //关闭后的操作
              //   });
              //监听提交
              form.on('submit(login)', function(data){
              	alert("this is the from !");
                $.post('/login/doLogin', $('#login-form-hooks').serialize(), function(o){
                	alert("come in !");
					if(o.result == "01") {
						window.location.href = o.url;
					} else {
						layer.msg(data.msg);
					}
					$('.refcaptcha').click();
				}, 'json');
                return false;
              });
            });
        })
    </script>

</body>

</html>