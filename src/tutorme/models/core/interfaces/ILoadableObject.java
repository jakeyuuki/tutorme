package tutorme.models.core.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ILoadableObject {
	void load(ResultSet rs) throws SQLException; // un-parse object
	void save(Connection connection) throws SQLException; // save object in database or Parse.
	void delete(); //delete this object when factory.save;
	void reload(); //reload object
}
