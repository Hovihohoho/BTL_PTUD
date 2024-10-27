/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class MonAn {
    private String maMonAn, tenMonAn, thongTinMon, trangThaiMon;
    private double giaTien;
    private LoaiMon loai_mon;

    public String getMaMonAn() {
        return maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getThongTinMon() {
        return thongTinMon;
    }

    public void setThongTinMon(String thongTinMon) {
        this.thongTinMon = thongTinMon;
    }

    public String getTrangThaiMon() {
        return trangThaiMon;
    }

    public void setTrangThaiMon(String trangThaiMon) {
        this.trangThaiMon = trangThaiMon;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public LoaiMon getLoai_mon() {
        return loai_mon;
    }

    public void setLoai_mon(LoaiMon loai_mon) {
        this.loai_mon = loai_mon;
    }

    public MonAn(String maMonAn, String tenMonAn, String thongTinMon, String trangThaiMon, double giaTien, LoaiMon loai_mon) {
        this.maMonAn = maMonAn;
        setTenMonAn(tenMonAn);
        setTrangThaiMon(trangThaiMon);
        setLoai_mon(loai_mon);
        setGiaTien(giaTien);
        setThongTinMon(thongTinMon);
    }
    
    
}
