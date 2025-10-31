package edu.ifam.daic.dad.DTO;

public class DadosDemograficosDTO {

    private long populacaoTotal;
    private long totalHomens;
    private long totalMulheres;

    // Faixas etárias
    private long faixa0_10;
    private long faixa11_20;
    private long faixa21_30;
    private long faixa31_40; // Adicionei esta para completar
    private long faixaAcima40;

    public DadosDemograficosDTO() {
    }

    public DadosDemograficosDTO(long populacaoTotal, long totalHomens, long totalMulheres, long faixa0_10, long faixa11_20, long faixa21_30, long faixa31_40, long faixaAcima40) {
        this.populacaoTotal = populacaoTotal;
        this.totalHomens = totalHomens;
        this.totalMulheres = totalMulheres;
        this.faixa0_10 = faixa0_10;
        this.faixa11_20 = faixa11_20;
        this.faixa21_30 = faixa21_30;
        this.faixa31_40 = faixa31_40;
        this.faixaAcima40 = faixaAcima40;
    }

    public long getPopulacaoTotal() {
        return populacaoTotal;
    }

    public void setPopulacaoTotal(long populacaoTotal) {
        this.populacaoTotal = populacaoTotal;
    }

    public long getTotalHomens() {
        return totalHomens;
    }

    public void setTotalHomens(long totalHomens) {
        this.totalHomens = totalHomens;
    }

    public long getTotalMulheres() {
        return totalMulheres;
    }

    public void setTotalMulheres(long totalMulheres) {
        this.totalMulheres = totalMulheres;
    }

    public long getFaixa0_10() {
        return faixa0_10;
    }

    public void setFaixa0_10(long faixa0_10) {
        this.faixa0_10 = faixa0_10;
    }

    public long getFaixa11_20() {
        return faixa11_20;
    }

    public void setFaixa11_20(long faixa11_20) {
        this.faixa11_20 = faixa11_20;
    }

    public long getFaixa21_30() {
        return faixa21_30;
    }

    public void setFaixa21_30(long faixa21_30) {
        this.faixa21_30 = faixa21_30;
    }

    public long getFaixa31_40() {
        return faixa31_40;
    }

    public void setFaixa31_40(long faixa31_40) {
        this.faixa31_40 = faixa31_40;
    }

    public long getFaixaAcima40() {
        return faixaAcima40;
    }

    public void setFaixaAcima40(long faixaAcima40) {
        this.faixaAcima40 = faixaAcima40;
    }
}
