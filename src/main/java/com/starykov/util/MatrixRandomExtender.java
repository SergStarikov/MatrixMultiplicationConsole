package com.starykov.util;

import com.starykov.data.Matrix;

import java.util.Random;

public class MatrixRandomExtender implements MatrixExtender {

    private Random random = new Random();

    public Matrix fillMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getMatrixRow(); i++) {
            for (int j = 0; j < matrix.getMatrixColumn(); j++) {
                matrix.getMatrix()[i][j] = getRandomFloatBetweenRangeFromProp();
            }
        }
        return matrix;
    }

    private float getRandomFloatBetweenRange(float min, float max){
        float x = (float) ((random.nextFloat()*((max-min)+1))+min);
        return x;
    }

    private float getRandomFloatBetweenRangeFromProp(){
        return getRandomFloatBetweenRange(Float.parseFloat(PropReader.INSTANCE.getProperty("min")), Float.parseFloat(PropReader.INSTANCE.getProperty("max")));
    }
}
