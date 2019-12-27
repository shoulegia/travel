function toggleimage() {
    //焦点图效果
    //点击图片切换图片
    $('#img_list').on('mousemove', '.little_img', function () {
        $('.little_img').removeClass('cur_img');
        var big_pic = $(this).data('bigpic');
        $('.big_img').attr('src', big_pic);
        $(this).addClass('cur_img');
    });
    //上下切换
    var picindex = 0;
    var nextindex = 4;
    $('#img_list').on('click', '.down_img', function () {
        var num = $('.little_img').length;
        if ((nextindex + 1) <= num) {
            $('.little_img:eq(' + picindex + ')').hide();
            $('.little_img:eq(' + nextindex + ')').show();
            picindex = picindex + 1;
            nextindex = nextindex + 1;
        }
    });
    $('#img_list').on('click', '.up_img', function () {
        var num = $('.little_img').length;
        if (picindex > 0) {
            $('.little_img:eq(' + (nextindex - 1) + ')').hide();
            $('.little_img:eq(' + (picindex - 1) + ')').show();
            picindex = picindex - 1;
            nextindex = nextindex - 1;
        }
    });
    //自动播放
    var timer = setInterval("auto_play()", 5000);
}

//自动轮播方法
function auto_play() {
    var cur_index = $('.prosum_left dd').find('a.cur_img').index();
    cur_index = cur_index - 1;
    var num = $('.little_img').length;
    var max_index = 3;
    if ((num - 1) < 3) {
        max_index = num - 1;
    }
    if (cur_index < max_index) {
        var next_index = cur_index + 1;
        var big_pic = $('.little_img:eq(' + next_index + ')').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(' + next_index + ')').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
    } else {
        var big_pic = $('.little_img:eq(0)').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(0)').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
    }
}

//动态获取数据
function loadPage() {
    var rid = getParameter("rid");
    $.get("/route/findOne", {rid: rid}, function (route) {
        console.log(route)
        $("#title").html(route.rname);
        $("#pros_title").html(route.rname);
        $("#hot").html(route.routeIntroduce);
        $("#seller").html(route.seller.sname);
        $("#sellerPhone").html(route.seller.consphone);
        $("#sellerAdress").html(route.seller.address);
        $("#price").html(route.price);
        $("#favCount").html(route.count);


        $("#big_img").attr("src", route.rimage);

        var img_list = '<a class="up_img up_img_disable"></a>';

        for (var i = 0; i < route.routeImgList.length; i++) {
            if (i >= 4) {
                img_list += '<a title="" class="little_img" ' +
                    'data-bigpic="' + route.routeImgList[i].bigPic + '" style="display: none">\n' +
                    '<img src="' + route.routeImgList[i].smallPic + '">\n' +
                    '</a>'
            } else {
                img_list += '<a title="" class="little_img" ' +
                    'data-bigpic="' + route.routeImgList[i].bigPic + '">\n' +
                    '<img src="' + route.routeImgList[i].smallPic + '">\n' +
                    '</a>'
            }
        }
        img_list += '<a class="down_img down_img_disable" style="margin-bottom: 0;"></a>';
        $("#img_list").html(img_list)
    });
    toggleimage();
    isFavourite(rid)
}

//查询是否收藏
function isFavourite(rid) {
    $.get("/route/isFavourite", {rid: rid}, function (flag) {
        if (flag) {
            //已收藏
            $("#collect").addClass("already");
            $("#collect").attr("disabled", "disabled");
            $("#collect").removeAttr("onclick");
        } else {
            //未收藏
            $("#collect").removeClass("already");
            $("#collect").removeAttr("disabled");
        }
    })
}

//添加收藏
function addFav() {
    var rid = getParameter("rid");
    $.get("/user/findOne", {}, function (data) {
        if (data) {
            $.get("/route/addFavourite", {rid: rid}, function (data) {
                alert("收藏成功！");
                loadPage()
            })
        } else {
            alert("尚未登录,请前往登录");
            return
        }
    })
}