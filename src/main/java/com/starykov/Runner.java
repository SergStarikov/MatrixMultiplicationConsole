package com.starykov;

import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;
import com.starykov.util.*;
import com.starykov.validation.Validation;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static com.starykov.data.StringConstants.TIME_ELAPSED;

public class Runner {

    private static final Logger logger = Logger.getLogger(Runner.class);

    private final MatrixExtender extender = new MatrixRandomExtender();
    private final Validation validation = new Validation();
    private final MatrixMultiplier multiplier = new MatrixMultiplier();
    private final Printer printer = new Printer();

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

            printer.printMessage("Multiplication matrices in one thread");

            validation.isRowEqualColumn(firstMatrix, secondMatrix);

            Instant start = Instant.now();
            multiplier.multiplyMatrices(firstMatrix, secondMatrix);
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            printer.printMessageWithParams(TIME_ELAPSED, timeElapsed);

            printer.printMessage("Multiplication matrices in several threads");
            start = Instant.now();
            multiplier.multiplyMatricesByThreads(firstMatrix, secondMatrix, getPropertyByName("threadCounts"));
            finish = Instant.now();
            timeElapsed = Duration.between(start, finish).toMillis();
            printer.printMessageWithParams(TIME_ELAPSED, timeElapsed);

        } catch (MatrixException | InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        catch (Exception e){
            logger.error("Unexpected exception " + e.getMessage());
        }
    }

    private int getPropertyByName(String propertyName) {
        return Integer.parseInt(PropReader.INSTANCE.getProperty(propertyName));
    }
}
