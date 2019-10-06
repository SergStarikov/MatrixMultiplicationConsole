package com.starykov.util;

import com.starykov.data.Matrix;

import java.text.MessageFormat;

public class Printer {
    public void printMatrix(Matrix matrix){
        for (int i = 0; i < matrix.getMatrixRow(); i++) {
            for (int j = 0; j < matrix.getMatrixColumn(); j++) {
                System.out.printf("%10.4f",matrix.getMatrix()[i][j]);
            }
            System.out.println();
        }
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printMessageWithParams(String message, long param){
        System.out.println(MessageFormat.format(message, param));
    }
}
