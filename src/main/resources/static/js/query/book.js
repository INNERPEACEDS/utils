layui.use('laydate', function(){
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#bookStartCreateDate' ,//指定元素
    });
    laydate.render({
        elem: '#bookEndCreateDate' ,//指定元素
    });
});

layui.use(['table','upload'], function(){
    var upload = layui.upload;
    var table = layui.table;
    table.render({
        elem: '#bookTable'
        , height: 400
        , url: '/downloadAndUpload/queryBookRecord'
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
            , limits: [10,20,30,40,50]
        }
        ,cols: [[//表头
            {field: 'index',title:'序号',templet: function (d) {
                    return d.LAY_INDEX;
            }},
            {field: 'id', title: '书籍编号'},
            {field: 'name', title: '书籍名称'},
            {field: 'remarks', title: '备注'},
            {field: 'createTime', title: '创建时间'},
            {field: 'updateTime', title: '更新时间'},
            {field: 'operating', title: '操作', align:'center', toolbar:'#operating'}
        ]]
        ,id: "bookReload"
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
            var bookId = $('#bookId');
            var bookName = $('#bookName');
            var bookRemarks = $('#bookRemarks');
            var bookStartCreateDate = $('#bookStartCreateDate');
            var bookEndCreateDate = $('#bookEndCreateDate');
            //执行重载
            table.reload('bookReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    id: bookId.val()
                    ,name: bookName.val()
                    ,remarks: bookRemarks.val()
                    ,startCreateDate: bookStartCreateDate.val()
                    ,endCreateDate: bookEndCreateDate.val()
                }
            });
        }
    };

    $('#queryBooks').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    /*table中操作项*/
    table.on('tool(books)',function (obj) {
        var data = obj.data;
        var orderNo = data.id;
        var layEvent = obj.event;
        if(layEvent === 'details') {
            $.ajax({
                url: '/queryOrder/detail',
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

    /*上传功能*/
    // 上传图片
    upload.render({
        // 绑定元素
        elem: '#imgUpload',
        //上传接口
        url: '/downloadAndUpload/uploadByFastDFS',
        accept: 'images',
        acceptMime: 'image/*',
        // 上传完毕回调
        done: function (res) {
            if (res.ok) {
                console.log(res.data.fileId);
                $('#img').val(res.data.fileId);
                $('#imgUpload').html("<img src=" + res.data.url + ">");
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

    // 上传协议
    //选完文件后不自动上传
    upload.render({
        elem: '#bizAgreementUpload',
        url: '/downloadAndUpload/uploadByFastDFS',
        accept: 'file',
        exts: 'zip|rar|7z',
        done: function (res) {
            console.log(res.data);
            if (res.ok) {
                $('#bizAgreement').val(res.data.fileId);
                $('#bizAgreementBtn').html("<i class='layui-icon layui-icon-ok-circle'></i>上传完成");
                layer.msg('上传完成', {
                    icon: 1
                });
            } else {
                layer.msg(res.msg, {
                    icon: 5
                });
            }
        }
    });
});

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
