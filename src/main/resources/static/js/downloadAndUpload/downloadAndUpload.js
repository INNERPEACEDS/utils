//添加请求过滤
function addMers(){
    if(!uploadXls()){
        return false;
    }
    return true;
}

//上传文件检查
function uploadXls(){
    if(confirm("您确定要添加吗？")){
        var file = $("#uploadFile").val();
        //判断选的文件存在
        if(file==null || file==""){
            alert("请上传批量入驻文件！");
            return false;
        }
        var strs = file.split(".");
        var extensionName = strs[strs.length-1];

        //判断文件是否为excel格式
        /*if(extensionName=="xls"){
            /!* $("#form").submit(); *!/
        }else{
            alert("您上传的文件格式不正确，请上传excel格式的文件且后缀为xls！");
            return false;
        }*/
        return true;
    }
};
// 下载模板
function downloadFile() {
    window.open("download?fileName=pubKey");
    /*window.location.href = "<%=request.getContextPath()%>/../../file/download/download.png";*/
};
// 下载优秀书籍记录
function downloadBookRecord() {
    var bookId = $("#bookId").val();
    var bookName = $("#bookName").val();
    var bookRemarks = $("#bookRemarks").val();
    window.open("downloadBookRecord?id=" + bookId + "&name=" + bookName + "&remarks=" + bookRemarks);
}
// 查询优秀书籍记录
/*function queryBookRecord() {
    var bookId = $("#bookId").val();
    var bookName = $("#bookName").val();
    var bookRemarks = $("#bookRemarks").val();
    window.open("queryBookRecord?id=" + bookId + "&name=" + bookName + "&remarks=" + bookRemarks);
}*/
function batchAddDate() {
    window.open("batchAddCreateDate");
}