package com.sue.open.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.sue.open.member.Member;

public class DownloadViewPDF extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Member> members = (List<Member>) model.get("members");
		
		BaseFont bfKorean = BaseFont.createFont(
				"c:\\windows\\fonts\\batang.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bfKorean);
		Table table = new Table(6, members.size() + 1);
		table.setPadding(5);
		Cell cell = new Cell(new Paragraph("idx",font));
		cell.setHeader(true);
		table.addCell(cell);
		cell = new Cell(new Paragraph("email",font));
		table.addCell(cell);
		cell = new Cell(new Paragraph("password",font));
		table.addCell(cell);
		cell = new Cell(new Paragraph("name",font));
		table.addCell(cell);
		cell = new Cell(new Paragraph("photo",font));
		table.addCell(cell);
		cell = new Cell(new Paragraph("regdate",font));
		
		table.addCell(cell);
		table.endHeaders();
		for (Member m : members) {
		table.addCell(m.getIdx()+"");
		table.addCell(m.getId());
		table.addCell(m.getPassword());
		table.addCell(m.getName());
		table.addCell(m.getPhoto());
		table.addCell(m.getRegDate());
		}
		document.add(table);
		
	}
	
	
}
