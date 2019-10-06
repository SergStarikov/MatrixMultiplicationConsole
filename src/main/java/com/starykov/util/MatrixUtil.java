package com.starykov.util;

import com.starykov.data.Matrix;
import com.starykov.data.MatrixThread;

public class MatrixUtil {

    public Matrix matrixMultiplication(Matrix firstMatrix, Matrix secondMatrix) {
        Matrix multiplicandMatrix = new Matrix(firstMatrix.getMatrixRow(), secondMatrix.getMatrixColumn());
        for (int i = 0; i < firstMatrix.getMatrixRow(); i++) {
            for (int j = 0; j < secondMatrix.getMatrixColumn(); j++) {
                for (int k = 0; k < firstMatrix.getMatrixColumn(); k++) {
                    multiplicandMatrix.getMatrix()[i][j] += firstMatrix.getMatrix()[i][k] * secondMatrix.getMatrix()[k][j];
                }
            }
        }
        return multiplicandMatrix;
    }

    public Matrix matrixMultiplicationByThreads(Matrix firstMatrix, Matrix secondMatrix, int threadCount) throws InterruptedException {
        Matrix multiplicandMatrix = new Matrix(firstMatrix.getMatrixRow(), secondMatrix.getMatrixColumn());

        if (threadCount > multiplicandMatrix.getMatrixRow()){
            threadCount = multiplicandMatrix.getMatrixRow();
        }

        int cellsToThread = (multiplicandMatrix.getMatrixRow() * multiplicandMatrix.getMatrixColumn()) / threadCount;
        int startIndex = 0;
        MatrixThread[] matrixThreads = new MatrixThread[threadCount];
        for (int i = threadCount - 1; i >= 0; --i) {
            int endIndex = startIndex + cellsToThread;
            if (endIndex == 0) {
                endIndex = firstMatrix.getMatrixRow() * secondMatrix.getMatrixColumn();
            }

            matrixThreads[i] = new MatrixThread(firstMatrix, secondMatrix, multiplicandMatrix, startIndex, endIndex);
            matrixThreads[i].start();
            startIndex = endIndex;
        }

        for (int i = 0; i < matrixThreads.length; i++) {
            matrixThreads[i].join();
        }
        return multiplicandMatrix;
    }
}
