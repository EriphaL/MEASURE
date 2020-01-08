var iframeUrl = pathUrl("MainPage.action"); //首页url

/*
 * @Description: 获取菜单
 * @param :
 */
getMenu();
function getMenu() {
  var data = "";
  //	data += "<li class='active'><a th:href=''>首页</a></li>";

  data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)'>";
  data +=
    "<a href='javascript:;' addtabs='101' url='MainPage.action'>夏厦测量系统</a>";
  data += "</li>";

  data +=
    "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  data +=
    "<a class='dropdown-toggle' data-toggle='dropdown' style='font-size:15px;' >录入页面<b class='caret'></b></a>";
  data += "<ul class='dropdown-menu'>";
  /*	data +=         "<li><a href='javascript:;' addtabs='106' url='MainPage.action'>控制图管理</a></li>";*/
  data +=
    "<li><a href='javascript:;' addtabs='102' url='HierarchicalType.action'>产品管理</a></li>";
  data +=
    "<li><a href='javascript:;' addtabs='103' url='DetectionItem.action'>检测项管理</a></li>";
  // data +=
  //   "<li><a href='javascript:;' addtabs='105' url='PoorDefinition.action'>不良定义</a></li>";
  // data +=
  //   "<li><a href='javascript:;' addtabs='106' url='EquipmentDetection.action'>检测设备管理</a></li>";
  // data +=
  //   "<li><a href='javascript:;' addtabs='119' url='historyExp.action'>经验库管理</a></li>";
  // data +=
  //   "<li><a href='javascript:;' addtabs='104' url='UserManagement.action'>用户管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='109' url='comPort.action'>COM端口设置</a></li>";

  //	data += 		"<li><a href='javascript:;' addtabs='107' url='ProcessCapability.action'>工序能力报表</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='108' url='GoodYield.action'>良品率报表</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='107' url='ToolLifeManagement.action'>刀具寿命管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='102' url='departmentjob.action'>岗位部门管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='103' url='supplierType.action'>供应商类型管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='104' url='supplier4erp.action'>供应商基础信息</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='105' url='job.action'>岗位管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='106' url='department.action'>部门管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='107' url='machineType.action'>设备类型管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='108' url='machine.action'>设备基础信息</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='109' url='materielType.action'>物料类型管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='110' url='materiel.action'>物料基础信息</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='111' url='process.action'>工艺基础信息</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='112' url='processRoute.action'>工艺路线管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='113' url='bom.action'>BOM管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='114' url='staffQuery.action'>员工查询</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='115' url='equipmentQuery.action'>设备查询</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='116' url='codeManagement.action'>编码管理</a></li>";
  data += "</ul>";
  data += "</li>";

  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'  style='font-size:15px;' >多图查看<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='110' url='viewComparison.action'>查看比较</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='111' url='newMonitor.action'>实时监控</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='112' url='MultiParameter.action'>多参数对比</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";

  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)'>";
  //	data += 	"<a href='javascript:;' addtabs='113' url='MultiProject.action'>多项目输入</a>";
  //	data += "</li>";
  // data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)'>";
  // data +=
  //   "<a href='javascript:;' addtabs='108' url='GoodYield.action'>良品率报表</a>";
  // data += "</li>";
  // data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)'>";
  // data +=
  //   "<a href='javascript:;' addtabs='107' url='ProcessCapability.action'>工序能力报表</a>";
  // data += "</li>";

  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'>生产管理<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='201' url='order.action'>订单管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='202' url='productionPlan.action'>生产计划管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='203' url='materialPlan.action'>物料计划管理</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";
  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'>库存管理<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='301' url='applyPurchase.action'>申请采购-填写</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='302' url='applyPurchaseAudit.action'>申请采购-待审核</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='303' url='applyPurchaseWrite.action'>申请采购-待完成</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='304' url='applyPurchaseAll.action'>申请采购-全部</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='305' url='applyShipment.action'>申请出货-填写</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='306' url='applyShipmentAudit.action'>申请出货-待审核</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='307' url='applyShipmentWrite.action'>申请出货-待完成</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='308' url='applyShipmentAll.action'>申请出货-全部</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='309' url='materialInventory.action'>物料库存记录</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='310' url='warehouseStock.action'>仓库库存记录</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";
  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'>统计中心<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='401' url='orderAll.action'>全部订单</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='402' url='orderStatistics.action'>订单量统计</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='403' url='chtj.action'>出货统计</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='404' url='cgtj.action'>采购统计</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";
  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'>权限管理<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='501' url='person.action'>员工管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='502' url='user4erp.action'>用户管理</a></li>";
  //	data += 		"<li><a href='javascript:;' addtabs='503' url='role4erp.action'>角色管理</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";
  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'>日志管理<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='601' url='log.action'>日志管理</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";
  //	data += "<li onmouseover='MouseOver(this)' onmouseout='MouseOut(this)' class='dropdown'>";
  //	data += 	"<a class='dropdown-toggle' data-toggle='dropdown'>系统设置<b class='caret'></b></a>";
  //	data += 	"<ul class='dropdown-menu'>";
  //	data += 		"<li><a href='javascript:;' addtabs='701' url='systemInformation.action'>系统基础设置</a></li>";
  //	data += 	"</ul>";
  //	data += "</li>";

  $("#nav").html(data);
}
