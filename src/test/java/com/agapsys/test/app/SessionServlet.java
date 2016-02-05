/*
 * Copyright 2016 Agapsys Tecnologia Ltda-ME.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agapsys.test.app;

import com.agapsys.security.Secured;
import com.agapsys.security.web.WebSecurity;
import com.agapsys.web.action.dispatcher.ActionServlet;
import com.agapsys.web.action.dispatcher.HttpExchange;
import com.agapsys.web.action.dispatcher.WebAction;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Leandro Oliveira (leandro@agapsys.com)
 */
@WebServlet("/session/*")
public class SessionServlet extends ActionServlet {
	@WebAction
	public void doSimpleLogin(HttpExchange exchange) {
		SimpleUser user = new SimpleUser();
		WebSecurity.setCurrentUser(user);
	}
	
	@WebAction
	public void doExtraLogin(HttpExchange exchange) {
		ExtraUser user = new ExtraUser();
		WebSecurity.setCurrentUser(user);
	}
	
	@WebAction
	public void doAdminLogin(HttpExchange exchange) {
		AdminUser user = new AdminUser();
		WebSecurity.setCurrentUser(user);
	}
	
	
	@WebAction
	public void logout(HttpExchange exchange) {
		WebSecurity.unregisterCurrentUser();
	}
	
	@WebAction
	public void publicGet(HttpExchange exchange) throws IOException {
		exchange.getResponse().getWriter().print("OK");
	}
	
	@WebAction
	@Secured
	public void securedGet(HttpExchange exchange) throws IOException {
		exchange.getResponse().getWriter().print("OK");
	}
	
	@WebAction
	@Secured("ROLE")
	public void extraSecuredGet(HttpExchange exchange) throws IOException {
		exchange.getResponse().getWriter().print("OK");
	}
}
