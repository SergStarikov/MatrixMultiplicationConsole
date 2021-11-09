package com.starykov.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatrixThread extends Thread {
    private final Matrix firstMatrix;
    private final Matrix secondMatrix;
    private final Matrix multiplicandMatrix;
    private final int startIndex;
    private final int endIndex;

    @Override
    public void run() {
        int columnCountResultMatrix = secondMatrix.getMatrixColumns();
        for (int i = startIndex; i < endIndex; ++i) {
            for (int j = 0; j < secondMatrix.getMatrixRows(); ++j) {
                multiplicandMatrix.getMatrix()[i / columnCountResultMatrix][i % columnCountResultMatrix] +=
                        firstMatrix.getMatrix()[i/columnCountResultMatrix][j] * secondMatrix.getMatrix()[j][i % columnCountResultMatrix];
            }
        }
    }
}
