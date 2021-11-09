package com.starykov;

import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;
import com.starykov.util.*;
import com.starykov.validation.Validation;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

import static com.starykov.data.StringConstants.TIME_ELAPSED;

@Slf4j
public class Runner {

    private final MatrixExtender extender = new MatrixRandomExtender();
    private final Validation validation = new Validation();
    private final MatrixMultiplier multiplier = new MatrixMultiplier();

    public void runApp(){

        int rows = getPropertyByName("rows");
        int columns = getPropertyByName("columns");

        Matrix firstMatrix = new Matrix(rows, columns);
        Matrix secondMatrix = new Matrix(rows, columns);

        try {
            validation.isMatrixEmptyOrNull(firstMatrix);
            validation.isMatrixEmptyOrNull(secondMatrix);
            extender.fillMatrix(firstMatrix);
            extender.fillMatrix(secondMatrix);

            log.info("Multiplication matrices in one thread");

            validation.isRowEqualColumn(firstMatrix, secondMatrix);

            Instant start = Instant.now();
            multiplier.multiplyMatrices(firstMatrix, secondMatrix);
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            log.info(TIME_ELAPSED, timeElapsed);

            log.info("Multiplication matrices in {} threads", getPropertyByName("threadCounts"));
            start = Instant.now();
            multiplier.multiplyMatricesByThreads(firstMatrix, secondMatrix, getPropertyByName("threadCounts"));
            finish = Instant.now();
            timeElapsed = Duration.between(start, finish).toMillis();
            log.info(TIME_ELAPSED, timeElapsed);

        } catch (MatrixException | InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        catch (Exception e){
            log.error("Unexpected exception {}", e.getMessage());
        }
    }

    private int getPropertyByName(String propertyName) {
        return Integer.parseInt(PropReader.INSTANCE.getProperty(propertyName));
    }
}
