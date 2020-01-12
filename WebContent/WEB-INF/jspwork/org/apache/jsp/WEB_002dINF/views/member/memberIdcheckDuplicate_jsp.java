/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.47
 * Generated at: 2020-01-07 23:09:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberIdcheckDuplicate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');

	boolean isUsable = (boolean)request.getAttribute("isUsable");
	String memberId = request.getParameter("memberId");


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>아이디 중복 검사</title>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-3.4.1.js\"></script>\n");
      out.write("<style>\n");
      out.write("\t#checkId-container{\n");
      out.write("\t\ttext-align: center;\n");
      out.write("\t\tpadding-top: 50px;\n");
      out.write("\t}\n");
      out.write("\tspan#duplicated{\n");
      out.write("\t\tcolor: red;\n");
      out.write("\t\tfont-weight: bold;\n");
      out.write("\t}\n");
      out.write("</style>\n");
      out.write("<script>\n");
      out.write("\t//아이디 중복 검사 함수 : 팝업창 처리\n");
      out.write("\tfunction checkIdDuplicate(){\n");
      out.write("\t\tvar $memberId = $(\"#memberId_\");\n");
      out.write("\t\tif($memberId.val().trim().length < 4){\n");
      out.write("\t\t\talert(\"아이디는 4글자 이상 가능합니다.\");\n");
      out.write("\t\t\treturn;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\t//폼을 팝업창에 제출\n");
      out.write("\t\t//frm.target = 팝업창이름\n");
      out.write("\t\tvar url = \"");
      out.print(request.getContextPath());
      out.write("/member/checkIdDuplicate\";\n");
      out.write("\t\t\n");
      out.write("\t\tvar frm = document.checkIdDuplicateFrm;\n");
      out.write("\t\tfrm.memberId.value = $memberId.val().trim();\n");
      out.write("\t\tfrm.submit();\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction setMemberId(){\n");
      out.write("\t\tvar memberId = \"");
      out.print(memberId);
      out.write("\";\n");
      out.write("\t\tconsole.log(memberId);\n");
      out.write("\t\t//부모창의 폼 접근\n");
      out.write("\t\t//memberEnroll.jsp의 form이름이 memberEnrollFrm에 접근\n");
      out.write("\t\tvar frm = opener.memberEnrollFrm;\n");
      out.write("\t\tfrm.memberId.value = memberId;\n");
      out.write("\t\tfrm.idValid.value = 1;\n");
      out.write("\t\t\n");
      out.write("\t\tfrm.memberPwd.focus();\n");
      out.write("\t\t\n");
      out.write("\t\tself.close();\n");
      out.write("\t}\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div id=\"checkId-container\">\n");
      out.write("\t");
 if(isUsable){
      out.write("\n");
      out.write("\t\t[<span>");
      out.print(memberId );
      out.write("</span>]는 사용가능합니다.\n");
      out.write("\t\t<br /><br />\n");
      out.write("\t\t<button type=\"button\" onclick=\"setMemberId()\">사용</button>\n");
      out.write("\t");
 }
	   else{
	
      out.write("\n");
      out.write("\t\t[<span id=\"duplicated\">");
      out.print(memberId );
      out.write("</span>]는 이미 사용중입니다.\n");
      out.write("\t\t<br /><br />\n");
      out.write("\t\n");
      out.write("\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/member/memberIdCheckDuplicate\" name=\"checkIdDuplicateFrm\" method=\"post\">\n");
      out.write("\t\t\t<input type=\"text\" name=\"memberId\" id=\"memberId_\" placeholder=\"아이디를 입력하세요.\"/>\n");
      out.write("\t\t\t&nbsp;&nbsp;\n");
      out.write("\t\t\t<input type=\"button\" value=\"아이디 중복 검사\" onclick=\"checkIdDuplicate();\"/>\n");
      out.write("\t\t</form>\n");
      out.write("\t");
 } 
      out.write("\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
