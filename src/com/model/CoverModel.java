package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoverModel implements Model {

   @Override
   public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
      return "humor/cover.jsp";
   }

}