
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

@Service
public class XmlRpc implements HttpRequestHandler {
	private XmlRpcServletServer servlet ;
	public XmlRpc() throws XmlRpcException{
		this.servlet = new XmlRpcServletServer();
		PropertyHandlerMapping propertyHandlerMapping = new PropertyHandlerMapping();
		propertyHandlerMapping.addHandler("/xml-rpc", MessageService.class);
		
		XmlRpcServerConfigImpl config = new XmlRpcServerConfigImpl();
		config.setEnabledForExceptions(true);
		config.setEnabledForExtensions(true);
		
		servlet.setHandlerMapping(propertyHandlerMapping);
		servlet.setConfig(config);
	}
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servlet.execute(request,response);
		
	}

}
