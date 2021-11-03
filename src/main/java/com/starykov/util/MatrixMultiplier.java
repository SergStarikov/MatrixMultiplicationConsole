package com.starykov.util;

import com.starykov.data.Matrix;
import com.starykov.data.MatrixThread;

public class MatrixMultiplier {

    public Matrix multiplyMatrixs(Matrix firstMatrix, Matrix secondMatrix) {
        Matrix multiplicandMatrix = new Matrix(firstMatrix.getMatrixRows(), secondMatrix.getMatrixColumns());
        for (int i = 0; i < firstMatrix.getMatrixRows(); i++) {
            for (int j = 0; j < secondMatrix.getMatrixColumns(); j++) {
                for (int k = 0; k < firstMatrix.getMatrixColumns(); k++) {
                    multiplicandMatrix.getMatrix()[i][j] += firstMatrix.getMatrix()[i][k] * secondMatrix.getMatrix()[k][j];
                }
            }
        }
        return multiplicandMatrix;
    }

    public Matrix multiplyMatrixsByThreads(Matrix firstMatrix, Matrix secondMatrix, int threadCount) throws InterruptedException {
        Matrix multiplicandMatrix = new Matrix(firstMatrix.getMatrixRows(), secondMatrix.getMatrixColumns());

        if (threadCount > multiplicandMatrix.getMatrixRows()){
            threadCount = multiplicandMatrix.getMatrixRows();
        }

        int cellsToThread = (multiplicandMatrix.getMatrixRows() * multiplicandMatrix.getMatrixColumns()) / threadCount;
        int startIndex = 0;
        MatrixThread[] matrixThreads = new MatrixThread[threadCount];
        for (int i = threadCount - 1; i >= 0; --i) {
            int endIndex = startIndex + cellsToThread;
            if (endIndex == 0) {
                endIndex = firstMatrix.getMatrixRows() * secondMatrix.getMatrixColumns();
            }

            matrixThreads[i] = new MatrixThread(firstMatrix, secondMatrix, multiplicandMatrix, startIndex, endIndex);
            matrixThreads[i].start();
            startIndex = endIndex;
        }

        for (MatrixThread matrixThread : matrixThreads) {
            matrixThread.join();
        }
        return multiplicandMatrix;
    }
}
