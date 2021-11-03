package com.starykov.data;

public class Matrix {
    private final float[][] matrix;
    private final int matrixRows;
    private final int matrixColumns;

    public Matrix(int matrixRows, int matrixColumns) {
        this.matrixRows = matrixRows;
        this.matrixColumns = matrixColumns;
        this.matrix = new float[matrixRows][matrixColumns];
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public int getMatrixRows() {
        return matrixRows;
    }

    public int getMatrixColumns() {
        return matrixColumns;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "Rows=" + matrixRows +
                " Columns=" + matrixColumns +
                '}';
    }
}
