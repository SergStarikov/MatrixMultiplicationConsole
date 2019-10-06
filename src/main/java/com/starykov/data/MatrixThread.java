package com.starykov.data;

public class MatrixThread extends Thread {
    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private Matrix multiplicandMatrix;
    private int startIndex;
    private int endIndex;

    public MatrixThread(Matrix firstMatrix, Matrix secondMatrix, Matrix multiplicandMatrix, int startIndex, int endIndex) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.multiplicandMatrix = multiplicandMatrix;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public Matrix getFirstMatrix() {
        return firstMatrix;
    }

    public Matrix getSecondMatrix() {
        return secondMatrix;
    }

    public Matrix getMultiplicandMatrix() {
        return multiplicandMatrix;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    @Override
    public void run() {
        int columnCountResultMatrix = secondMatrix.getMatrixColumn();
        for (int i = startIndex; i < endIndex; ++i) {
            for (int j = 0; j < secondMatrix.getMatrixRow(); ++j) {
                multiplicandMatrix.getMatrix()[i / columnCountResultMatrix][i % columnCountResultMatrix] +=
                        firstMatrix.getMatrix()[i/columnCountResultMatrix][j] * secondMatrix.getMatrix()[j][i % columnCountResultMatrix];
            }
        }
    }
}
