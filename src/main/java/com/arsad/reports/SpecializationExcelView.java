package com.arsad.reports;


import com.arsad.entity.Specialization;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import java.util.List;
import java.util.Map;


public class SpecializationExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 1. Define our own excel file name */
        response.addHeader("content-Disposition", "attachment;filename=SPEC_REPORT.xls");

        /* 2. Read data given by controller */
        @SuppressWarnings("unchecked") List<Specialization> specList = (List<Specialization>) model.get("specList");

        /* 3. create one sheet */
        Sheet sheet = workbook.createSheet("SPECIALIZATION");

        /* 4. create row0 as header */
        createSheetHeader(sheet);

        /* 5. create other rows from list given by controller */
        createSheatBody(sheet, specList);
    }

    private void createSheetHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("CODE");
        row.createCell(2).setCellValue("NAME");
        row.createCell(3).setCellValue("NOTE");

    }

    private void createSheatBody(Sheet sheet, List<Specialization> specList) {
        int rowNumber = 1;
        for (Specialization specialization : specList) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(specialization.getId());
            row.createCell(1).setCellValue(specialization.getSpecCode());
            row.createCell(2).setCellValue(specialization.getSpecName());
            row.createCell(3).setCellValue(specialization.getSpecNote());
        }
    }

}
