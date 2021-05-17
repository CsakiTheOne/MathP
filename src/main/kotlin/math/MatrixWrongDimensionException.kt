package math

import java.lang.Exception

class MatrixWrongDimensionException(m1: Matrix, m2: Matrix? = null): Exception("Matrix has wrong dimension! First:\n$m1\nOther:\n$m2")