package org.acme.resources;

import io.agroal.api.AgroalDataSource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/departments")
public class ExampleResource {
	
	@Inject
    @Named("todos")
    AgroalDataSource dataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws SQLException {
    	
    	 StringBuilder sb = new StringBuilder();
         try (Connection connection = dataSource.getConnection();
              PreparedStatement ps =
                  connection.prepareStatement(" SELECT * FROM departments");
              ResultSet rs = ps.executeQuery()) {
             while (rs.next()) {
                 sb.append(rs.getString("department_name")).append("\n");
             }
         }
         return sb.toString();
    }
}