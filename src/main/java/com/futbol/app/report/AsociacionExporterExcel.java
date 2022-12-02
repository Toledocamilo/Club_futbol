package com.futbol.app.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.futbol.app.entity.Asociacion;

public class AsociacionExporterExcel {
	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	List<Asociacion> listaAsociaciones;

	public AsociacionExporterExcel(List<Asociacion> listaAsociaciones) {
		this.listaAsociaciones = listaAsociaciones;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Asociaciones");
	}

	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);

		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setBold(true);
		fuente.setFontHeight(16);
		estilo.setFont(fuente);

		Cell celda = fila.createCell(0);
		celda.setCellValue("ID");
		celda.setCellStyle(estilo);

		celda = fila.createCell(1);
		celda.setCellValue("Nombre");
		celda.setCellStyle(estilo);

		celda = fila.createCell(2);
		celda.setCellValue("Pais");
		celda.setCellStyle(estilo);

		celda = fila.createCell(3);
		celda.setCellValue("Presidente");
		celda.setCellStyle(estilo);

		celda = fila.createCell(4);
		celda.setCellValue("Siglas");
		celda.setCellStyle(estilo);
	}

	private void escribirDatosDeTabla() {
		int numeroFilas = 1;

		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);

		for (Asociacion asociacion : listaAsociaciones) {
			Row fila = hoja.createRow(numeroFilas++);

			Cell celda = fila.createCell(0);
			celda.setCellValue(asociacion.getId());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);

			celda = fila.createCell(1);
			celda.setCellValue(asociacion.getNombre());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);

			celda = fila.createCell(2);
			celda.setCellValue(asociacion.getPais());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);

			celda = fila.createCell(3);
			celda.setCellValue(asociacion.getPresidente());
			hoja.autoSizeColumn(3);
			celda.setCellStyle(estilo);

			celda = fila.createCell(4);
			celda.setCellValue(asociacion.getSiglas());
			hoja.autoSizeColumn(4);
			celda.setCellStyle(estilo);
		}

	}
	
	public void exporta(HttpServletResponse response) throws IOException {
		escribirCabeceraDeTabla();
		escribirDatosDeTabla();
		
		ServletOutputStream outPutStream = response.getOutputStream();
		libro.write(outPutStream);
		
		libro.close();
		outPutStream.close();
	}

}
