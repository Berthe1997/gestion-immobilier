<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="<c:url value="assetss/css/headers.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="assetss/css/styliste.css" />" />
<link rel="stylesheet" href="assetss/fontes/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value="assetss/css/table.css" />" />	
	
		<!-- Custom fonts for this template-->
		<link href="<c:url value="/assets/backend/vendor/fontawesome-free/css/all.min.css" />" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<c:url value="/assets/backend/css/sb-admin-2.min.css" />" rel="stylesheet"> 
  <!-- Custom styles for this page -->
  <link href="<c:url value="/assets/backend/vendor/datatables/dataTables.bootstrap4.min.css" />" rel="stylesheet">
  <link rel="stylesheet" href="http://anijs.github.io/lib/anicollection/anicollection.css">
  <link rel="stylesheet" type="text/css" href="<c:url value="/assets/datepicker/dist/css/bootstrap-datepicker.min.css" />" rel="stylesheet">
 
  <style type="text/css">
        .preloader {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 9999;
            /* background-color: #fff; */
        }

        .pre {
            border: 1px solid grey;
            min-height: 10em;
        }

        .preloader .loading {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            font: 14px arial;
        }
  </style>