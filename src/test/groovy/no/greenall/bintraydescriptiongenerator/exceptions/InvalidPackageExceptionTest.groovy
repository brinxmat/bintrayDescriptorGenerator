package no.greenall.bintraydescriptiongenerator.exceptions

class InvalidPackageExceptionTest extends GroovyTestCase {

    public static final String EXPECTED_MESSAGE = "Classic me"

    void testInvalidPackageExceptionCanBeThrowWithMessage() {

        String message = shouldFail {
            throw new InvalidPackageException(EXPECTED_MESSAGE)
        }

        assert EXPECTED_MESSAGE == message
    }

}