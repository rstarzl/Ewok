package Ewok.DB;

public interface PhysicalDB {
	public boolean add(DBEntry	content);
	public boolean delete(DBEntry	content);
	public DBEntry query(DBEntry	content);
}
