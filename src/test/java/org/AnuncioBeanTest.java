package org;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class AnuncioBeanTest {

    private ProdutoBean produto(double valor) {
        ProdutoBean p = new ProdutoBean();
        p.setValor(valor);
        return p;
    }

    @Test
    @DisplayName("getValor: 10% de desconto (0.1) → 100 → 90")
    public void getValorDescontoFracao10pct() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(100.0));
        a.setDesconto(0.10);
        assertEquals(90.0, a.getValor(),1e-6, "Espera-se valor*(1 - desconto) para fração [0..1]");
    }

    @Test
    @DisplayName("getValor: 50% de desconto (0.5) → 100 → 50")
    public void getValorDesconto50pct() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(100.0));
        a.setDesconto(0.5);
        assertEquals(50.0, a.getValor(), 1e-6, "Espera-se valor*(1 - desconto) para fração [0..1]");
    }

    @Test
    @DisplayName("getValor: 100% de desconto (1.0) → 100 → 0")
    public void getValorDesconto100pct() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(100.0));
        a.setDesconto(1.0);
        assertEquals(0.0, a.getValor(), 1e-6, "Espera-se valor*(1 - desconto) para fração [0..1]");
    }

    @Test
    @DisplayName("getValor: desconto nulo deve ser tratado")
    public void getValorDescontoNulo() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(100.0));
        a.setDesconto(null);
        assertThrows(IllegalStateException.class, a::getValor,
                "Esperado lançar IllegalStateException quando desconto for null");
    }

    @Test
    @DisplayName("getValor: produto nulo deve ser tratado")
    public void getValorProdutoNulo() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(null);
        a.setDesconto(0.2);
        assertThrows(IllegalStateException.class, a::getValor,
                "Esperado lançar IllegalStateException quando produto for null");
    }

    @Test
    @DisplayName("getValor: valor do produto nulo deve ser tratado")
    public void getValorValorProdutoNulo() {
        ProdutoBean p = new ProdutoBean();
        p.setValor(null);
        AnuncioBean a = new AnuncioBean();
        a.setProduto(p);
        a.setDesconto(0.2);
        assertThrows(IllegalStateException.class, a::getValor,
                "Esperado lançar IllegalStateException quando valor do produto for null");
    }

    @Test
    @DisplayName("Desconto inválido (<0 ou >1) deve ser rejeitado")
    public void descontoForaDoIntervalo() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(100.0));
        a.setDesconto(-0.1);
        assertThrows(IllegalArgumentException.class, a::getValor,
                "Desconto negativo não deve ser aceito");

        a.setDesconto(1.1);
        assertThrows(IllegalArgumentException.class, a::getValor,
                "Desconto > 1.0 não deve ser aceito quando semântica é fração [0..1]");
    }
}
