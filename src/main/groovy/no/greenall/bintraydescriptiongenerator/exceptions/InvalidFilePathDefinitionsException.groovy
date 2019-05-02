package no.greenall.bintraydescriptiongenerator.exceptions

class InvalidFilePathDefinitionsException extends Exception {
    InvalidFilePathDefinitionsException() {
        super("FilePathDefinitions are not set")
    }
}
