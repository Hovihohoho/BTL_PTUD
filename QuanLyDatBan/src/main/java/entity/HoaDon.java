package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
    private String maHD;
    private LocalDate thoiGianTao, ngayDatBan;          //YYYY-MM-DD
    private int soLuongKhach;
    private NhanVien nhanVien;
    private Ban ban;
    private YeuCauKhachHang yeucau;
    private double tongTien;
    private String trangThaiHoaDon;

    public String getTrangThaiHoaDon() {
        return trangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(String trangThaiHoaDon) {
        this.trangThaiHoaDon = trangThaiHoaDon;
    }
    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDate thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public LocalDate getNgayDatBan() {
        return ngayDatBan;
    }

    public void setNgayDatBan(LocalDate ngayDatBan) {
        this.ngayDatBan = ngayDatBan;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public YeuCauKhachHang getYeucau() {
        return yeucau;
    }

    public void setYeucau(YeuCauKhachHang yeucau) {
        this.yeucau = yeucau;
    }

    public HoaDon(String maHD, YeuCauKhachHang yeucau, NhanVien nhanVien, Ban ban, int soLuongKhach, LocalDate thoiGianTao, LocalDate ngayDatBan, String trangThaiHoaDon) {
        this.maHD = maHD;
        this.thoiGianTao = thoiGianTao;
        this.ngayDatBan = ngayDatBan;
        this.soLuongKhach = soLuongKhach;
        this.nhanVien = nhanVien;
        this.ban = ban;
        this.yeucau = yeucau;
        this.trangThaiHoaDon = trangThaiHoaDon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.maHD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHD, other.maHD);
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
   
}
