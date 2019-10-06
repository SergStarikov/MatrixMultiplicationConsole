package com.starykov.data;

public class Matrix {
    private float[][] matrix;
    private int matrixRow;
    private int matrixColumn;

    public Matrix(int matrixRow, int matrixColumn) {
        this.matrixRow = matrixRow;
        this.matrixColumn = matrixColumn;
        this.matrix = new float[matrixRow][matrixColumn];
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public int getMatrixRow() {
        return matrixRow;
    }

    public void setMatrixRow(int matrixRow) {
        this.matrixRow = matrixRow;
    }

    public int getMatrixColumn() {
        return matrixColumn;
    }

    public void setMatrixColumn(int matrixColumn) {
        this.matrixColumn = matrixColumn;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "Row=" + matrixRow +
                " Column=" + matrixColumn +
                '}';
    }
}
