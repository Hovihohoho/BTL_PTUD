package entity;

public class CaLamViec {
    private String maCa;
    private String gioBatDau;
    private String gioKetThuc;

    public CaLamViec(String maCa, String gioBatDau, String gioKetThuc) {
        this.maCa = maCa;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    @Override
    public String toString() {
        return String.format("CaLamViec [maCa=%s, tenCa=%s, gioBatDau=%s, gioKetThuc=%s]", 
            maCa, gioBatDau, gioKetThuc);
    }
}
