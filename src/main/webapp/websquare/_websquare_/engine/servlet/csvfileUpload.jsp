<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %><%
String ref = request.getHeader("referer");
String param = request.getParameter("gridID");
if(ref == null || ref.equals("") || param == null || param.equals("")) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return;
}

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv=Content-Type content="text/html;charset=UTF-8" />
<meta http-equiv=Cache-Control content=No-Cache />
<meta http-equiv=Pragma	content=No-Cache />
<title>FILE UPLOAD</title>
<script language="JavaScript">
    var vActionUrl = "";
    var domain = "";
    var processMsg = "";
    var type = "";
    var vappend = "";
    var gridID = "";
    var vfooter = "";
    var action = "";
    var dataList = "";
    var columnIds = ""
    var expression = ""
    var status = "";
    var optionParam = "";
    var maxFileSize = -1;
    var useModalDisable = "";
    var columnOrder = "";
    var useXHR = false;

    var Upload_ignore_spaces = "";
    var Upload_include_spaces = "";
    var Upload_advanced = "";
    var Upload_include = "";
    var Upload_not_include = "";
    var Upload_fill_hidden = "";
    var Upload_sheet_no = "";
    var Upload_starting_row = "";
    var Upload_starting_col = "";
    var Upload_footer = "";
    var Upload_file = "";
    var Upload_header = "";
    var Upload_file_header = "";
    var Upload_file_choose = "";
    var Upload_file_span = "";

    var Upload_msg1 = "";
    var Upload_msg2 = "";
    var Upload_msg3 = "";
    var Upload_msg4 = "";
    var Upload_msg5 = "";
    var Grid_warning9 = "";

    var paramObj;
    var isIE = navigator.appName.match(/Explorer/i) != null;
    var isMoz = navigator.userAgent.match(/Firefox/i) != null || (navigator.userAgent.match(/Gecko/i) != null && navigator.userAgent.match(/AppleWebKit/i) == null);
    var isOpera = navigator.userAgent.match(/Opera/i) != null;
    var isChrome = !isIE && (navigator.userAgent.match(/Chrome/i) != null);
    var isAndroid = /Android/.test(navigator.userAgent);
    var isIphone = /iPhone/.test(navigator.userAgent);
    var isIpad = /iPad/.test(navigator.userAgent);
    var isIpod = /iPod/.test(navigator.userAgent);
    var isSafari = false;
    if (typeof navigator.vendor != "undefined") {
        isSafari = navigator.vendor.indexOf("Apple") > -1;
    }

    window.onload = doInit;
    window.onbeforeunload = doFinish;
    
    function _safeInnerHTML(elem, str) {
        try {
            if (!elem || typeof elem.textContent !== "string") {
                return;
            }
            if (typeof str !== "string") {
                str = "";
            }
            if (str.indexOf("<") >= 0) {
                elem.textContent = "";
                var pattern1 = /<\s*script/ig;
                var pattern2 = /\s*\/\s*script\s*>/ig;
                var safeElem = "wq-safescr";
                str = str.replace(pattern1, "<" + safeElem).replace(pattern2, "/" + safeElem +">");
                if (location.hostname !== window.document.domain) {
                    var tempDiv = document.createElement("div");
                    tempDiv.innerHTML = str;
                    while (tempDiv.firstChild) {
                        elem.appendChild(tempDiv.firstChild);
                    }
                } else {
                    var parser = new DOMParser();
                    var bodyContent = parser.parseFromString(str, "text/html").body;
                    for (var i = 0; i < bodyContent.childNodes.length; i++) {
                        var node = bodyContent.childNodes[i];
                        if (node.nodeType !== 1 || node.tagName.toUpperCase() !== "SCRIPT") {
                            elem.appendChild(node.cloneNode(true));
                        }
                    }
                }
            } else {
                elem.textContent = str;
            }
        } catch (e) {
            console.error(e);
        }
    }
    
    function doInit() {
        if(opener.WebSquare.util.isOpera()) {
            check_fun();
        }

        try {
            domain = getParameter("domain");  
            if(domain) {
                document.domain = domain;
            }
        } catch (e) {
            opener.WebSquare.exception.printStackTrace(e);
        }

        var postMsg = getParameter("postMsg");
        if(postMsg == "true") {
            if(window.addEventListener) {
                window.addEventListener ("message", receiveMessage, false);
            } else {
                if(window.attachEvent) {  
                    window.attachEvent("onmessage", receiveMessage);
                }
            }
        }

        useModalDisable = getParameter("useModalDisable");
        if(useModalDisable == "true") {
            opener.WebSquare.layer.showModal();
        }
        Upload_ignore_spaces = opener.WebSquare.language.getMessage( "Upload_ignore_spaces" ) || "Ignore blank spaces";
        Upload_include_spaces = opener.WebSquare.language.getMessage( "Upload_include_spaces" ) || "Include blank spaces";
        Upload_advanced = opener.WebSquare.language.getMessage( "Upload_advanced" ) || "Advanced";
        Upload_hidden_values = opener.WebSquare.language.getMessage( "Upload_hidden_values" ) || "Hidden values";
        Upload_include = opener.WebSquare.language.getMessage( "Upload_include" ) || "Include";
        Upload_not_include = opener.WebSquare.language.getMessage( "Upload_not_include" ) || "Not include";
        Upload_fill_hidden = opener.WebSquare.language.getMessage( "Upload_fill_hidden" ) || "Fill Hidden";
        Upload_starting_row = opener.WebSquare.language.getMessage( "Upload_starting_row" ) || "Start row";
        Upload_file = opener.WebSquare.language.getMessage( "Upload_file" ) || "File Upload";
        Upload_fill = opener.WebSquare.language.getMessage( "Upload_fill" ) || "Fill";
        Upload_ignore = opener.WebSquare.language.getMessage( "Upload_ignore" ) || "Ignore";
        Upload_header = opener.WebSquare.language.getMessage( "Upload_header" ) || "Header";
        Upload_file_header = opener.WebSquare.language.getMessage( "Upload_file_header" ) || "File Upload";
        Upload_file_choose = opener.WebSquare.language.getMessage( "Upload_file_choose" ) || "Choose File";
        Upload_file_span = opener.WebSquare.language.getMessage( "Upload_file_span" ) || "No file chosen";

        Upload_msg2 = opener.WebSquare.language.getMessage( "Upload_msg2" ) || "File size exceeding the limit.";
        Upload_msg3 = opener.WebSquare.language.getMessage( "Upload_msg3" ) || "Normal processing failed.";
        Upload_msg4 = opener.WebSquare.language.getMessage( "Upload_msg4" ) || "Check the file extension.";
        Upload_msg5 = opener.WebSquare.language.getMessage( "Upload_msg5" ) || "Failed to reflect on the grid.";
        maxFileSize = getParameter("maxFileSize");
        maxFileSize = parseInt( maxFileSize, 10 );
        Grid_warning9 = opener.WebSquare.language.getMessage( "Grid_warning9", maxFileSize ) || "Data size exceeding the limit.\n limit : %1 byte";

        document.getElementById( "setting" ).textContent = Upload_advanced;
        document.getElementById( "sub" ).setAttribute( "summary", Upload_ignore_spaces + "," + Upload_hidden_values + "," + Upload_fill_hidden + "," + Upload_footer );
        document.getElementById( "advaned" ).textContent = Upload_advanced;
        document.getElementById( "space_option" ).textContent = Upload_ignore_spaces;
        document.getElementById( "file_header" ).textContent = Upload_file_header;
        document.getElementById( "choose_file" ).textContent = Upload_file_choose;
        document.getElementById( "choose_span" ).textContent = Upload_file_span;
        document.getElementById( "header_option").textContent =  Upload_header;

        var sel1 = document.getElementById( "spaceSelect" );
        sel1.options[0].text = Upload_ignore_spaces;
        sel1.options[1].text = Upload_include_spaces;
        document.getElementById( "start_option" ).textContent = Upload_starting_row;
        document.getElementById( "hidden_option").textContent = Upload_hidden_values;
        var sel2 = document.getElementById( "hiddenSelect" );
        sel2.options[0].text = Upload_include;
        sel2.options[1].text = Upload_not_include;
        document.getElementById( "hidden_fill").textContent = Upload_hidden_values;
        var sel3 = document.getElementById( "fillHidden" );
        sel3.options[0].text = Upload_fill;
        sel3.options[1].text = Upload_ignore;
    
        document.getElementById( "sendFILE").value =  Upload_file;

        //  header, append, hidden, columnNum, hiddenColumns, action
        type = getParameter("type");
        uploadType = getParameter("uploadType");
        var delim = getParameter("delim");
        var escapeChar = getParameter("escapeChar");
        var vheader = getParameter("header");
        vfooter = getParameter("footer");
        vappend = getParameter("append");
        var vhidden = getParameter("hidden");
        var fillHidden = getParameter("fillHidden");
        var advancedHidden = getParameter("advancedHidden");
        var skipSpace = getParameter("skipSpace");
        var vremoveColumns = getParameter("removeColumns");
        var actionUrl = getParameter("action");
        gridID = getParameter("gridID");
        var vcolumnNum = getParameter("columnNum");
        var expressionColumns = getParameter("expressionColumns");
        var vhiddenColumns = getParameter("hiddenColumns");
        var vheaderRows = getParameter("headerRows");
        var gridStartRow = getParameter("gridStartRow");
        //var gridStartCol = getParameter("gridStartCol");
        
        columnIds = getParameter("columnIds");

        processMsg = getParameter("processMsg");
        processMsg = opener.WebSquare.text.BASE64URLDecoder( processMsg );
        dataList = getParameter("dataList");
        expression = getParameter("expression");
        status = getParameter("status");
        optionParam = getParameter("optionParam");
        columnOrder = getParameter("columnOrder");
        useXHR = getParameter("useXHR");

        document.getElementById("domain").value = domain;
        document.getElementById("delim").value = delim;
        document.getElementById("escapeChar").value = escapeChar;

        // advancedHidden
        var advancedSetting = document.getElementById("advancedSetting");
        if( typeof advancedHidden == "string") {
            advancedHidden = opener.WebSquare.util.getBoolean(advancedHidden);
        }

        if( advancedHidden ) {
            advancedSetting.style.display = 'none';
            advancedSetting.style.visibility = 'hidden';
        }

        // hidden
        var myhidden =document.getElementsByName("hidden");
        if( typeof vhidden == "string") {
            vhidden  = opener.WebSquare.util.getBoolean(vhidden);
        }

        if( vhidden ) {
            myhidden[0].options[0].selected = true;
        } else {
            myhidden[0].options[1].selected =true;
        }

        // fillHidden
        var myfillHidden =document.getElementsByName("fillHidden");
        if( typeof fillHidden == "string" ) {
            fillHidden  = opener.WebSquare.util.getBoolean(fillHidden);
        }
        
        if( fillHidden ) {
            myfillHidden[0].options[0].selected = true;
        } else {
            myfillHidden[0].options[1].selected =true;
        }

        // skipSpace
        var skip_space =document.getElementsByName("skip_space");
        if( typeof skipSpace == "string" ) {
            skipSpace  = opener.WebSquare.util.getBoolean(skipSpace);
        }
        
        if( skipSpace ) {
            skip_space[0].options[0].selected = true;
        } else {
            skip_space[0].options[1].selected = true;
        }

        document.getElementById("removeColumns").value = vremoveColumns;
        document.getElementById("columnNum").value = vcolumnNum;
        document.getElementById("expressionColumns").value = expressionColumns;
        document.getElementById("hiddenColumns").value = vhiddenColumns;
        //document.getElementById("footer").value = vfooter;
        document.getElementById("header").value = vheader;
        document.getElementById("headerRows").value = vheaderRows;

        var el = opener.WebSquare.xml.getElementsByTagName(opener[gridID].element, "w2:gBody");
        var rows =opener.WebSquare.xml.getElementsByTagName(el[0],"w2:row");
        var myrows = rows.length;
        document.getElementById("bodyRows").value = myrows;

        document.getElementById("gridStartRow").value = gridStartRow;
        //document.getElementById("gridStartCol").value = gridStartCol;
        //document.getElementById("gridEndCol").value = gridEndCol;

    	var elemType = opener[gridID].element._elementType;
	    var gridStyle = "";
	    if (elemType === "json") {
	        gridStyle = opener.WebSquare.xmljs.json2xml(opener[gridID].element._element, {
                "changeKey" : {
                    "w2:select" : "w2:column" 
                } 
            });
	    } else {
	        gridStyle = opener.WebSquare.xml.noNameSpaceSerialize(opener[gridID].element._element);
	    }
        document.getElementById("gridStyle").value = gridStyle;
        document.getElementById("expression").value = expression;
        document.getElementById("optionParam").value = optionParam;
        document.getElementById("columnOrder").value = columnOrder;
        document.getElementById("useXHR").value = useXHR;

        with( document.__uploadForm__ ) {
            action = actionUrl;
        }

        if(opener.WebSquare.util.isSafari()) {
            setTimeout(function() {
                var bottomMargin = parseInt(document.height - document.documentElement.offsetHeight, 10) * -1||0;     
                if( bottomMargin != 0 ) {
                    self.resizeBy(0, bottomMargin);
                }
            }, 1);
        }
    }

    function doFinish() {
        if(useModalDisable == "true") {
            opener.WebSquare.layer.hideModal();
        }
    }

    function upload( thisForm ) {
        try {
            var filename = document.getElementById("filename").value;
            if( !filename || filename =="" ) {
                return false;
            }
            
            var isCSVFile = endsWith(filename.toLowerCase(), ".csv") == true ||
                            endsWith(filename.toLowerCase(), ".txt") == true;
            if( !isCSVFile ) {
                alert( Upload_msg4 );
                return false;
            }

            if( maxFileSize != -1 ) {
                var uploadFile = document.getElementById( "filename" );
                if( uploadFile && uploadFile.files ) {
                    if( maxFileSize < uploadFile.files[0].size ) {
                        alert( Grid_warning9 );
                        return;
                    }
                }
            }

            var frm = window.frames;
            var find = false;
            for( var i = 0; i < frm.length; i++ ) {
                if( frm[i].name == thisForm.target ) {
                    find = true;
                }
            }
            
            if( !find ) {
                var layerUP= document.createElement("div");
                var src = "";
                layerUP.style.border="1px solid blue";
                layerUP.style.width="100px";
                layerUP.style.height="100px";
                layerUP.style.visibility = "hidden";
                document.body.appendChild(layerUP);
                src = opener.WebSquare.net.getSSLBlankPage();
                _safeInnerHTML(layerUP, "<iframe frameborder='0px' name='" + thisForm.target + "' scrolling='no' style='width:100px; height:100px' " + src + "></iframe>");
            }
            
            showProcessMessage( processMsg );

            thisForm.submit();
        } catch(e) {
            alert(e.description);
        }
    }

    function returnData( vData ) {
        
        if( processMsg != "" ) {
            hideProcessMessage();
        }

        var doc = opener.WebSquare.xml.parse( vData );
        var exception = doc.getElementsByTagName("Exception");

        if( exception.length > 0) {
            var code = doc.getElementsByTagName("deniedCodeList")[0].firstChild;
            if( typeof code == "undefined" || code == null || code == "" ) {
                code = "";    
            } else {
                code = code.nodeValue;
            }

            if( code == "102" ) {
                msg = Upload_msg2;
            } else {
                var msg = doc.getElementsByTagName("message")[0].firstChild;
                if( typeof msg == "undefined" || msg == null || msg == "" ) {
                    msg = Upload_msg3;
                } else {
                    msg = msg.nodeValue;
                }
            }
            
            alert(msg);
        } else {
            var child = (doc.getElementsByTagName("array"))[0].firstChild.nodeValue;
            
            if( typeof vappend =="string" ) {
                vappend = opener.WebSquare.util.getBoolean(vappend);
            }
            
            var compId = "";
            try {
                
                var jsonArray = {
                        columnInfo:columnIds.split(","),
                        data:child
                 }
                
                if( dataList != "" ) {
                    compId = dataList;
                    var dcComp = opener.WebSquare.util.getComponentById(compId);
                    var preCnt = dcComp.getRowCount();
                    
                    if( uploadType == 1 || uploadType == 2 ) {
                        opener.window[compId].setArrayFile(jsonArray, vappend, gridID, uploadType);
                    }else{
                        opener.window[compId].setArray(jsonArray, vappend);
                    }

                    if( status == "C" ) {
                        var postCnt = dcComp.getRowCount();
                        if( vappend ) {
                                dcComp.modifyRangeStatus( preCnt, postCnt, "C" );
                        } else {
                            dcComp.modifyRangeStatus( 0, postCnt, "C" );
                        }
                    }

                } else {
                    compId = gridID;
                    var gridObj = opener.window[compId];
                    var preCnt = gridObj.getRowCount();
                    if( uploadType == 1 || uploadType == 2 ) {
                        opener.window[compId].setDataFile(child, vappend, jsonArray.columnInfo, compId, uploadType);
                    }else{
                        opener.window[compId].setData(child, vappend);
                    }

                    if( status == "C" ) {
                        var postCnt = gridObj.getRowCount()
                        if( vappend ) {
                            gridObj.modifyRangeStatus( preCnt, postCnt, "C" );
                        } else {
                            gridObj.modifyRangeStatus( 0, postCnt, "C" );
                        }
                    }

                }
                
                var fileNameDom = document.getElementById("filename")
                var fileName = fileNameDom.value;
                var fileNameArr = fileName.split("\\");
                opener.window[gridID].fireFileReadEnd( fileNameArr[fileNameArr.length-1] );
                window.self.close();
            } catch (e) {
                opener.WebSquare.exception.printStackTrace(e);
                alert( Upload_msg5 );
            }
        }
    }
    
    function receiveMessage(retObj) {
        try {
            returnData(retObj.data);
        } catch (e) {
            opener.WebSquare.exception.printStackTrace(e);
            alert( Upload_msg5 );
        }
    }

    function crossBrowserHeight() {
        if(opener.WebSquare.util.isIE(6)) {
            return 67;
        } else if(opener.WebSquare.util.isIE(7)) {
            return 67;
        } else if(opener.WebSquare.util.isIE(8)) {
            return 67;
        } else if(opener.WebSquare.util.isIE(9)) {
            return 67;
        } else if(opener.WebSquare.util.isIE(10)) {
            return 67;
        } else if(opener.WebSquare.util.isFF()) {
            //return 120;
            return 67;
        } else if(opener.WebSquare.util.isChrome()) {
            return 67;
            //return 123;
        } else if(opener.WebSquare.util.isSafari()) {
            return 67;
        } else if(opener.WebSquare.util.isOpera()) {
            return 67;
        } 
        return 67;
    }
    
    function check_fun() {
        var checkfun = document.getElementById('subcheck').checked;
        if( checkfun == true ) {
            document.getElementById('sub').style.display='block';
            var height = crossBrowserHeight();
            window.resizeBy(0 , height);//resizeBy ff7.0
        } else {
            document.getElementById('sub').style.display='none';
            var height = crossBrowserHeight();
			window.resizeBy(0 ,-1 * parseInt(height));//resizeBy ff7.0
        }
    }
    
    function showProcessMessage( processMsg ) {
        
        try {
            if(!processMsg || processMsg == "" ) { 
                return;
            }

            var processbar2_main = document.getElementById( "___processbar2" );
            var processbar2 = document.getElementById( "___processbar2_i" );
            var processMsgURL = opener.WebSquare.core.getConfiguration( "processMsgURL" );
            var processMsgHeight = opener.WebSquare.core.getConfiguration( "processMsgHeight" );
            var processMsgWidth = opener.WebSquare.core.getConfiguration( "processMsgWidth" );
            var processMsgBackground = opener.WebSquare.core.getConfiguration( "processMsgBackground" );
            var processMsgBackgroundColor = opener.WebSquare.core.getConfiguration( "/WebSquare/processMsgBackground/@backgroundColor" );
            if (processMsgBackgroundColor == ""){
                processMsgBackgroundColor = "#112233";
            }
            if( processMsgURL == "" ) {
                processMsgURL = opener.WebSquare.baseURI + opener.WebSquare.BootLoader.inquiresPath("message/processMsg.html");
            }
            
            processMsgURL = processMsgURL + "?param=" + opener.WebSquare.text.URLEncoder(processMsg);
            
            if( processMsgHeight == "" || processMsgWidth == "" ) {
                processMsgHeight = "74";
                processMsgWidth = "272";
            }

            WebSquare = opener.WebSquare;
            WebSquare.layer.processMsg = processMsg;
            
            if(!processbar2_main){
                var node2Main = document.createElement( "div" );
                node2Main.id = "___processbar2";
                node2Main.className = "w2modal";
                node2Main.style.backgroundColor = processMsgBackgroundColor;
                node2Main.tabIndex = 1;
                if(processMsgBackground == "true"){
                    node2Main.style.visibility = "visible";
                } else{
                    node2Main.style.visibility = "hidden";
                }
                
                node2Main.style.height = document.documentElement.clientHeight + "px";
                document.body.appendChild( node2Main );
                
                var e = e || event;
                if( e.preventDefault ) {
                    if( e.type == "keydown" ) {
                        e.preventDefault();
                    }
                } else {
                    if( e.type == "keydown" ) {
                        e.returnValue = false;
                    }
                }
                
            } else {
                processbar2_main.tabIndex = 1;
                processbar2_main.style.zIndex = 10000;
                processbar2_main.style.display = "block";
                processbar2_main.style.top = "0px";
                processbar2_main.style.left = "0px";
            }

            if( !processbar2 ) {
                var nTop = document.documentElement.scrollTop + document.documentElement.clientHeight/2 - parseInt(processMsgHeight)/2;
                var nLeft = document.documentElement.scrollLeft + document.documentElement.clientWidth/2 - parseInt(processMsgWidth)/2;

                var node2 = document.createElement("div");
                node2.id = "___processbar2_i";
                node2.style.position = "absolute";
                node2.style.top = parseInt(nTop) + "px";
                node2.style.left = parseInt(nLeft) + "px";
                node2.style.overflow = "hidden";
                node2.style.zIndex = 10001;
                node2.style.visibility = "visible";
                node2.style.height = processMsgHeight + "px";
                node2.style.width = processMsgWidth + "px";

                document.body.appendChild( node2 );
                _safeInnerHTML(node2, "<iframe frameborder='0' scrolling='no'ß name='__processbarIFrame' style='position:absolute; width:"+processMsgWidth+"px; height:"+ processMsgHeight +"px; top:0px; left:0px' src='" + processMsgURL + "'></iframe>");
                
            } else {
                var nTop = document.documentElement.scrollTop + document.documentElement.clientHeight/2 - parseInt(processMsgHeight)/2;
                var nLeft = document.documentElement.scrollLeft + document.documentElement.clientWidth/2 - parseInt(processMsgWidth)/2;
                processbar2.style.top = parseInt(nTop) + "px";
                processbar2.style.left = parseInt(nLeft) + "px";
                processbar2.style.zIndex = 10001;
                window.frames["__processbarIFrame"].location.replace( processMsgURL );
                processbar2.style.display = "block";
            } 
        } catch( e ) {
            opener.WebSquare.exception.printStackTrace(e);
        }
    }
    
    function hideProcessMessage() {
        try {
            var processbar2 = document.getElementById( "___processbar2" );
            var processbar2i = document.getElementById( "___processbar2_i" );
            if( typeof processbar2 != "undefined" && processbar2 != null ) {
                processbar2.style.zIndex = -1;
                processbar2.style.display = "none";
                processbar2.tabIndex = "-1";
                processbar2.textContent = '';
            }
            if( typeof processbar2i != "undefined" && processbar2i != null ) {
                processbar2i.style.zIndex = -1;
                processbar2i.style.display = "none";
            }
        } catch( e ) {
            opener.WebSquare.exception.printStackTrace(e);
        }
    }

    function getParameter(param) {
        var ret = "";
        try {
            if (!paramObj) {
                paramObj = {};
                var srch = location.search.substring(1);
                var arrayOfSrch = srch.split("&");
                for (var i = 0; i < arrayOfSrch.length; i++) {
                    var tmpArray = arrayOfSrch[i].split("=");
                    if (tmpArray.length == 2) {
                        paramObj[trim(tmpArray[0])] = trim(tmpArray[1]);
                    }
                }
            }
            ret = paramObj[param];
        } catch (e) {
            ret = "";
        }
        if (ret == null || typeof ret == 'undefined') {
            ret = '';
        }
        return decodeURI(ret);
    }

    function trim(str) {
        if (typeof str == "undefined" && str == null) return "";
        var leftTrimRegExp = new RegExp("^\\s\\s*");
        var rightTrimRegExp = new RegExp("\\s\\s*$");
        str = str.replace(leftTrimRegExp, '').replace(rightTrimRegExp, '');
        return str;
    }

    function getPopupParam() {
        try {
            var str = getParameter("modalParamIdx");
            return opener.WebSquare.net._getParam(str);
        } catch (e) {
            return "";
        }
    }

    function endsWith(str, s) {
        return str.substring(str.length - s.length, str.length) == s;
    }
</script>
<style type="text/css">
    html, body {margin:0px; padding:0px; font-family:"Malgun Gothic"; font-size:11px;}
    p {margin:0px; padding:0px;}
    img, fieldset {border:0;}
    table {width:100%; background:#fff; border-collapse:collapse; border-spacing:0; empty-cells:show;}
    table caption, table summary {width:0; height:0; font-size:0; line-height:0; overflow:hidden; visibility:hidden;}
    
    .none {display:none;}
    .block {display:block;}

    .wrap {width:444px; min-height:106px; border:1px solid #b3b3b3;}
    .header {height:27px; background:url(../../uiplugin/grid/upload/images/bg_header.gif) repeat-x left top;}
    .header .title {padding-left:28px; font-weight:bold; line-height:23px; background:url(../../uiplugin/grid/upload/images/bul_title.gif) no-repeat 11px 6px; float:left;}
    .header .title2 {padding-left:28px; font-weight:bold; line-height:23px; float:left;}
    .header span {padding-right:20px; float:right; display:block;}
    .header span input[type=checkbox] {position:relative; top:1px;}

    .content {padding:15px 10px 12px;}
    .content .filebox {padding:8px 0 0 11px; width:408px; height:33px; border:1px solid #d3d3d3; background:#f6f6f6;}
    .content .filebox input[type=file] {width:397px; height:21px; font-family:Verdana; font-size:12px; background:#fff;}

    .tbl {margin:15px auto 0; width:400px;}
    .tbl th, .tbl td {min-width:100px; height:24px; text-align:left;}
    .tbl th .dot {padding-left:14px; background:url(../../uiplugin/grid/upload/images/dot.gif) no-repeat left center;}
    .tbl td .ipt {width:74px; height:16px; /*bordeR:1px solid #abadb3;*/}
    .tbl td .sel {width:80px; height:20px;}
    .btn_file {margin-bottom:14px; width:90px; position:relative; left:333px;}
</style>

</head>
<body>
<form name="__uploadForm__" method="post" action="" enctype="multipart/form-data" target="__targetFrame__">
 
    <div class="wrap">
        <div class="header">
            <p class="title" id="file_header">File Upload</p>
            <span id="advancedSetting"><input type="checkbox" onclick="check_fun();" id="subcheck" /><label for="subcheck"><p id="setting" class="title">Advanced</p></label></span>
        </div>
        <div class="content">
            <div class="filebox">
                <button type="button" id="choose_file">Choose File</button>
                <span class="fileName" id="choose_span">No file chosen</span>
                <input type="file" id="filename" name="filename" style="display:none;"/>
            </div>
            <table id="sub" class="tbl none" summary="Ignore blank spaces,Hidden Values,Fill Hidden,Start Row">
            <caption id="advaned">Advanced</caption>
                <tr>
                    <th><label id="space_option" for="spaceSelect" class="dot">Ignore blank spaces :</label></th>
                    <td>
                        <select name="skip_space" class="sel" id="spaceSelect">
                            <option value="true">Ignore blank spaces</option>
                            <option value="false">Include blank spaces</option>
                        </select>
                    </td>
                    <th><label id="start_option" for="gridStartRow" class="dot">Start Row :</label></th>
                    <td>
                        <input type="text" id="gridStartRow" name="gridStartRow" class="ipt" />
                    </td>
                </tr>
                <tr>
                    <th><label id="hidden_option" for="hiddenSelect" class="dot">Hidden Values :</label></th>
                    <td>
                        <select name="hidden" class="sel" id="hiddenSelect">
                            <option value="true">Include</option>
                            <option value="false">Not include</option>
                        </select>
                    </td>
                    <th><label id="hidden_fill" for="fillHidden" class="dot">Fill Hidden :</label></th>
                    <td>
                        <select name="fillHidden" id="fillHidden">
                            <option value="true">Fill</option>
                            <option value="false">Ignore</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label id="header_option" for="header" class="dot">Header :</label></th>
                    <td colSpan="2">
                        <select name="header" class="sel" id="header">
                            <option value="true">Include</option>
                            <option value="false">Not include</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>

        <div class="foot">
            <p><input type="button" id="sendFILE" name="sendFILE" class="btn_file" value="File Upload" onclick="return upload(this.form)" /></p>
        </div>

    </div>
  <!-- working start -->
  <input type="hidden" id="domain" name="domain" value="" />
  <input type="hidden" id="delim" name="delim" value="" />
  <input type="hidden" id="escapeChar" name="escapeChar" value="" />
  <input type="hidden" id="footer" name="footer" value="" />
  <input type="hidden" id="removeColumns" name="removeColumns" value="" />
  <input type="hidden" id="expressionColumns" name="expressionColumns" value="CC" />
  <input type="hidden" id="hiddenColumns" name="hiddenColumns" value="" />
  <input type="hidden" id="headerRows" name="headerRows" value="" />
  <input type="hidden" id="columnNum" name="columnNum" value="" />
  <input type="hidden" id="bodyRows" name="bodyRows" value="" />
  <input type="hidden" id="gridStyle" name="gridStyle" value="" />
  <input type="hidden" id="expression" name="expression" value="" />
  <input type="hidden" id="optionParam" name="optionParam" value="" />  
  <input type="hidden" id="columnOrder" name="columnOrder" value="" />
  <input type="hidden" id="useXHR" name="useXHR" value="" />  
</form>
<script>
    var file = document.getElementById("filename");
    var fileBtn = document.getElementById("choose_file");
    var fileName = document.querySelector(".fileName");
    fileBtn.addEventListener("click", function() {
        file.click();
    });
    file.addEventListener("change", function() {
        if (file.value) {
            var name = file.value.split("\\").pop();
            fileName.textContent = name;
        }
    });
</script>
</body>
</html>