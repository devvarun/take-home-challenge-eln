/**
 * 
 */
function analyseKeyword() {
	var title = "Analyse Keyword: ";
	
	var keyword = $("#keyword").val();
	var entry_text = $("#entry_text").val();
	
//	alert(keyword+'...'+entry_text);
	
	var json = {};
	json.entry_text = entry_text;
	json.keyword = keyword;
	var jsonStr = JSON.stringify( json);
	var reqObj = {
		url : "./eln/api/analyseKeyword",
		data : jsonStr
	};
	
	updateRequestDisplay(reqObj, title);
	
	standardPost(reqObj, title, function(data) {
		updateResponseDisplay(data, title);
		resizeResults();
	});
}


function resizeResults(){$( "#results-type-header" ).resizable();}

function standardPost(reqObj, title, successFunc, errFunc) {
	// clear out the title of the search in the JSON display...
	if (typeof (reqObj) == "undefined" && reqObj == null) {
		reqObj = {};
	}
	// extend posting object with repetitive attributes...
	$.extend(reqObj, {
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		type : "POST",
		success : function(data) {
			updateResponseDisplay(data, title + " done", this);
			if (typeof (successFunc) != "undefined" && successFunc != null)
				successFunc(data);
			resizeResults();
		},
		statusCode : {
			404 : function(data) {
				updateResponseDisplay(data, title + " done : NOT FOUND !!!", this);
				resizeResults();
			},
			500 : function(data) {
				updateResponseDisplay(data, title + " done : ERROR !!!", this);
				resizeResults();
			},
			502 : function(data) {
				updateResponseDisplay(data, title+ " done : APPLICATION ERROR!!!", this);
				resizeResults();
			},
		}
	});
	$.ajax(reqObj);
}


function updateRequestDisplay(data, title) {
	if (typeof (data) == "string") {
		$("#results-type").text(library.json.prettyPrint(JSON.parse(data)));
		return;
	}
	$("#rowDisplays").html("");
	var x = {};
	$.extend(x, data);
	if (typeof (x.data) == "string") {
		x.data = JSON.parse(x.data);
	}
	$("#request").html(library.json.prettyPrint(x));
	$("#results-type").text("");
}

function updateResponseDisplay(data, title) {
	if (typeof (data) == "string") {
		$("#results-type").text(data);
		return;
	}
	$("#results").html(library.json.prettyPrint(data));
	$("#results-type").text(title);
}



$(function() {
	$("#analyseKeyword").on("click", analyseKeyword);
});

if (!library)
	var library = {};

library.json = {
	replacer : function(match, pIndent, pKey, pVal, pEnd) {
		var key = '<span class=json-key>';
		var val = '<span class=json-value>';
		var str = '<span class=json-string>';
		var r = pIndent || '';
		if (pKey)
			r = r + key + "\"" + pKey.replace(/[": ]/g, '') + '"</span>: ';
		if (pVal)
			r = r + (pVal[0] == '"' ? str : val) + pVal + '</span>';
		return r + (pEnd || '');
	},
	prettyPrint : function(obj) {
		var jsonLine = /^( *)("[\w]+": )?("[^"]*"|[\w.+-]*)?([,[{])?$/mg;
		return JSON.stringify(obj, null, 3).replace(/&/g, '&amp;').replace(
				/\\"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;')
				.replace(jsonLine, library.json.replacer);
	}
};