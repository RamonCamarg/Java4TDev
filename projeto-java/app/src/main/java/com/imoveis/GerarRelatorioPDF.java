package com.imoveis;

import java.io.FileNotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import com.imoveis.Model.Aluguel;

import javax.swing.JOptionPane;
import java.io.IOException;

public class GerarRelatorioPDF {

    public void gerarRelatorioPDF(Aluguel aluguel) {
        // Cria um novo documento PDF
        PDDocument document = new PDDocument();
        try {
            // Cria uma nova página
            PDPage page = new PDPage();
            document.addPage(page);

            // Inicia o fluxo de conteúdo para escrever na página
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Configura a fonte
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Relatório de Aluguel");
            contentStream.endText();

            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 720);
            contentStream.showText("====================");
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Cliente: " + aluguel.getUsuario().getNome());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 680);
            contentStream.showText("CPF: " + aluguel.getUsuario().getCpf());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 660);
            contentStream.showText("Telefone: " + aluguel.getUsuario().getTelefone());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 640);
            contentStream.showText("Imóvel: " + aluguel.getImovel().getDescricao());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 620);
            contentStream.showText("Data de Início: " + aluguel.getData_inicio());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 600);
            contentStream.showText("Data de Fim: " + aluguel.getData_fim());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 580);
            contentStream.showText("Valor do Aluguel: R$ " + aluguel.getImovel().getValor_aluguel());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 560);
            contentStream.showText("====================");
            contentStream.endText();

            // Finaliza a escrita e fecha o fluxo de conteúdo
            contentStream.close();
            // Definindo o diretório para salvar os realtórios
            String diretorio = "C:\\Users\\Ramon\\Desktop\\Programas\\projeto-java\\relatorios";
            // Salva o documento PDF no arquivo
            String nomeArquivo = diretorio + "\\relatorio_aluguel_" + aluguel.getImovel().getCodigo_id() + ".pdf";
            document.save(nomeArquivo);

            // Fecha o documento PDF
            document.close();

            // Exibe mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Relatório PDF gerado com sucesso!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório em PDF", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
