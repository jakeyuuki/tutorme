package tutorme.models.core.interfaces;

import java.util.List;

import tutorme.models.core.LoadableObject;

public interface IBusinessObjectFactory {
	<T extends LoadableObject> List<T> load(String query, Class<T> type);
	<T extends LoadableObject> T loadTop1(String query, Class<T> type);
	<T extends LoadableObject> T loadByPK(String query, Class<T> type);
	<T extends LoadableObject> T create(Class<T> type);
	<T extends LoadableObject> T delete(String schemaColumnName, String query, Class<T> type);

	void save(); //saveAll()
}
