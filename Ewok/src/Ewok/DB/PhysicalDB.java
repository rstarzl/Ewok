package Ewok.DB;
/**
 * 
 * @author JS
 *
 */
public interface PhysicalDB {
	public boolean add(DBEntry content);
	public boolean delete(DBEntry content);
	public DBEntry query(String key);
}
