package pl.edu.pjatk.tau.labone.helper;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

// mvn exec:java -Dexec.mainClass="com.example.shdemo.helper.DatabaseExport" -Dexec.classpathScope=test

public class DatabaseExport {
	public static void main(String[] args) throws Exception {
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");

		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream(
				"src/test/resources/databaseDump.dtd"));
		
		FlatXmlDataSet.write(connection.createDataSet(), new FileOutputStream(
				"src/test/resources/databaseDump.xml"));
	}

}
