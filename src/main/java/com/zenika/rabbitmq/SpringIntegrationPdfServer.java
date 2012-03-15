/**
 * 
 */
package com.zenika.rabbitmq;

import org.mortbay.jetty.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author acogoluegnes
 *
 */
public class SpringIntegrationPdfServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-integration-amqp-pdf-server.xml");
		PdfService gateway = ctx.getBean("pdfService",PdfService.class);
		
		Server server = new Server(8086);
		server.setHandler(new PdfWebHandler(gateway));
		server.start();	
		System.out.println("Spring Integration AMQP server started, try http://localhost:8086/?pdf-request=myRequest");
	}

}
