package entity;

import java.time.LocalDate;

public class NhanVien {
    private String maNV, tenNV, sDT, email;
    private LocalDate ngayVaoLam;
    private CaLamViec maCa;
    private TaiKhoan maTK;

    // Getters và Setters
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public CaLamViec getMaCa() {
        return maCa;
    }

    public void setMaCa(CaLamViec maCa) {
        this.maCa = maCa;
    }

    public TaiKhoan getMaTK() {
        return maTK;
    }

    public void setMaTK(TaiKhoan maTK) {
        this.maTK = maTK;
    }

    public NhanVien() {
        this.maNV = "TM0000";
        this.tenNV = "Khong Co Ten";
        this.sDT = "0943211234";
        this.email = "khongcoemail@gmail.com";
        this.ngayVaoLam = LocalDate.now();
    }

    public NhanVien(String maNV, TaiKhoan maTK, CaLamViec maCa, String tenNV, String sDT, String email, LocalDate ngayVaoLam) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sDT = sDT;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.maCa = maCa;
        this.maTK = maTK;
    }

    // Phương thức toString() để hiển thị thông tin nhân viên
    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", sDT='" + sDT + '\'' +
                ", email='" + email + '\'' +
                ", ngayVaoLam=" + ngayVaoLam +
                ", maCa=" + maCa +
                ", maTK=" + maTK +
                '}';
    }
}
