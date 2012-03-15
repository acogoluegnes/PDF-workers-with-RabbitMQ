/**
 * 
 */
package com.zenika.rabbitmq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;

/**
 * @author acogoluegnes
 *
 */
public class PdfWebHandler extends AbstractHandler {
	
	private final PdfService pdfService;
	
	public PdfWebHandler(PdfService pdfService) {
		this.pdfService = pdfService;
	}
	
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {
		String pdfRequest = request.getParameter("pdf-request");
		pdfRequest = pdfRequest == null ? "not pdf-request param" : pdfRequest;
		byte [] content = pdfService.createPdf(pdfRequest);
		response.getOutputStream().write(content);
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/pdf");			
		response.setHeader("Content-Disposition","attachment; filename=spring-amqp.pdf");
		response.flushBuffer();
	}

}
