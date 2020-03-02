<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>TradeWins Ltd</title>
        <meta name="description" content="">
        <meta name="viewporddt" content="width=device-width, initial-scale=1">

       <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/reset.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/screen-07.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/screen-01.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.css">

                  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/styles.css">
                  <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
                  <script src="script.js"></script>
       	  	<link href="${pageContext.servletContext.contextPath}/css/nouislider.css" rel="stylesheet">


    </head>
    <script>
          	function getPage(obj,selectedId)
          	{
          	var url=obj;

          	document.ConsData.action=url;
          	document.ConsData.selectAdminPrivilegeId.value=selectedId;
          	document.ConsData.method="POST";
          	document.ConsData.submit();
          	}

          	function getPage2(obj)
            {
            var url=obj;

            document.ConsData.action=url;
            document.ConsData.method="POST";
            document.ConsData.submit();
            }

          </script>
    <body>

    <div id='cssmenu'>
    <ul>
       <li><a href='#'><span>Home</span></a></li>
       <li class='active has-sub'><a href='#'><span>Programs</span></a>
          <ul>
             <li class='has-sub'><a href='#'><span>All Programs</span></a>
             </li>
             <li class='has-sub'><a href='#'><span>Add Program</span></a>
             </li>

          </ul>
       </li>
       <li class='active has-sub'><a href='#'><span>Centers</span></a>
                 <ul>
                    <li class='has-sub'><a href='#'><span>All Centers</span></a>
                    </li>
                    <li class='has-sub'><a href='#'><span>Add Center</span></a>
                    </li>

                 </ul>
              </li>
       <li class='active has-sub'><a href='#'><span>Participants</span></a>
            <ul>
               <li class='has-sub'><a href='#'><span>Search Participants</span></a>
               </li>
               <li class='has-sub'><a href='#'><span>Add Participant</span></a>
               </li>

            </ul>
         </li>

    </ul>
    </div>

    </body></html>