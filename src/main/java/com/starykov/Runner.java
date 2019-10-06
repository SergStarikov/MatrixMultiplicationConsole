package com.starykov;

import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;
import com.starykov.util.*;
import com.starykov.validation.Validation;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static com.starykov.util.StringConstants.TIME_ELAPSED;

public class Runner {

    private static final Logger logger = Logger.getLogger(Runner.class);

    private Matrix matrix_1 = new Matrix(Integer.parseInt(PropReader.INSTANCE.getProperty("row")), Integer.parseInt(PropReader.INSTANCE.getProperty("column")));
    private Matrix matrix_2 = new Matrix(Integer.parseInt(PropReader.INSTANCE.getProperty("row")), Integer.parseInt(PropReader.INSTANCE.getProperty("column")));

    private MatrixExtender extender = new MatrixRandomExtender();
    private Validation validation = new Validation();
    private MatrixUtil util = new MatrixUtil();
    private Printer printer = new Printer();

    public void runApp(){

        try {
            validation.isMatrixEmptyOrNull(matrix_1);
            validation.isMatrixEmptyOrNull(matrix_2);
            extender.fillMatrix(matrix_1);
            extender.fillMatrix(matrix_2);

            printer.printMessage("Multiplication matrices in one thread");

            validation.isRowEqualColumn(matrix_1, matrix_2);


            Instant start = Instant.now();
            util.matrixMultiplication(matrix_1, matrix_2);
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            printer.printMessageWithParams(TIME_ELAPSED, timeElapsed);

            printer.printMessage("Multiplication matrices in several threads");
            start = Instant.now();
            util.matrixMultiplicationByThreads(matrix_1, matrix_2, Integer.parseInt(PropReader.INSTANCE.getProperty("threadCount")));
            finish = Instant.now();
            timeElapsed = Duration.between(start, finish).toMillis();
            printer.printMessageWithParams(TIME_ELAPSED, timeElapsed);

        } catch (MatrixException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        catch (Exception e){
            logger.error("Unexpected exception " + e.getMessage());
        }
    }
}
