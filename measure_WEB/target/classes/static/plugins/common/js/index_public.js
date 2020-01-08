var sID = "";//获取session里的用户ID
var tabpanel;//初始化tab
var jcTabs = ['<iframe src="'+ iframeUrl +'" width="100%" height="100%" frameborder="0"></iframe>'];
/*
 * @Description: 预加载
 * @param :
 */
$(function() {
//	fnGetMenu();
//	name();
	fnInitTabPanel();
});
/*
 * @Description: 初始化tabpanel
 * @param :
 */
function fnInitTabPanel(){
	tabpanel = new TabPanel({
		renderTo: 'scrolltab',
		height: $(window).height()-60,
//		height: 520,
		//border:'none',  
		active: 0,
		//maxLength : 10,  
		autoResizable: true,
		widthResizable : true,
		heightResizable : true,
		items: [{
			id: '101',
			title: '夏厦测量系统',
			html: jcTabs[0],
			closable: false
		}]
	});
}

/*
 * @Description: 鼠标悬停事件
 * @param :
 */
function MouseOver(obj) {
	$(obj).addClass("open");
}
/*
 * @Description: 鼠标移开事件
 * @param :
 */
function MouseOut(obj) {
	$(obj).removeClass("open");
}
/*
 * @Description: 获取登录用户姓名
 * @param :
 */
function name() {
	$.ajax({
		url: pathUrl("user/getSession.action"),
		type: 'POST',
//		async : true,
		cache: false,
		dataType: 'JSON',
		success: function(data) {
			sID = data.id;
			$("#name").html(data.name);
		},
		error: function() {
//			Showbo.Msg.alert("登录失败！！！");
		}
	});
}
/*
 * @Description: 退出登录按钮点击事件
 * @param :
 */
$("#signOut").click(function() {
	showSwal(
		"退出",
		"您确定退出登录吗？",
		"是的，确定退出！",
		function(result){
			if(result.value){
				$.ajax({
					url: pathUrl("/removeSession.action"),
					type: 'POST',
//					async : true,
					cache: false,
					dataType: 'JSON',
					success: function(data) {
						if(data.code == "0000") {
							window.location.href = pathUrl("");
						} else {
							swal_Tips("退出登录失败！");
						}
					},
					error: function() {
						swal_Tips('系统出错,请联系管理员');
					}
				});
			}
		}
	);
})

/*
 * @Description: 修改密码按钮点击事件
 * @param :
 */
$("#modify").click(function() {
	$("#modifyModal").modal('show');
	$('#modifyForm')[0].reset();
	$("#old").hide();
	$("#new").hide();
});

/*
 * @Description: 修改密码模态框确定按钮点击事件
 * @param :
 */
$("#confirm").click(function() {
	var o = $("#oldpsd").val();
	var n = $("#newpsd").val();
	var s = $("#surepsd").val();
	var a = "";
	$.ajax({
		url: pathUrl("/verifyPassword.action"),
		type: 'POST',
		async: false,
		cache: false,
		dataType: 'JSON',
		data: {
			"id": sID,
			"password": o
		},
		success: function(data) {
//			console.log(data)
			if(data.code == "9999") {
				a = 1;
			}
		},
		error: function() {
//			Showbo.Msg.alert("登录失败！！！");
		}
	});
	if(o.length <= 0) {
		swal_Tips_Short("请输入原密码！！");
		$("#old").show();
		$("#new").hide();
		return false;
	} else if(a == 1) {
		swal_Tips_Short("您输入的密码有误，请重新输入！！");
		$("#old").show();
		$("#new").hide();
		return false;
	} else if(n.length <= 0 || s.length <= 0) {
		swal_Tips_Short("密码不能为空，请重新输入！！");
		$("#old").hide();
		$("#new").show();
		return false;
	} else if(n != s) {
		swal_Tips_Short("您输入的2次密码不一致，请重新输入！！");
		$("#old").hide();
		$("#new").show();
		return false;
	} else {
		$.ajax({
			url: pathUrl("user/updatePassword.action"),
			type: 'POST',
//			async : true,
			cache: false,
			dataType: 'JSON',
			data: {
				"uId": id,
				"uPassword": $("#newpsd").val()
			},
			success: function(data) {
//				console.log(data);
				if(data.code == "0000") {
					$("#old").hide();
					$("#new").hide();
					swal_Tips(data.message);
					$("#modifyModal").modal('hide');
//					logOut(2);
				} else if(data.code == "9999") {
					swal_Tips_Short(data.message);
				}
			},
			error: function() {
//				Showbo.Msg.alert("登录失败！！！");
			}
		});
	}
})
/*
 * @Description: 修改密码模态框取消按钮点击事件
 * @param :
 */
$("#cancel").click(function() {
	$("#modifyModal").modal('hide');
})

/*
 * @Description: 设置右键窗口
 * @param :
 */
var options = {
	items: [
		{
			text: '关闭当前',
			onclick: function() {
				if(tabId != "101") {
					tabpanel.kill(tabId);
				}
			}
		},
		{
			text: '关闭其他',
			//<![CDATA[
			onclick: function() {
				$(".tabpanel_mover li").each(
					function() {
						if($(this).attr('id') != tabId && $(this).attr('id') != "101") {
							tabpanel.kill($(this).attr('id'));
						}
					});
				$("#" + tabId).click();
			}
			//]]>
		}, {
			text: '关闭全部',
			onclick: function() {
				$(".tabpanel_mover li").each(function() {
					if($(this).attr('id') != "101") {
						tabpanel.kill($(this).attr('id'));
					}
				});
				$("#101").click();
			}
		}
	]
};

/*
 * @Description: 对每个菜单添加事件
 * @param :
 */
setTimeout(function(){
	$(".navbar-nav a").each(function() {
		$(this).click(function(e) {
//			console.log($(this).attr('addtabs'))
//			var id = "Tab_"+$(this).attr('addtabs');
			var id = $(this).attr('addtabs');
			tabpanel.addTab({
				id: id,
				title: $(this).text(),
				html: '<iframe src="' + $(this).attr('url') + '" width="100%" height="100%" frameborder="0"></iframe>',
				closable: true,
			});
			$('.' + id).children().attr('src', $('.' + id).children().attr('src'));
			$('.tabpanel_mover li').contextify(options);
		});
	});
},100);

var tabId;//从js中获取id

function getTabIdFormatter(param) {
	tabId = param;
};

/*
 * @Description: 浏览器窗口大小自适应
 * @param :
 */
$(window).resize(function() {
	window.setTimeout(function() {
		window.parent.document.getElementById("scrolltab").style.height = ($(window).height()-71) +"px";
		tabpanel.resize();
	}, 200);
});