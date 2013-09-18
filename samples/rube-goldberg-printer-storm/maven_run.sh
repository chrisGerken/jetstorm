mvn -e exec:java -Dexec.classpathScope=compile -Dexec.mainClass=com.gerkenip.rgp.topology.RubeGoldbergPrinterTopology -Djava.util.logging.config.file=src/main/resources/logging.properties

#To execute in production
#storm jar rube-goldberg-printer-storm-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.gerkenip.rgp.topology.RubeGoldbergPrinterTopology prod -Djava.util.logging.config.file=prod.logging.properties

