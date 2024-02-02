package com.aha.test.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BsBankInfo implements Serializable {

    private String bankName;
    private String bankNo;
    private BankInfo bankInfo;

    @Data
    static class BankInfo implements Serializable{
        private String info;
    }

}
