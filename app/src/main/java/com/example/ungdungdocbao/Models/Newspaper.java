package com.example.ungdungdocbao.Models;

public class Newspaper {
    private Integer ID;
    private String TieuDe;
    private String DanhMuc;
    private String MoTa;
    private String NoiDung;
    private String NgayDang;
    private String HinhAnh;
    private String TieuDeHinhAnh;
    private String TacGia;
    private Integer LuotXem;

    public Newspaper(Integer Id, String TieuDe, String DanhMuc, String MoTa, String NoiDung, String NgayDang, String HinhAnh, String TieuDeHinhAnh, String TacGia, Integer LuotXem){
        this.TieuDe=TieuDe;
        this.ID = Id;
        this.DanhMuc=DanhMuc;
        this.MoTa=MoTa;
        this.NoiDung=NoiDung;
        this.NgayDang=NgayDang;
        this.HinhAnh = HinhAnh;
        this.TieuDeHinhAnh= TieuDeHinhAnh;
        this.TacGia = TacGia;
        this.LuotXem = LuotXem;

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        ID = ID;
    }
    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getDanhMuc() {
        return DanhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        DanhMuc = danhMuc;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getTieuDeHinhAnh() {
        return TieuDeHinhAnh;
    }

    public void setTieuDeHinhAnh(String tieuDeHinhAnh) {
        TieuDeHinhAnh = tieuDeHinhAnh;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public Integer getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(Integer luotXem) {
        LuotXem = luotXem;
    }
}
