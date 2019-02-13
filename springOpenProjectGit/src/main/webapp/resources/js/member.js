console.log("Member module..........")

var memberService = (function(){

	function getList(callback, error){
		
		$.getJSON('/open/member/list.json', function(data){
			if(callback){
				callback(data);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function getInfo(callback, error){
		$.getJSON('/open/member/info.json', function(data){
			if(callback){
				callback(data);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function insert(member, callback, error){
		$.ajax({
			type : 'post',
			url : '/open/member/new',
			data : JSON.stringify(member),
			contentType : 'application/json; charset=utf-8',
			success : function(result, status, er){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function remove(idx, callback, error){
		$.ajax({
			type : 'delete',
			url : '/open/member/' + idx,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function update(member, callback, error){
		$.ajax({
			type : 'put',
			url : '/open/member/' + member.idx,
			data : JSON.stringify(member),
			contentType : 'application/json; charset=utf-8',
			success : function(result, status, er){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error) {
					error(er);
				}
			}
		});
	}
	
	

	return {
		getList : getList,
		remove : remove,
		update : update,
		getInfo : getInfo
	};
	
})();