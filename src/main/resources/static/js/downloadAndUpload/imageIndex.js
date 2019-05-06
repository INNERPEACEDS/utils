// 下载优秀书籍记录
function downloadImage() {
    var id = $("#id").val();
    var serial = $("#serial").val();
    var name = $("#name").val();
    var address = $("#address").val();
    var unionId = $("#unionId").val();
    var description = $("#description").val();
    var startCreateDate = $("#startCreateDate").val();
    var endCreateDate = $("#endCreateDate").val();
    window.open("downloadImage?id=" + id + + "&serial"+ serial + "&name=" + name + "&address=" + address + "&unionId" + unionId + "&description" + description + "&startCreateDate=" +　startCreateDate + "&endCreateDate=" + endCreateDate);
}
function downloadImage1() {
    var id = $("#id").val();
    var serial = $("#serial").val();
    var name = $("#name").val();
    var address = $("#address").val();
    var unionId = $("#unionId").val();
    var description = $("#description").val();
    var startCreateDate = $("#startCreateDate").val();
    var endCreateDate = $("#endCreateDate").val();
    window.open("downloadImage1?id=" + id + + "&serial"+ serial + "&name=" + name + "&address=" + address + "&unionId" + unionId + "&description" + description + "&startCreateDate=" +　startCreateDate + "&endCreateDate=" + endCreateDate);
}
function getImage1() {
    var serial = $("#serial1").val();
    window.open("getImage1?id=" + serial, "_self");
}

/*使用layui*/
layui.use('laydate', function(){
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#startCreateDate' ,//指定元素
    });
    laydate.render({
        elem: '#endCreateDate' ,//指定元素
    });
});
layui.use(['table','upload'], function(){
    var upload = layui.upload;
    var table = layui.table;
    table.render({
        elem: '#imageTable'
        , height: 400
        , url: '/downloadAndUpload/queryImages'
        , method: 'post'
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.list //解析数据列表
            };
        }
        ,request: {
            pageName: 'pageNum'
            , limitName: 'pageSize'

        }
        ,page: {
            prev: '上一页'
            , next: '下一页'
            , limits: [5,10,15,20,25,30,35,40,45,50]
        }
        ,cols: [[//表头
            {field: 'index',title:'序号',templet: function (d) {
                    return d.LAY_INDEX;
                }},
            {field: 'id', title: '标识'},
            {field: 'serial', title: '序列号'},
            {field: 'name', title: '文件名称'},
            {field: 'address', title: '文件存储地址'},
            {field: 'description', title: '文件描述'},
            {field: 'unionId', title: '关联ID'},
            {field: 'createTime', title: '创建时间'},
            {field: 'updateTime', title: '更新时间'},
            {field: 'operating', title: '操作', align:'center', toolbar:'#operating'}
        ]]
        ,id: "imageReload"
        ,done: function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            //console.log(res);
            //得到当前页码
            //console.log(curr);
            //得到数据总量
            //console.log(count);
        }
    });
    var $ = layui.$, active = {
        reload: function(){
            var id = $("#id").val();
            var serial = $("#serial").val();
            var name = $("#name").val();
            var address = $("#address").val();
            var unionId = $("#unionId").val();
            var description = $("#description").val();
            var startCreateDate = $("#startCreateDate").val();
            var endCreateDate = $("#endCreateDate").val();
            //执行重载
            table.reload('imageReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    id: id
                    ,serial: serial
                    ,name: name
                    ,address: address
                    ,unionId: unionId
                    ,description: description
                    ,startCreateDate: startCreateDate
                    ,endCreateDate: endCreateDate
                }
            });
        }
    };

    $('#queryImages').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    /*table中操作项*/
    table.on('tool(images)',function (obj) {
        var data = obj.data;
        var orderNo = data.id;
        var layEvent = obj.event;
        if(layEvent === 'details') {
            $.ajax({
                url: '/queryImage/detail',
                method: 'POST',
                dataType: 'json',
                data: {
                    orderNo: orderNo
                },
                success:function (data) {
                    openDetials(data);
                },
                error:function (data) {
                    alert(data.msg);
                }
            });
        }

        if (layEvent === 'add') {
            $.ajac({
                url: '/addBook',
                method: 'POST',
                dataType: 'json',
                data:{
                    orderNo: orderNo
                },
                success:function() {

                },
                error:function () {

                }
            });
        }

        if(layEvent === 'delete') {
            alert("该服务暂时不能使用");
        }
    });

    //拖拽上传
    upload.render({
        elem: '#test10'
        ,url: '/upload/'
        ,done: function(res){
            console.log(res)
            if (res.ok) {
                console.log(res.data.fileId);
                $('#img').val(res.data.fileId);
                $('#imgUpload').html("<img src=" + res.data.url + ">");
                layer.msg(res.msg, {
                    icon: 5
                });
            } else {
                layer.msg(res.msg, {
                    icon: 5
                });
            }
        },
        error: function () {
            // 请求异常回调
            layer.msg('上传失败', {icon: 5});
        }
    });
});

//显示订单详情
function openDetials(data){
    layer.open({
        type: 1,
        title: '详情',
        content: '<div class="order-details">\n' +
            '<h4>'+data.data.productname+'</h4>\n' +
            '<h5>'+data.data.merorderamt+'</h5>\n' +
            '<ul class="clearfix">\n'+
            '<li><label>当前状态</label><span>'+state(data.data.paystate)+'</span></li>\n' +
            '<li><label>首款时间</label><span>'+data.data.plattime+'</span></li>\n'+
            '<li><label>平台订单号</label><span>'+data.data.merorderid+'<\span></li>\n'+
            '</ul>'
    });
}

//显示订单详情
/*function openDetials(data){
    layer.open({
        type: 1,
        title: '详情',
        content: '<div class="order-details">\n' +
            '<h4>'+data.data.productname+'</h4>\n' +
            '<h5>'+data.data.merorderamt+'</h5>\n' +
            '<ul class="clearfix">\n'+
            '<li><label>当前状态</label><span>'+state(data.data.paystate)+'</span></li>\n' +
            '<li><label>首款时间</label><span>'+data.data.plattime+'</span></li>\n'+
            '<li><label>平台订单号</label><span>'+data.data.merorderid+'<\span></li>\n'+
            '</ul>'
    });
}*/

//支付状态
/*
function state(state) {
    if(state === 0) {
        return "未支付";
    }
    if (state === 1) {
        return "支付成功";
    }
    if (state === 2) {
        return "支付失败";
    }
}*/
