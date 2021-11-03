package com.starykov.validation;

import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;

import static com.starykov.data.StringConstants.MATRIX_NULL_OR_EMPTY;
import static com.starykov.data.StringConstants.WRONG_DIMENSION_ERROR_MESSAGE;

public class Validation {

    public void isRowEqualColumn(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException {
        if(firstMatrix.getMatrixRows() != secondMatrix.getMatrixColumns()){
            throw new MatrixException(WRONG_DIMENSION_ERROR_MESSAGE);
        }
    }

    public void isMatrixEmptyOrNull(Matrix matrix) throws MatrixException {
        if (matrix == null || matrix.getMatrix().length == 0){
            throw new MatrixException(MATRIX_NULL_OR_EMPTY);
        }
    }
}
