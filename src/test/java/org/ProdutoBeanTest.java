package org;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProdutoBeanTest {

    private ProdutoBean novoProduto(double valor) {
        ProdutoBean p = new ProdutoBean();
        p.setValor(valor);
        p.setCodigo("X" + valor);
        p.setNome("N" + valor);
        p.setDescricao("D" + valor);
        p.setEstado("NOVO");
        return p;
    }

    @Test
    @DisplayName("compareTo: deve ordenar por valor (menor < maior)")
    void compareToOrdenaPorValor() {
        ProdutoBean p100 = novoProduto(100.0);
        ProdutoBean p200 = novoProduto(200.0);
        assertTrue(p100.compareTo(p200) < 0, "100 deve ser menor que 200");
        assertTrue(p200.compareTo(p100) > 0, "200 deve ser maior que 100");
        assertEquals(0, p100.compareTo(novoProduto(100.0)), "iguais devem resultar em 0");
    }

    @Test
    @DisplayName("compareTo: deve ser antissimétrico e consistente")
    void compareToAntissimetrico() {
        ProdutoBean p1 = novoProduto(123.0);
        ProdutoBean p2 = novoProduto(456.0);
        int a = p1.compareTo(p2);
        int b = p2.compareTo(p1);
        assertTrue(a == -b, "compareTo deve ser antissimétrico");
    }

}