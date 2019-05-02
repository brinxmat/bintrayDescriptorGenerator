package no.greenall.bintraydescriptiongenerator.exceptions

class InvalidVersionExceptionTest extends GroovyTestCase {

    public static final String EXPECTED_MESSAGE = "Classic you"

    void testInvalidPackageExceptionCanBeThrowWithMessage() {

        String message = shouldFail {
            throw new InvalidVersionException(EXPECTED_MESSAGE)
        }

        assert EXPECTED_MESSAGE == message
    }

}
