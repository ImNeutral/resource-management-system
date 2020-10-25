package com.monstarlab.rms.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.monstarlab.rms.model.Employee;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperReportUtil {
	
	public static void generate(String jrxmlName, Collection<Employee> employees,
								HttpServletResponse response) 
										throws JRException, IOException {
		

		Map<String, Object> parameters = new HashMap<String, Object>();
        //set path to resources/jasperReports
        String path = "";

        JasperReport jasperReport = JasperCompileManager
               .compileReport( path + jrxmlName + ".jrxml");
		
        //parameters to be received by the jrxml
        parameters.put("SUBREPORT_DIR", "/");
        parameters.put("months", DateUtil.getNextMonths(4));
        parameters.put("monthNames", DateUtil.getMonthNames());

        // DataSource
        // then using empty datasource.
        JRDataSource dataSource = new JRBeanCollectionDataSource(employees);
 
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);
        
        // Make sure the output directory exists.
        File outDir = new File(path);
        outDir.mkdirs();
 
        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=resources-report.pdf");
        OutputStream out = response.getOutputStream();
        
        // Export to PDF, DOCX, XLS
        // String absolutePath = path + outputFileName;
    	// absolutePath += ".pdf";
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
}
