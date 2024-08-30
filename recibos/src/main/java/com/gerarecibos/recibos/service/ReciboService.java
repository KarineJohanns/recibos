package com.gerarecibos.recibos.service;
import com.gerarecibos.recibos.model.Configuracao;
import com.gerarecibos.recibos.repository.ConfiguracaoRepository;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.gerarecibos.recibos.model.Recibo;
import com.gerarecibos.recibos.repository.ReciboRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public byte[] gerarReciboPdf(Long reciboId) throws IOException {

        Recibo recibo = reciboRepository.findById(reciboId).orElseThrow(() -> new RuntimeException("Recibo não encontrado"));
        Configuracao configuracao = configuracaoRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataPagamentoFormatada = recibo.getParcela().getDataPagamento().format(formatter);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Recibo"));
        document.add(new Paragraph("Emitente: " + configuracao.getNomeEmitente() + ", CPF: " + configuracao.getCpfEmitente()));
        document.add(new Paragraph("Detalhes: " + recibo.getConteudo()));
        document.add(new Paragraph("Cliente: " + recibo.getParcela().getCliente().getNome()));
        document.add(new Paragraph("Produto: " + recibo.getParcela().getProduto().getNome()));
        document.add(new Paragraph("Valor Pago: " + recibo.getParcela().getValorPago()));
        document.add(new Paragraph("Data de Pagamento: " + dataPagamentoFormatada));

        document.close();

        return baos.toByteArray();
    }
}