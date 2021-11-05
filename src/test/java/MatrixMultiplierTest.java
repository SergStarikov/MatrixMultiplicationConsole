import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;
import com.starykov.util.MatrixMultiplier;
import com.starykov.util.Printer;
import com.starykov.validation.Validation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static com.starykov.data.StringConstants.MATRIX_NULL_OR_EMPTY;
import static com.starykov.data.StringConstants.WRONG_DIMENSION_ERROR_MESSAGE;

public class MatrixMultiplierTest {

    private final Matrix matrix_2x3 = new Matrix(2, 3);
    private final Matrix matrix_3x2 = new Matrix(3, 2);
    private final Matrix matrix_2x2 = new Matrix(2, 2);
    private final Printer printer = new Printer();

    public void fillTestMatrix() {
        matrix_2x3.getMatrix()[0][0] = 1f;
        matrix_2x3.getMatrix()[0][1] = 2f;
        matrix_2x3.getMatrix()[0][2] = 3f;
        matrix_2x3.getMatrix()[1][0] = 4f;
        matrix_2x3.getMatrix()[1][1] = 5f;
        matrix_2x3.getMatrix()[1][2] = 6f;

        matrix_3x2.getMatrix()[0][0] = 7f;
        matrix_3x2.getMatrix()[0][1] = 8f;
        matrix_3x2.getMatrix()[1][0] = 9f;
        matrix_3x2.getMatrix()[1][1] = 10f;
        matrix_3x2.getMatrix()[2][0] = 11f;
        matrix_3x2.getMatrix()[2][1] = 12f;

        matrix_2x2.getMatrix()[0][0] = 58f;
        matrix_2x2.getMatrix()[0][1] = 64f;
        matrix_2x2.getMatrix()[1][0] = 139f;
        matrix_2x2.getMatrix()[1][1] = 154f;
    }

    @Test
    public void shouldCorrectlyMultiply() {
        fillTestMatrix();
        Matrix resultMatrix = new MatrixMultiplier().multiplyMatrices(matrix_2x3, matrix_3x2);

        checkArraysEquality(matrix_2x2.getMatrix(), resultMatrix.getMatrix());
    }

    @Test
    public void shouldCorrectlyMultiplyByThreads() throws InterruptedException {
        fillTestMatrix();

        Matrix resultMatrix = new MatrixMultiplier().multiplyMatricesByThreads(matrix_2x3, matrix_3x2, 4);

        checkArraysEquality(matrix_2x2.getMatrix(), resultMatrix.getMatrix());
    }

    @Test
    public void shouldCorrectlyMultiplyByThreadsBigMatrix () throws InterruptedException {
        Matrix matrix_5x2 = new Matrix(5, 2);
        Matrix matrix_2x5 = new Matrix(2, 5);
        Matrix matrix_5x5 = new Matrix(5, 5);

        Arrays.stream(matrix_5x2.getMatrix()).forEach(el->Arrays.fill(el, 1));
        Arrays.stream(matrix_2x5.getMatrix()).forEach(el->Arrays.fill(el, 2));
        Arrays.stream(matrix_5x5.getMatrix()).forEach(el->Arrays.fill(el, 4));

        Matrix resultMatrix = new MatrixMultiplier().multiplyMatricesByThreads(matrix_5x2, matrix_2x5, 4);

        checkArraysEquality(matrix_5x5.getMatrix(), resultMatrix.getMatrix());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage() throws MatrixException{
        thrown.expect(MatrixException.class);
        thrown.expectMessage(WRONG_DIMENSION_ERROR_MESSAGE);
        new Validation().isRowEqualColumn(matrix_2x3, matrix_2x3);
    }

    @Test
    public void shouldTestExceptionMessageEmptyOrNull() throws MatrixException{
        Matrix matrix = null;
        thrown.expect(MatrixException.class);
        thrown.expectMessage(MATRIX_NULL_OR_EMPTY);
        new Validation().isMatrixEmptyOrNull(matrix);
    }

    private void checkArraysEquality(float[][] expectedArray, float[][] actualArray) {
        for (int i = 0; i < actualArray.length; i++) {
            Assert.assertArrayEquals(expectedArray[i], actualArray[i], 0.1f);
        }
    }
}
