function loadRoute(cid, currentPage, rname) {
    $.get("/route/pageQuery", {cid: cid, currentPage: currentPage, rname: rname}, function (data) {
        $("#totalCount").html(data.totalCount);
        $("#totalPage").html(data.totalPage);

        var lis = '';
        var first = '<li style="cursor: pointer" onclick="loadRoute(' + cid + ',1,\'' + rname + '\')"><a href="javascript:void(0);">首页</a></li>';

        //上一页
        var before = data.currentPage - 1;
        if (before <= 0) {
            before = 1
        }

        var prefix = '<li style="cursor: pointer" onclick="loadRoute(' + cid + ',' + before + ',\'' + rname + '\')" class="threeword"><a >上一页</a></li>';
        lis += first + prefix;

        var begin, end;
        if (data.totalPage < 10) {
            begin = 1;
            end = data.totalPage
        } else {
            begin = data.currentPage - 5;
            end = data.currentPage + 4;

            if (begin < 1) {
                begin = 1;
                end = begin + 9
            }
            if (end > data.totalPage) {
                begin = data.totalPage - 9;
                end = data.totalPage
            }
        }
        for (var i = begin; i <= end; i++) {
            if (data.currentPage == i) {
                lis += '<li class="curPage" onclick="loadRoute(' + cid + ',' + i + ',\'' + rname + '\')" style="cursor: pointer"><a href="javascript:void(0);" >' + i + '</a></li>'
            } else {
                lis += '<li onclick="loadRoute(' + cid + ',' + i + ',\'' + rname + '\')" style="cursor: pointer"><a href="javascript:void(0);" >' + i + '</a></li>'
            }
        }
        //下一页
        var next = data.currentPage + 1;
        if (next >= data.totalPage) {
            next = data.totalPage
        }
        var nextfix = '<li style="cursor: pointer" onclick="loadRoute(' + cid + ',' + next + ',\'' + rname + '\')" class="threeword"><a ">下一页</a></li>';
        var last = '<li style="cursor: pointer" onclick="loadRoute(' + cid + ',' + data.totalPage + ',\'' + rname + '\')" class="threeword"><a href="javascript:;">末页</a></li>';
        lis += nextfix + last;
        $("#pageNum").html(lis);
        var routelis = '';
        for (var i = 0; i < data.list.length; i++) {
            routelis += ' <li>\n' +
                '<div class="img"><img src="' + data.list[i].rimage + '" style="width: 299px"></div>\n' +
                '<div class="text1">\n' +
                '<p style="margin: 0">' + data.list[i].rname + '</p>\n' +
                '<br/>\n' +
                '<p style="margin:0">' + data.list[i].routeIntroduce + '</p>\n' +
                ' </div>\n' +
                ' <div class="price">\n' +
                '<p class="price_num">\n' +
                '<span>&yen;</span>\n' +
                '<span>' + data.list[i].price + '</span>\n' +
                '<span>起</span>\n' +
                '</p>\n' +
                '<p><a href="route_detail.html?rid=' + data.list[i].rid + '">查看详情</a></p>\n' +
                '</div>\n' +
                '</li>';
        }
        $("#route_list").html(routelis);
        window.scrollTo(0, 0)
    })
}