//====================================================================================================
// [插件使用] 请在此js文件内添加用例类型的实现
//====================================================================================================
function typeSelect(data){
	switch(data){
		case "WEB_CASE":web_case(data);break;
		case "WEB_SCENARIO":web_scenario(data);break;
	}
}

function web_case(data){
            dwrService.listWebCaseBeanByUser(data,function(dataList){
        		for(var i=0;i<dataList.length;i++){
        			$("<option></option>")
	                    .val(dataList[i].id +'_'+dataList[i].caseName)
	                    .text(dataList[i].id +' - '+dataList[i].caseName)
	                    .appendTo($("#taskData"));
        			$("#taskData").trigger("chosen:updated"); 
        		}
            });
}

function web_scenario(data){
    dwrService.listWebScenarioBeanByUser(data,function(dataList){
		for(var i=0;i<dataList.length;i++){
			$("<option></option>")
                .val(dataList[i].id +'_'+dataList[i].scenarioName)
                .text(dataList[i].id +' - '+dataList[i].scenarioName)
                .appendTo($("#taskData"));
			$("#taskData").trigger("chosen:updated"); 
		}
    });
}