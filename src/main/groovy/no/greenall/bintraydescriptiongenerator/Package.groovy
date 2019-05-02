package no.greenall.bintraydescriptiongenerator

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class Package {
    String name
    String repo
    String subject
    String desc
    URL website_url
    URL issue_tracker_url
    URL vcs_url
    String[] licenses
    String[] labels
    Boolean public_download_numbers
    Boolean public_stats

    String reportValidationErrors() {
        def report = []

        if (!isNameValid()) {
            report << "name"
        }

        if (!isRepoValid()) {
            report << "repo"
        }

        if (!isSubjectValid()) {
            report << "subject"
        }

        String reportString = "In order to be valid, Bintray requires that the package object contains fields, name, repo and subject, the following were lacking: ${report.join(", ")}"

        return reportString

    }

    boolean isValid() {
        return isNameValid() && isRepoValid() && isSubjectValid()
    }

    boolean isNameValid() {
        return verifyBintrayPackageString(this.name)
    }

    boolean isRepoValid() {
        return verifyBintrayPackageString(this.repo)
    }

    boolean isSubjectValid() {
        return verifyBintrayPackageString(this.subject)
    }

    static boolean verifyBintrayPackageString(String string) {
        return string && string?.trim()?.length() > 0
    }
}
