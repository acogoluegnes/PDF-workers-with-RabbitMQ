/**
 * 
 */
package com.zenika.rabbitmq;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author acogoluegnes
 *
 */
public class ITextPdfService implements PdfService {

	public byte [] createPdf(String request) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			document.addTitle(request);
			document.addSubject(request);
			document.addKeywords(request);
			document.add(new Paragraph(request));
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}		
		document.close();		
		return out.toByteArray();
	}
	
}
