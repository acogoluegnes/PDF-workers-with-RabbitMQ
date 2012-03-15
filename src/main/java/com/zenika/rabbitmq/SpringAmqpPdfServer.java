/**
 * 
 */
package com.zenika.rabbitmq;

import org.mortbay.jetty.Server;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author acogoluegnes
 *
 */
public class SpringAmqpPdfServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-amqp-pdf-server.xml");
		final RabbitOperations tpl = ctx.getBean(RabbitOperations.class);
		
		Server server = new Server(8085);
		server.setHandler(new PdfWebHandler(new PdfService() {
			
			@Override
			public byte[] createPdf(String request) {
				return (byte[]) tpl.convertSendAndReceive("pdfRequests", request);
			}
		}));
		server.start();	
		System.out.println("Spring AMQP server started, try http://localhost:8085/?pdf-request=myRequest");
	}
	
	
}
