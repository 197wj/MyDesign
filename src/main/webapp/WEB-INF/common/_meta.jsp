<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var ="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="${path }/res/hui/favicon.ico" >
<link rel="Shortcut Icon" href="${path }/res/hui/favicon.ico" />

<link rel="stylesheet" type="text/css" href="${path }/res/hui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/hui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/hui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/hui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${path }/res/hui/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/kkpager/kkpager_blue.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/css/mycss.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/css/wode.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/css/ban-dan-dangqian.css" />
<link rel="stylesheet" type="text/css" href="${path }/res/css/jindutiao.css" />
<script type="text/javascript" src="${path }/res/hui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${path }/res/js/echarts.js"></script>
<script type="text/javascript" src="${path }/res/js/highcharts.js"></script>
<script type="text/javascript" src="${path }/res/hui/lib/hcharts/Highcharts/5.0.6/js/highcharts-more.js"></script>
<script type="text/javascript" src="${path }/res/hui/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript" src="${path }/res/kkpager/kkpager.js"></script>