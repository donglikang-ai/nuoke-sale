layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url: '/order/list',
        method: 'post',
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            groups: 2, //只显示 1 个连续页码
            first: "首页", //显示首页
            last: "尾页", //显示尾页
            limits: [3, 10, 20, 30]
        },
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "userListTable",
        cols: [[
            {type: "checkbox", width: '5%', fixed: "left"},
            {field: 'createDate', title: '报修时间', width: '10%', align: "center"},
            {field: 'terminalName', title: '设备类型', width: '15%', align: 'center'},
            {field: 'faultName', title: '故障类型', width: '20%', align: "center"},
            {field: 'address', title: '地址', width: '25%', align: "center"},
            {
                field: 'status', title: '状态', width: '10%', align: "center", templet: function (d) {
                    if (d.status == "0") {
                        return "新订单";
                    } else if (d.status == "1") {
                        return "处理中";
                    } else if (d.status == "2") {
                        return "已关闭";
                    }
                }
            },
            {title: '操作', width: '15%', templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".orderStatus").val() != '') {
            table.reload("userListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //订单处理
    function doOrder(edit) {

        var index = layui.layer.open({
            title: "订单处理",
            type: 2,
            content: "/order/add",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".id").val(edit.id);
                    body.find(".createDate").val(edit.createDate);
                    body.find(".name").val(edit.name);
                    body.find(".mobile").val(edit.mobile);
                    body.find(".address").val(edit.address);
                    body.find(".terminalName").val(edit.terminalName);
                    body.find(".faultName").val(edit.faultName);
                    body.find(".faultInfo").val(edit.faultInfo);
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }



    function getMansList(){
        $.ajax({
            type:"GET",
            url:"/repairman/mansList",
            success:function(res){
                layer.close(index);
                if(res.success){
                    parent.layer.msg("处理成功!",{time:1500},function(){
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            },
            error:function(){
                layer.close(index);
                parent.layer.msg("处理失败!",{time:1500});
            }
        });
    }



    //列表操作
    table.on('tool(userList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            doOrder(data);
        }else if (layEvent === 'del') { //删除
            if(data.status==2){
                layer.msg('该订单已属于关闭状态');
            }else{
                layer.confirm('确定关闭此订单？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post("/order/close",{"id":data.id},function (res){
                        if(res.success){
                            layer.msg("关闭成功",{time: 1000},function(){
                                tableIns.reload();
                            });
                        }else{
                            layer.msg(res.message);
                        }

                    });
                });
            }

        }
    });

})
