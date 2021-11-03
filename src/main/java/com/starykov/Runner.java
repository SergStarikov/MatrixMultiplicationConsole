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

        int rows = Integer.parseInt(PropReader.INSTANCE.getProperty("rows"));
        int columns = Integer.parseInt(PropReader.INSTANCE.getProperty("columns"));

        Matrix matrix_1 = new Matrix(rows, columns);
        Matrix matrix_2 = new Matrix(rows, columns);

        try {
            validation.isMatrixEmptyOrNull(matrix_1);
            validation.isMatrixEmptyOrNull(matrix_2);
            extender.fillMatrix(matrix_1);
            extender.fillMatrix(matrix_2);

            printer.printMessage("Multiplication matrices in one thread");

            validation.isRowEqualColumn(matrix_1, matrix_2);

            Instant start = Instant.now();
            multiplier.multiplyMatrixs(matrix_1, matrix_2);
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            printer.printMessageWithParams(TIME_ELAPSED, timeElapsed);

            printer.printMessage("Multiplication matrices in several threads");
            start = Instant.now();
            multiplier.multiplyMatrixsByThreads(matrix_1, matrix_2, Integer.parseInt(PropReader.INSTANCE.getProperty("threadCount")));
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
}
