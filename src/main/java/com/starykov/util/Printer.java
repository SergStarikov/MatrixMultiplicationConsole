package com.starykov.util;

import com.starykov.data.Matrix;
import org.apache.log4j.Logger;

import java.text.MessageFormat;

public class Printer {
    private static final Logger logger = Logger.getLogger(Printer.class);

    //TODO
    public void printMatrix(Matrix matrix){
        for (int i = 0; i < matrix.getMatrixRows(); i++) {
            for (int j = 0; j < matrix.getMatrixColumns(); j++) {
                System.out.printf("%10.4f",matrix.getMatrix()[i][j]);
            }
            System.out.println();
        }
    }

    public void printMessage(String message){
        logger.info(message);
    }

    public void printMessageWithParams(String message, long param){
        logger.info(MessageFormat.format(message, param));
    }
}
