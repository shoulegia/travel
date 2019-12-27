function load() {
    $.get("/user/findOne", {}, function (data) {
        if (data) {
            var msg = "欢迎回来，" + data.name;
            $("#span_username").html(msg);
            $(".login").css("display", "block");
            $(".login_out").css("display", "none")
        } else {
            $(".login").css("display", "none");
            $(".login_out").css("display", "block")
        }

    });

    //查询类别
    $.get("/category/findAllCatg", {}, function (data) {
        var lis = '<li class="nav-active"><a href="index.html">首页</a></li>'
        if (data) {
            for (var i = 0; i < data.length; i++) {
                lis += '<li ><a href="route_list.html?id=' + data[i].cid + '">' + data[i].cname + '</a></li>'
            }
        }
        lis += '<li><a href="favoriterank.html">收藏排行榜</a></li>'
        $("#category").html(lis);
    });

    //搜索框事件
    $("#searchBtn").click(function () {
        var rname = $("#search").val();
        var cid = getParameter("id");

        location.href = "http://localhost:18083/route_list.html?id="+cid+"&rname="+rname;
    })
}