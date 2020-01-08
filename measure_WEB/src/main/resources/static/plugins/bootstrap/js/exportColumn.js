(function($){
	$.fn.exportColumn = function(options){
		var that = $(this);
		initModal();
		fillModal(that);
		saveModal(that);
		
	}
//	初始化模态框
	function initModal(){
		var modal = '<div class="modal fade" id="filterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">'+
		  			'<div class="modal-dialog modal-dialog-centered" role="document">'+
		  			'<div class="modal-content">'+
		  			'<div class="modal-header">'+
		  			'<h5 class="modal-title" id="headTitle">选择要导出的列</h5>'+
		  			'<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top:-20px;opacity:1;">'+
		  			'<span aria-hidden="true">&times;</span>'+
		  			'</button>'+
		  			'</div>'+
		  			'<div class="modal-body">'+
		  			'<div class="dropdown" style="border-bottom:1px solid #ccc;">'+//筛选列下拉
		  			'<label class="form-check-label" id="allCheck">'+
				    '<input class="form-check-input" type="checkbox" value=""/>全选'+
				    '</label>'+
				    '</div>'+//筛选列下拉结束
				    '<div class="form-check" id="contentList" style="height:200px;overflow:auto;">'+//内容列
					'<label class="form-check-label">'+
					'<input class="form-check-input" type="checkbox" value=""/>没有可选内容'+
					'</label>'+
					'</div>'+//内容列结束
					'</div>'+
					'<div class="modal-footer">'+
					'<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>'+
					'<button type="button" class="btn btn-primary" id="saveChange">保存</button>'+
					'</div>'+
					'</div>'+
					'</div>'+
					'</div>';
		$('body').append(modal);
	}
	
//	在模态框中填充数据
	function fillModal(element){
		$('#filterModal').modal('show');
		var str = '';
		var column = element.bootstrapTable('getOptions').columns[0];
		for(var i=0;i<column.length;i++){
			if(column[i].title != undefined && column[i].field != 'operate'){
				str = str + '<label class="form-check-label"><input class="form-check-input" type="checkbox" data-field="'+column[i].field+'" value=""/>'+column[i].title+'</label><br>';
			}
		}
		$('#contentList').html(str);
		
//		全选功能
		$('#allCheck input').on('change',function(){
			if($(this).prop('checked')){
				$('#contentList input').each(function(){
					$(this).prop('checked', true);
				})
			}else{
				$('#contentList input').each(function(){
					$(this).prop('checked', false);
				})			
			}
		})
		$('#contentList input').on('change', function(){
			if($('#contentList input:checked').length != $('#contentList input').length){
				$('#allCheck input').prop('checked', false);
			}else{
				$('#allCheck input').prop('checked', true);
			}
		})
		$('#allCheck input').prop('checked',true);
		$('#allCheck input').trigger('change');//默认全选
	}
	
//	保存数据
	function saveModal(element){
		$('#saveChange').bind('click',(function(){
			return function(){
				var colArr = [];
				 var checkInp = $('#contentList input:not(:checked)');
				 checkInp.each(function(ind,item){
					 colArr.push($(item).data('field'));
				 })
				 colArr.push('0');
				 $('#filterModal').modal('hide');
				 element.bootstrapTable('refreshOptions',{colArr,colArr});
				$('#export').text('导出中...');
				$('#export').attr('disabled','disabled');	
				var setTime = setTimeout(function(){
					
					clearTimeout(setTime);
					$('#exportTableBtn').eq(0).trigger('click');
				},500);
				$('#saveChange').unbind('click');
			}
		})(element))
		
	}
})(jQuery)