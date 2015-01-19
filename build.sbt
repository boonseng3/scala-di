name := "Scala DI"

version := "0.1"

scalaVersion := "2.11.4"

libraryDependencies ++= {
  Seq(
  	 "org.scaldi"    %% "scaldi"         % "0.4",
     "com.escalatesoft.subcut"    %% "subcut"         % "2.1",
     "net.codingwell"    %% "scala-guice"         % "4.0.0-beta5"
  )  
}

EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.Unmanaged, EclipseCreateSrc.Source, EclipseCreateSrc.Resource)

EclipseKeys.withSource := true
