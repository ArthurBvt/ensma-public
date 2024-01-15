package fr.ensma.a3.ia.carnetadressesdal.dao.sql;

import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.carnetadressesdal.dao.IDao;

public abstract class AbstractSqlDAO<T> implements IDao<T> {

	@Override
	public Optional<T> getById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<T> getByValue(T elem) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(T elem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T elem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T elem) {
		// TODO Auto-generated method stub
		
	}

}
