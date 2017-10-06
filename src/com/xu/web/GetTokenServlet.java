package com.xu.web;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.xu.test.SHA1Util;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/developerVerify")
public class GetTokenServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("==========enter token=======");
    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");
    String echostr = request.getParameter("echostr");
    String token = "106296486d2d6bf0a1fda9e5a292c6cc";//许振昌 md5
    //将token、timestamp、nonce三个参数进行字典序排序
    String [] arr = new String[]{token,timestamp,nonce};
    Arrays.sort(arr);
    String join = Joiner.on("").join(arr);
    String encode = SHA1Util.encode(join);
    System.out.println(encode+"-----------------"+signature);
    response.setCharacterEncoding("utf-8");
    ServletOutputStream out = response.getOutputStream();
    out.write(echostr.getBytes());
    out.flush();
    out.close();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.doPost(request, response);
  }
}
