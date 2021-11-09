package com.starykov.util;

import com.starykov.data.Matrix;

import java.util.Random;

public class MatrixRandomExtender implements MatrixExtender {

    private final Random random = new Random();

    public void fillMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getMatrixRows(); i++) {
            for (int j = 0; j < matrix.getMatrixColumns(); j++) {
                matrix.getMatrix()[i][j] = getRandomFloatBetweenRangeFromProp();
            }
        }
    }

    private float getRandomFloatBetweenRange(float min, float max) {
        return ((random.nextFloat() * ((max - min) + 1)) + min);
    }

    private float getRandomFloatBetweenRangeFromProp() {
        return getRandomFloatBetweenRange(
                Float.parseFloat(PropReader.INSTANCE.getProperty("min_number")),
                Float.parseFloat(PropReader.INSTANCE.getProperty("max_number"))
        );
    }
}
