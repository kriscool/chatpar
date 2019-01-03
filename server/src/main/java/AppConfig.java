
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class AppConfig {

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl();
    }

    @Bean(name = "/hessian-service")
    public HessianServiceExporter exporterServer() {
        HessianServiceExporter hse = new HessianServiceExporter();
        hse.setServiceInterface(MessageService.class);
        hse.setService(messageService());
        return hse;
    }
    
    @Bean(name = "/burlap-service")
    public RemoteExporter burlapService() {
		BurlapServiceExporter exporter = new BurlapServiceExporter();
        exporter.setService(messageService());
        exporter.setServiceInterface( MessageService.class );
        return exporter;
    }
    
    @Bean(name = "/xml")
    public HttpRequestHandler xmlRpc() throws XmlRpcException {
		return new XmlRpc();
    }
}