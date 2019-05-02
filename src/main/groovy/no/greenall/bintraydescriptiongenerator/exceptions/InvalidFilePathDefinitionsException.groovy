package no.greenall.bintraydescriptiongenerator.exceptions

class InvalidFilePathDefinitionsException extends Exception {
    public static final String EMPTY_FILE_PATH_DEFINITIONS_ERROR = "The file path definitions were empty"

    InvalidFilePathDefinitionsException() {
        super(EMPTY_FILE_PATH_DEFINITIONS_ERROR)
    }
}
