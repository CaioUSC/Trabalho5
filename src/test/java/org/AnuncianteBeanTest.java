package org;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AnuncianteBeanTest {

    // Cria um produto teste para testar o desconto
    private ProdutoBean produto(double valor) {
        ProdutoBean p = new ProdutoBean();
        p.setCodigo("X");
        p.setNome("Produto");
        p.setDescricao("Desc");
        p.setEstado("NOVO");
        p.setValor(valor);
        return p;
    }

    private AnuncioBean anuncio(double valor, double descontoPercent) {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(valor));
        a.setDesconto(descontoPercent);
        a.setFotosUrl(new ArrayList<>());
        return a;
    }

    @Test
    @DisplayName("valorMedioAnuncios: média dos valores dos anuncios")
    public void valorMedioBasico() {
        AnuncianteBean an = new AnuncianteBean();
        an.addAnuncio(anuncio(100,0));
        an.addAnuncio(anuncio(200,0));
        Double media = an.valorMedioAnuncios();
        assertEquals(150.0, media, "Média de 100 e 200 deve ser 150");
    }

    @Test
    @DisplayName("valorMedioAnuncios: lista vazia deve retornar 0.0 (ou lançar exceção específica)")
    public void valorMedioListaVazia() {
        AnuncianteBean an = new AnuncianteBean();
        try {
            Double media = an.valorMedioAnuncios();
            assertEquals(0.0, media, 0.0001, "Para lista vazia, recomenda-se retornar 0.0");
        } catch (ArithmeticException ex) {
            fail("Divisão por zero detectada. Corrija para tratar lista vazia.");
        }
    }

    @Test
    @DisplayName("add/remove: tamanho consistente e exceção para índice inválido ao incluir e remover anuncios")
    public void addERemove() {
        AnuncianteBean an = new AnuncianteBean();
        an.addAnuncio(anuncio(50.0,0));
        an.addAnuncio(anuncio(150.0,0));
        assertEquals(2, an.getAnuncios().size());
        an.removeAnuncio(0);
        assertEquals(1, an.getAnuncios().size());
        assertThrows(IndexOutOfBoundsException.class, () -> an.removeAnuncio(10));
    }
}