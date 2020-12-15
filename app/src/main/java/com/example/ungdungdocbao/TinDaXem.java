package com.example.ungdungdocbao;

import java.util.ArrayList;
import java.util.List;

public class TinDaXem {
    public List<String> dstindaxem = new ArrayList<>();
    public TinDaXem(List<String> dstindalieu){
        this.dstindaxem =dstindalieu;
    }
    public void addTinDaXem(String id){
        dstindaxem.add(id);
    }
}
