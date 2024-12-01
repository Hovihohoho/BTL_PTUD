package entity;

import java.time.LocalTime;

public class CaLamViec {
    private String maCa;
    private String buoiLam;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;

    // Constructor
    public CaLamViec(String maCa, String buoiLam, LocalTime gioBatDau, LocalTime gioKetThuc) {
        this.maCa = maCa;
        this.buoiLam = buoiLam;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    // Getters and Setters
    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public String getBuoiLam() {
        return buoiLam;
    }

    public void setBuoiLam(String buoiLam) {
        this.buoiLam = buoiLam;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }
}
