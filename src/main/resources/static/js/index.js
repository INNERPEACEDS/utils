function loginInfo() {
    if (!confirm("确定要提交您的信息吗？")) {
        return false;
    }
    var boxHtml = '';
    boxHtml += '<div class="confirmBg"></div><div class="confirmBox">';
    boxHtml += '<form  name="subForm" action="loginInfo" method="post" style="margin-left: 85px;margin-top:28px;" >';
    boxHtml += '<input type="text" name="name" value="姓名"/>';
    boxHtml += '<input type="text" name="age" value="年龄"/>';
    boxHtml += '<input type="text" name="sex" value="性别"/>';
    boxHtml += '<div class="btn2121"><input type="button" id="btnSubmit" onclick = "checkSubmit()" class="btnBlue" value="确认提交" style="height:auto;"/>';
    boxHtml += '<a onclick="closeBox()" class="btnBlue">取消</a></form></div>';
    boxHtml += '</div>';
    $("body").append(boxHtml);
}
var checkSubmitFlag = false;
function checkSubmit() {
    if (!checkSubmitFlag) {
        console.log(1);
        // 第一次提交
        checkSubmitFlag = true;
        document.subForm.submit();
    } else {
        //重复提交
        alert("请等待处理完成");
        return false;
    }
}
/*关闭弹出的盒子*/
function closeBox() {
    $(".confirmBg").remove();
    $(".confirmBox").remove();
}