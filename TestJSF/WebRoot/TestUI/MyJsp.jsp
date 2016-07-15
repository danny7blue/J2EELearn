<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:f="http://xmlns.jcp.org/jsf/core"
	xmlns:c="http://xmlns.jcp.org/jsp/jstl/core"
	xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions" 
	xmlns:p="http://primefaces.org/ui" lang="en">
	<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
   xmlns:f="http://java.sun.com/jsf/core"
   xmlns:h="http://java.sun.com/jsf/html"
   xmlns:p="http://primefaces.prime.com.tr/ui"
   version="2.0">
  <head>
    <base href="<%=basePath%>">
    
    <title>Parkopedia test page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    Test of Parkopedia <br>
    <script src="http://www.parkopedia.cn/js/embeds/mapView.js" data-Location="http://www.parkopedia.cn/%E5%81%9C%E8%BD%A6/%e5%8c%97%e4%ba%ac%e5%b8%82%e9%80%9a%e5%b7%9e%e5%8c%ba%e5%a6%82%e6%84%8f%e8%b7%af%e5%8c%97%e4%ba%ac%e5%b8%82%e9%80%9a%e5%b7%9e%e5%8c%ba%e5%95%86%e4%bc%9a/" data-options="l=1&amp;tc=1&amp;zc=1&amp;country=CN&amp;ts[]=4&amp;ts[]=3&amp;ts[]=2" data-size="750:400" type="text/javascript"></script>
    <p:commandButton actionListener="#{parkopediaRequestBuilder.getmPlainTextParameters()}" value="显示URL" id="displayURLBtn"></p:commandButton>
  </body>
</html>
