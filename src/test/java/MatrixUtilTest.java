import com.starykov.data.Matrix;
import com.starykov.exception.MatrixException;
import com.starykov.util.MatrixUtil;
import com.starykov.util.Printer;
import com.starykov.validation.Validation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.starykov.util.StringConstants.MATRIX_NULL_OR_EMPTY;
import static com.starykov.util.StringConstants.WRONG_DIMENSION_ERROR_MESSAGE;

public class MatrixUtilTest {

    private Matrix matrix_2x3 = new Matrix(2, 3);
    private Matrix matrix_3x2 = new Matrix(3, 2);
    private Matrix matrix_2x2 = new Matrix(2, 2);

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
    public void shouldCorrectMultiplication() {
        fillTestMatrix();
        Matrix resultMatrix = new MatrixUtil().matrixMultiplication(matrix_2x3, matrix_3x2);

        for (int i = 0; i < resultMatrix.getMatrix().length; i++) {
            Assert.assertArrayEquals(resultMatrix.getMatrix()[i], matrix_2x2.getMatrix()[i], 0.1f);
        }
    }

    @Test
    public void shouldCorrectMultiplicationByThreads() throws InterruptedException {
        fillTestMatrix();
        Matrix resultMatrix = new MatrixUtil().matrixMultiplicationByThreads(matrix_2x3, matrix_3x2, 4);

        for (int i = 0; i < resultMatrix.getMatrix().length; i++) {
            Assert.assertArrayEquals(resultMatrix.getMatrix()[i], matrix_2x2.getMatrix()[i], 0.1f);
        }
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
}