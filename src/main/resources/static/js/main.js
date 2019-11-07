layui.use(['form', 'element', 'layer', 'jquery'], function () {
    $ = layui.jquery;
    //上次登录时间【此处应该从接口获取，实际使用中请自行更换】
    //icon动画
    $(".panel a").hover(function () {
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    }, function () {
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    })
    $(".panel a").click(function () {
        parent.addTab($(this));
    })




})
