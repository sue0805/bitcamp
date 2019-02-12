package com.sue.open.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.sue.open.member.Member;

public class DownloadView extends AbstractXlsView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Sheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet);
		List<Member> members = (List<Member>)model.get("members");
		int rowNum = 1;
		for(Member m : members) {
			createMemberRow(sheet, m, rowNum++);
		}
	}

	private Sheet createFirstSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "members");
		sheet.setColumnWidth(1, 256*20);
		return sheet;
	}
	
	private void createColumnLabel(Sheet sheet) {
		Row firstRow = sheet.createRow(0);
		Cell cell = firstRow.createCell(0);
		cell.setCellValue("idx");
		cell = firstRow.createCell(1);
		cell.setCellValue("email");
		cell = firstRow.createCell(2);
		cell.setCellValue("password");
		cell = firstRow.createCell(3);
		cell.setCellValue("name");
		cell = firstRow.createCell(4);
		cell.setCellValue("photo");
		cell = firstRow.createCell(5);
		cell.setCellValue("regdate");
	}
	
	private void createMemberRow(Sheet sheet, Member member, int rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(0);
		cell.setCellValue(member.getIdx());
		
		cell = row.createCell(1);
		cell.setCellValue(member.getId());
		cell = row.createCell(2);
		cell.setCellValue(member.getPassword());
		cell = row.createCell(3);
		cell.setCellValue(member.getName());
		cell = row.createCell(4);
		cell.setCellValue(member.getPhoto());
		cell = row.createCell(5);
		cell.setCellValue(member.getRegDate());
	}

}
