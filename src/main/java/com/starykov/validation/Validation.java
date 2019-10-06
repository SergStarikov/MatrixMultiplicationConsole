package com.starykov.validation;

import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;

import java.text.MessageFormat;

import static com.starykov.util.StringConstants.MATRIX_NULL_OR_EMPTY;
import static com.starykov.util.StringConstants.WRONG_DIMENSION_ERROR_MESSAGE;

public class Validation {

    public void isRowEqualColumn(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException {
        if(firstMatrix.getMatrixRow() != secondMatrix.getMatrixColumn()){
            throw new MatrixException(WRONG_DIMENSION_ERROR_MESSAGE);
        }
    }

    public void isMatrixEmptyOrNull(Matrix matrix) throws MatrixException {
        if (matrix == null || matrix.getMatrix().length == 0){
            throw new MatrixException(MATRIX_NULL_OR_EMPTY);
        }
    }
}
