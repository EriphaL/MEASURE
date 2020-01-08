var flag = 0; //判断保存时是新增还是删除
var cId; //保存id，用于修改

/**
 * @method
 * @desc  显示新增窗口
 * @author gj
 * @date 2018年10月14日
 */
function showAdd() {
  $("#name,#code").val("");
  flag = 1;
  $("#myModal").modal("show");
  $("#myModalLabel").html("添加");
}

/**
 * 提交新增和修改
 */
function save() {
  //新增，修改
  // if (flag == 1) {
    //新增
    if ($("#name").val() == "") {
      toastr.warning("名称不得为空!");
      return false;
    }
    if ($("#code").val() == "") {
      toastr.warning("编号不得为空!");
      return false;
    }
    $.ajax({
      url: "product/add.action",
      type: "POST",
      dataType: "JSON",
      //添加后保存数据传入后端
      data: {
        productName: $("#productName").val(),
        productType: $("#productType").val()
      },
      success: function(data) {
        if (data.code == "0000") {
          toastr.success(data.message);
          $("#myModal").modal("hide");
          showTable();
        } else {
          toastr.error(data.message);
        }
      },
      error: function(request) {
        console.log(request);
      }
    });
    $("#productName,#productType").val("");
  // }
  // else if(flag==2){    //修改
  // 	if($("#name").val()==""){
  // 		toastr.warning("名称不得为空!");
  // 		return false;
  // 	}
  // 	if($("#code").val()==""){
  // 		toastr.warning("编号不得为空!");
  // 		return false;
  // 	}
  // 	$.ajax({
  // 		url:"DetectionItem/update.action",
  //         type: "POST",
  //         dataType:"JSON",
  //         //修改后保存数据传入后端
  //         data:{
  //         	jcxmId:cId,
  //         	jcxmName:$("#name").val(),
  //         	jcxmCode:$("#code").val(),
  //         	state:1

  //         	//jcxmId:row.jcxmId,
  //         	//state:0
  //         },
  //         success: function(data) {
  //         	if(data.code=="0000"){
  //         		toastr.success(data.message);
  //         		$("#myModal").modal('hide');
  // 	        	showTable();
  //         	}else{
  //         		toastr.error(data.message);
  //         	}
  //         },
  //         error: function(request) {
  //         	console.log(request);
  //         }
  // 	});
  // }
}

/**
 *新增和修改格式化
 */

function operate(value, row, index) {
  var str = "";
  str +=
    '<a class="btn btn-xs delete btnD" style="background-color: #f23333;color:white;width:60px;height:25px;line-height:22.5px;">删除</a>';
  return str;
}
/**
 * 操作点击事件
 */
window.operateEvents = {
  "click .update": function(e, value, row, index) {
    $("#myModal").modal("show");
    //显示数据,通过#name识别位置写入row.jcxmName
    $("#productName").val(row.productName);
    $("#productType").val(row.productType)
    // $("#jcxName").val(row.jcxmName);
    // $("#code").val(row.jcxmCode);
    // $("#myModalLabel").html("修改");
    // flag = 2;

    //cId用于保存修改
    // cId = row.productId;
  },
  "click .delete": function(e, value, row, index) {
    Showbo.Msg.confirm("是否确认删除？", function(btn) {
      if (btn == "yes") {
        $.ajax({
          url: "product/delete.action",
          type: "POST",
          dataType: "JSON",
          data: {
            productId: row.productId,
            state: 0
          },
          success: function(data) {
            if (data.code == "0000") {
              toastr.success(data.message);
            } else {
              toastr.error(data.message);
            }
            showTable();
          },
          error: function(request) {
            console.log(request);
          }
        });
      }
    });
  }
};

/**
 * 调用显示table接口
 */
showTable();

/**
 *
 * @method
 * @desc  显示检测项列表
 * @author gj
 * @date 2018年10月14日
 */
function showTable() {
  $("#table")
    .bootstrapTable("destroy")
    .bootstrapTable({
      url: "product/findAll.action", //请求后台的URL（*）
      queryParams: function(param) {
        return {
          limit: param.limit,
          offset: param.offset
        };
      }, // 传递参数（*）
      sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
      striped: false, // 是否显示行间隔色
      pagination: true, // 是否显示分页（*）
      sortable: false, // 是否启用排序
      sortOrder: "asc", // 排序方式
      //        toolbar:"#toolbar",
      pageNumber: 1, // 初始化加载第一页，默认第一页
      pageSize: 10, // 每页的记录行数（*）
      pageList: [10, 25, 50, 100], // 可供选择的每页的行数（*）
      strictSearch: true,
      clickToSelect: true, // 是否启用点击选中行
      height: 412, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
      columns: [
        {
          //区别于表格中表格中！的唯一的id
          field: 'productName',
          title: '产品名称',
          align: "center",
          valign: "middle",
          width: 300
        },
        {
          field: 'productType',
          title: '产品型号',
          align: "center",
          valign: "middle",
          width: 300
        },
        
        {
          field: "",
          title: '操作',
          align: "center",
          valign: "middle",
          width: 600,
          formatter: operate,
          events: operateEvents
        }
      ]
    });
}
