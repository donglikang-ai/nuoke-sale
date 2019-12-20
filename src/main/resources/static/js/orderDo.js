layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            type: "POST",
            url: "/order/assign",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(data.field),
            success: function (res) {
                layer.close(index);
                if (res.success) {
                    parent.layer.msg("处理成功!", {time: 1500}, function () {
                        //刷新父页面
                        parent.location.reload();
                    });
                } else {
                    layer.msg(res.message);
                }
            },
            error: function () {
                layer.close(index);
                parent.layer.msg("处理失败!", {time: 1500});
            }
        });
        return false;
    })


    $.ajax({
        type: "GET",
        url: "/repairman/mansList",
        async: false,
        cache: false,
        success: function (data) {
            console.log("人员信息---->" + data.data)
            $.each(data.data, function (index, item) {
                $('#mans').append("<option value="+item.id+">"+item.name+"</option>");//往下拉菜单里添加元素
            })

            form.render();//菜单渲染 把内容加载进去
        },
        error: function () {
            layer.alert("维修人员加载失败，请刷新页面重试");
        }
    })

})