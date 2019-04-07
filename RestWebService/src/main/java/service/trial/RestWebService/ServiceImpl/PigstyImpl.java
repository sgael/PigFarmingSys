package service.trial.RestWebService.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.trial.RestWebService.Dao.PigstyDao;
import service.trial.RestWebService.Domain.Pigsty;
import service.trial.RestWebService.Service.PigstyService;

@Service
public class PigstyImpl implements PigstyService {

	@Autowired
	PigstyDao pigstyDao;

	public Pigsty savepig(Pigsty pigsty) {
		return pigstyDao.save(pigsty);
	}
	@Override
	public List<Pigsty> listall(){
		return pigstyDao.findAll();
	}
	
	@Override
	public Pigsty findByOwner(String owner) {
		return pigstyDao.findByOwner(owner);
	}
	@Override
	public void delete(Pigsty pigsty) {
		pigstyDao.delete(pigsty);
	}
	@Override
	public Pigsty findOne(Long id) {
		// TODO Auto-generated method stub
		return pigstyDao.findByid(id);
	}

}
