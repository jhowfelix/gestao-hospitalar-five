package br.com.five.service;

import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Font.BOLD;
import static com.itextpdf.text.Font.FontFamily.HELVETICA;
import static com.itextpdf.text.Font.NORMAL;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.five.entities.Paciente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class Pdf {

  public static ByteArrayInputStream geraPDF(
    List<Paciente> pacientes
  ) {
    Document documento = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try {
      PdfPTable tabela = new PdfPTable(4);
      tabela.setWidthPercentage(100);
      tabela.setWidths(new int[] { 4, 2, 2, 2 });
      Font headerPaciente = new Font(HELVETICA, 20);
      Font fonteHeader = new Font(HELVETICA, 10, BOLD);
      Font fonteBody = new Font(HELVETICA, 10, NORMAL);

      PdfPCell header;

      header = new PdfPCell(new Phrase("PACIENTES", headerPaciente));
      header.setHorizontalAlignment(ALIGN_CENTER);
      header.setColspan(4);
      tabela.addCell(header);

      header = new PdfPCell(new Phrase("NOME", fonteHeader));
      header.setHorizontalAlignment(ALIGN_CENTER);
      tabela.addCell(header);

      header = new PdfPCell(new Phrase("CPF", fonteHeader));
      header.setHorizontalAlignment(ALIGN_CENTER);
      tabela.addCell(header);

      header =
        new PdfPCell(new Phrase("DATA DE NASCIMENTO", fonteHeader));
      header.setHorizontalAlignment(ALIGN_CENTER);
      tabela.addCell(header);

      header = new PdfPCell(new Phrase("SEXO", fonteHeader));
      header.setHorizontalAlignment(ALIGN_CENTER);
      tabela.addCell(header);

      pacientes.forEach(
        paciente -> {
          PdfPCell body;

          body = new PdfPCell(new Phrase(paciente.getNome(), fonteBody));
          body.setHorizontalAlignment(ALIGN_CENTER);
          tabela.addCell(body);

          body = new PdfPCell(new Phrase(paciente.getCpf(), fonteBody));
          body.setHorizontalAlignment(ALIGN_CENTER);
          tabela.addCell(body);

          body =
            new PdfPCell(
              new Phrase(paciente.getDataNascimento().toString(), fonteBody)
            );
          body.setHorizontalAlignment(ALIGN_CENTER);
          tabela.addCell(body);

          body =
            new PdfPCell(new Phrase(paciente.getSexo().toString(), fonteBody));
          body.setHorizontalAlignment(ALIGN_CENTER);
          tabela.addCell(body);
        }
      );

      PdfWriter.getInstance(documento, out);
      documento.open();
      documento.add(tabela);

      documento.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return new ByteArrayInputStream(out.toByteArray());
  }
}
