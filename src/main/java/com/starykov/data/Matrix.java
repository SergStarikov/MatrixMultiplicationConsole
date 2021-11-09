package com.starykov.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Matrix {
    private final float[][] matrix;
    private final int matrixRows;
    private final int matrixColumns;

    public Matrix(int matrixRows, int matrixColumns) {
        this.matrixRows = matrixRows;
        this.matrixColumns = matrixColumns;
        this.matrix = new float[matrixRows][matrixColumns];
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "Rows=" + matrixRows +
                " Columns=" + matrixColumns +
                '}';
    }
}
