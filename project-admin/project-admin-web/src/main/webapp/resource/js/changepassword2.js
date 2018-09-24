LG.changepassword2 = function ()
{
    $(document).bind('keydown.changepassword', function (e)
    {
        if (e.keyCode == 13)
        {
            doChangePassword();
        }
    });
    var changePasswordWin2 = null;
    if (changePasswordWin2)
    {
    	$("#OldPassword2").val();
    	$("#NewPassword3").val();
    	$("#NewPassword22").val();
        changePasswordWin2.show();
       
    }
    else
    {
        var changePasswordPanle2 = $("#passwordForm2");
        changePasswordPanle2.ligerForm({
            fields: [
                { display: '旧密码', name: 'OldPassword2', type: 'password', validate: { maxlength: 50, required: true, messages: { required: '请输入密码'}} },
                { display: '新密码', name: 'NewPassword3', type: 'password', validate: { maxlength: 50, required: true,mimaqiangdu:true, messages: { required: '请输入密码'}} },
                { display: '确认密码', name: 'NewPassword22', type: 'password', validate: { maxlength: 50, required: true, equalTo: '#NewPassword3', messages: { required: '请输入密码', equalTo: '两次密码输入不一致'}} }
            ],  appendTo:$("#tishi")
        }
        );
        //验证
        jQuery.metadata.setType("attr", "validate");
        LG.validate(changePasswordPanle2, { debug: true });
        LG.validate(changePasswordPanle2);

        changePasswordWin2 = $.ligerDialog.open({
            width: 400,
            height: 220, top: 200,
            isResize: true,
            title: '用户修改密码',
            target : $("#passwordDetail2"),
            allowClose: false,
            buttons: [
            { text: '确定', onclick: function ()
            {
                doChangePassword();
            }
            }
            ]
        });
        
    }

    function doChangePassword()
    {
        var OldPassword = $("#OldPassword2").val();
        var NewPassword = $("#NewPassword3").val();
        if (changePasswordPanle2.valid())
        {
            LG.ajax({
                url: basepath+'SystemWeb/doChangePassword.do',
                data: { OldPassword: OldPassword, NewPassword: NewPassword },
                success: function (data,message)
                {
                
                    LG.showSuccess(message);
                    changePasswordWin2.hide();
                    $(document).unbind('keydown.changepassword');
                },
                error: function ()
                {
                    LG.showError("message");
                }
            });
        }
    }

};