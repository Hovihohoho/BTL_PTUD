package entity;

public class HoaDon {
    private String maDon;
    private String maMon;
    private int soLuong;

    public HoaDon(String maDon, String maMon, int soLuong) {
        this.maDon = maDon;
        this.maMon = maMon;
        this.soLuong = soLuong;
    }

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return String.format("ChiTietDonDatBan [maDon=%s, maMon=%s, soLuong=%d]", 
            maDon, maMon, soLuong);
    }
}
