package org.gradle

class Version {
  String version_major
  String minor
  String buildnumber
  String releaseType
  String buildTime
   
  String toString() {
  "$version_major.$minor.$buildnumber-$releaseType"
  }
}