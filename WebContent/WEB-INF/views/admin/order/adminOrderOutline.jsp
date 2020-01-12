<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	int count = 0;
	//그래프 날짜 변환작업
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
	String date = sdf.format(d);
	
	//최근 12달 배열생성
	String[] monthArr = new String[12];
	for(int i=0;i<monthArr.length;i++){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, count--);
		monthArr[i] = sdf.format(cal.getTime());
	}
	count=0;
	
	//최근 5년 배열 생성
	String[] yearArr = new String[5];
	for(int i=0;i<yearArr.length;i++){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
		cal.add(Calendar.YEAR, count--);
		yearArr[i] = sdf2.format(cal.getTime());
	}
	count=0;
	
	//흥미카테고리 배열 생성
	String[] hobbyArr = {"반려동물","육아","파티","운동","여행","캠핑"};
	
	//카테고리별 전체 판매 수량
	List<Integer> categoryCountList = (List<Integer>)request.getAttribute("categorySellCount");
	int[] categoryCountArr = {0,0,0,0,0,0};
	for(int i=0;i<categoryCountList.size();i++){
		categoryCountArr[i] = categoryCountList.get(i);
	}
	
	//월별 판매 판매금액 / 판매량
	List<Integer> montlyIncome = (List<Integer>)request.getAttribute("montlyIncome");
	int[] mIncome = {0,0,0,0,0,0,0,0,0,0,0,0};
	for(int i=0;i<montlyIncome.size();i++){
		mIncome[i] = montlyIncome.get(i);
	}
	List<Integer> montlySaleAmount = (List<Integer>)request.getAttribute("montlySaleAmount");
	int[] mAmount = {0,0,0,0,0,0,0,0,0,0,0,0};
	for(int i=0;i<montlySaleAmount.size();i++){
		mAmount[i] = montlySaleAmount.get(i);
	}
	
	//연도별 판매 판매금액 / 판매량
	List<Integer> yearlyIncome = (List<Integer>)request.getAttribute("yearlyIncome");
	int[] yIncome = {0,0,0,0,0};
	for(int i=0;i<yearlyIncome.size();i++){
		yIncome[i] = yearlyIncome.get(i);
	}
	List<Integer> yearlySaleAmount = (List<Integer>)request.getAttribute("yearlySaleAmount");
	int[] yAmount = {0,0,0,0,0};
	for(int i=0;i<yearlySaleAmount.size();i++){
		yAmount[i] = yearlySaleAmount.get(i);
	}
    
%>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawChart2);
      google.charts.setOnLoadCallback(drawChart3);
      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Category');
        data.addColumn('number', 'Count');
        data.addRows([
        <%for(int i = 0;i<hobbyArr.length;i++){%>
          ['<%=hobbyArr[i]%>', <%=categoryCountArr[i]%>],
        <%}%>
        ]);

        // Set chart options
        var options = {'title':'상품 카테고리별 판매 비율',
                       'width':1000,
                       'height':750};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('category_chart'));
        chart.draw(data, options);
      }
      
      function drawChart2() {
    	// Some raw data (not necessarily accurate)
          var data = google.visualization.arrayToDataTable([
            ['월', '판매금액', {type: 'string', role: 'tooltip'}, '판매량'],
            
            <%for(int i = 0;i<monthArr.length;i++){%>
	       		['<%=monthArr[11-i]%>', <%=mIncome[11-i]%>/10000, '<%=monthArr[11-i]%> 판매금액:<%=mIncome[11-i]/10000.0%>만원', <%=mAmount[11-i]%>],
	        <%}%>
          ]);

          var options = {
            title : '최근 1년간 월별 매출액/판매량 변화',
            seriesType: 'bars',
            series: {1: {type: 'line'}},
            'width':1000,
            'height':750,
            chartArea:{left:'15%',width:'50%',height:'75%'}};

          var chart = new google.visualization.ComboChart(document.getElementById('montly_chart'));
          chart.draw(data, options);
        }
      
      function drawChart3() {
    	// Some raw data (not necessarily accurate)
          var data = google.visualization.arrayToDataTable([
            ['년도', '판매금액', {type: 'string', role: 'tooltip'}, '판매량' ],
            <%for(int i = 0;i<yearArr.length;i++){%>
	       		['<%=yearArr[4-i]%>', <%=yIncome[4-i]%>/100000, '<%=yearArr[4-i]%> 판매금액:<%=yIncome[4-i]/100000.0%>십만원', <%=yAmount[4-i]%>],
	        <%}%>
          ]);

          var options = {
            title : '최근 5년간 연도별 매출액/판매량 변화',
            seriesType: 'bars',
            series: {1: {type: 'line'}},
            'width':1000,
            'height':750,
            chartArea:{left:'15%',width:'50%',height:'75%'}};
          
          var chart = new google.visualization.ComboChart(document.getElementById('yearly_chart'));
          chart.draw(data, options);
	  }
    </script>
    
    
    <div class="container-fluid contents">
		<div class="row itemTitle">
	    	<div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
	        	<h3>판매현황</h3>
	        </div>
	    </div>
	
		<div role="tabpanel" class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
		
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs nav-justified" role="tablist">
		    <li role="presentation" class="active"><a href="#category" aria-controls="category" role="tab" data-toggle="tab">카테고리별</a></li>
		    <li role="presentation"><a href="#monthly" aria-controls="monthly" role="tab" data-toggle="tab">월별</a></li>
		    <li role="presentation"><a href="#yearly" aria-controls="yearly" role="tab" data-toggle="tab">연도별</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="category">
		    	<div id="category_chart" style="display: block; margin: 0 atuo !important; width: auto"></div>
		    </div>
		    <div role="tabpanel" class="tab-pane" id="monthly">
		    	<div id="montly_chart" style="display: block; margin: 0 atuo !important; width: auto"></div>
		    </div>
		    <div role="tabpanel" class="tab-pane" id="yearly">
				<div id="yearly_chart" style="display: block; margin: 0 atuo !important; width: auto"></div>
			</div>
		  </div>
		
		</div>
	
		
	</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>